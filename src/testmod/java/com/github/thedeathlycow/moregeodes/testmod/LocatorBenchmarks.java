package com.github.thedeathlycow.moregeodes.testmod;

import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import com.github.thedeathlycow.moregeodes.entity.EchoDisplay;
import com.github.thedeathlycow.moregeodes.entity.GeodesEntityTypes;
import com.github.thedeathlycow.moregeodes.item.*;
import com.google.gson.*;
import io.netty.buffer.Unpooled;
import java.lang.management.ManagementFactory;
import java.nio.file.*;
import java.util.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;

/** The same compiled helper runs against the original V1 and optimized V2 JARs. */
public final class LocatorBenchmarks implements ModInitializer {
    private static final BlockPos ORIGIN = new BlockPos(8, 280, 8);
    private static final int WARMUPS = 12, SAMPLES = 24;

    @Override public void onInitialize() {
        if (!Boolean.getBoolean("geodes.tests.benchmarkOnly")) return;
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            ServerLevel level = server.overworld();
            for (int x = -2; x <= 2; x++) for (int z = -2; z <= 2; z++) level.setChunkForced(x, z, true);
        });
        ServerTickEvents.END_SERVER_TICK.register(new java.util.function.Consumer<net.minecraft.server.MinecraftServer>() {
            private int ticks;
            @Override public void accept(net.minecraft.server.MinecraftServer server) {
                if (++ticks != 40) return; // Finish asynchronous entity-section loading outside timing.
                run(server);
            }
        }::accept);
    }

    private static void run(net.minecraft.server.MinecraftServer server) {
            try {
                ServerLevel level = server.overworld();
                JsonObject report = new JsonObject();
                report.addProperty("mod_version", FabricLoader.getInstance().getModContainer("geodes").orElseThrow().getMetadata().getVersion().getFriendlyString());
                report.addProperty("java", System.getProperty("java.version"));
                report.addProperty("seed", level.getSeed());
                report.addProperty("warmups_per_case", WARMUPS); report.addProperty("samples_per_case", SAMPLES);
                report.addProperty("scope", "Synchronous activation CPU/allocation, loaded chunks, no tracking players; not FPS or chunk-generation timing");
                // Preload every chunk that either locator could inspect, outside timing.
                for (int x = (ORIGIN.getX() - 28) >> 4; x <= (ORIGIN.getX() + 28) >> 4; x++)
                    for (int z = (ORIGIN.getZ() - 28) >> 4; z <= (ORIGIN.getZ() + 28) >> 4; z++) level.getChunk(x, z);
                JsonArray cases = new JsonArray();
                for (String name : List.of("untuned", "tuned_empty", "tuned_sparse", "tuned_dense_128", "echo_dense")) {
                    for (BlockPos p : BlockPos.betweenClosed(ORIGIN.offset(-27,-27,-27), ORIGIN.offset(27,27,27)))
                        level.setBlock(p, Blocks.AIR.defaultBlockState(), 2);
                    int targets = name.equals("tuned_sparse") ? 1 : name.endsWith("128") || name.equals("echo_dense") ? 128 : 0;
                    for (int i = 0; i < targets; i++) level.setBlock(ORIGIN.offset((i % 8) * 2 - 7, (i / 8 % 4) * 2 - 3, (i / 32) * 2 - 3), ModBlocks.EMERALD_CLUSTER.defaultBlockState(), 2);
                    ItemStack stack = name.equals("untuned") ? ModItems.CRYSTAL_LOCATOR.getDefaultInstance()
                            : name.equals("echo_dense") ? ModItems.ECHO_LOCATOR.getDefaultInstance()
                            : GeodesItemGroup.makeTunedLocatorStack("geodes:emerald");
                    Thread.sleep(250); // Let asynchronous setup work settle, outside the measured interval.
                    cases.add(measure(level, name, stack));
                }
                report.add("activation_cases", cases);
                report.add("metadata", metadata(level));
                Files.writeString(Path.of("locator-benchmark.json"), new GsonBuilder().setPrettyPrinting().create().toJson(report));
                System.out.println("GEODES_BENCHMARK_COMPLETE " + report.get("mod_version"));
            } catch (Throwable error) {
                error.printStackTrace();
                try { Files.writeString(Path.of("benchmark-failure.txt"), error.toString()); } catch (Exception ignored) {}
            } finally { server.halt(false); }
    }

    private static JsonObject measure(ServerLevel level, String name, ItemStack stack) {
        CrystalLocatorItem item = (CrystalLocatorItem)stack.getItem();
        com.sun.management.ThreadMXBean bean = (com.sun.management.ThreadMXBean)ManagementFactory.getThreadMXBean();
        if (bean.isThreadAllocatedMemorySupported() && !bean.isThreadAllocatedMemoryEnabled()) bean.setThreadAllocatedMemoryEnabled(true);
        long thread = Thread.currentThread().threadId();
        int expected = -1;
        for (int i = 0; i < WARMUPS; i++) {
            int found = item.activate(level, ORIGIN, stack);
            if (expected < 0) expected = found;
            if (found != expected) throw new AssertionError("Unstable warmup target count");
            cleanup(level, found);
        }
        JsonArray nanos = new JsonArray(), bytes = new JsonArray();
        long[] times = new long[SAMPLES], allocations = new long[SAMPLES];
        for (int i = 0; i < SAMPLES; i++) {
            long beforeBytes = bean.getThreadAllocatedBytes(thread), start = System.nanoTime();
            int found = item.activate(level, ORIGIN, stack);
            times[i] = System.nanoTime() - start;
            allocations[i] = bean.getThreadAllocatedBytes(thread) - beforeBytes;
            if (found != expected) throw new AssertionError("Target count changed during measurement");
            nanos.add(times[i]); bytes.add(allocations[i]);
            cleanup(level, found);
        }
        Arrays.sort(times); Arrays.sort(allocations);
        JsonObject out = new JsonObject(); out.addProperty("case", name); out.addProperty("matched_blocks", expected);
        out.addProperty("median_ms", (times[11] + times[12]) / 2_000_000.0);
        out.addProperty("p95_ms", times[22] / 1_000_000.0);
        out.addProperty("median_thread_allocated_bytes", (allocations[11] + allocations[12]) / 2);
        out.add("sample_nanos", nanos); out.add("sample_thread_allocated_bytes", bytes);
        return out;
    }

    private static void cleanup(ServerLevel level, int expected) {
        var displays = level.getEntitiesOfClass(EchoDisplay.class, new AABB(ORIGIN).inflate(29));
        if (displays.size() != expected) throw new AssertionError("Fixture entity sections are not loaded: " + displays.size() + "/" + expected);
        for (EchoDisplay display : displays) display.discard();
    }

    private static JsonObject metadata(ServerLevel level) {
        int batches = 0, payloadBytes = 0;
        for (int i = 0; i < 100; i++) {
            EchoDisplay display = GeodesEntityTypes.ECHO_DISPLAY.create(level, EntitySpawnReason.TRIGGERED);
            display.setBlockState(ModBlocks.EMERALD_CLUSTER.defaultBlockState());
            display.setPos(8, 280, 48); display.setGlowDelay(12); display.getEntityData().packDirty();
            for (int tick = 0; tick < 42; tick++) {
                display.tick();
                var dirty = display.getEntityData().packDirty();
                if (dirty != null) {
                    batches++;
                    RegistryFriendlyByteBuf buf = new RegistryFriendlyByteBuf(Unpooled.buffer(), level.registryAccess());
                    try {
                        // Normalize the entity ID to keep varint length identical.
                        ClientboundSetEntityDataPacket.STREAM_CODEC.encode(buf, new ClientboundSetEntityDataPacket(1, dirty));
                        payloadBytes += buf.readableBytes();
                    } finally { buf.release(); }
                }
            }
            if (!display.isRemoved()) throw new AssertionError("Display lifetime changed");
        }
        JsonObject out = new JsonObject(); out.addProperty("display_count",100); out.addProperty("ticks_per_display",42);
        out.addProperty("dirty_metadata_batches",batches); out.addProperty("encoded_metadata_body_bytes",payloadBytes);
        out.addProperty("scope","Actual dirty tracker data encoded with the vanilla packet codec; excludes spawn/removal, framing, compression and transport");
        return out;
    }
}
