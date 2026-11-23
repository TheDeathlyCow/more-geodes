package com.github.thedeathlycow.moregeodes.testmod;

import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import com.github.thedeathlycow.moregeodes.entity.EchoDisplay;
import com.github.thedeathlycow.moregeodes.entity.GeodesEntityTypes;
import com.google.gson.*;
import java.nio.file.*;
import java.util.Arrays;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.world.entity.EntitySpawnReason;
import org.joml.Matrix4f;

final class LocatorRenderTests {
    private static volatile long sink;

    static String run(Minecraft mc) throws Exception {
        EchoDisplay[] displays = new EchoDisplay[256];
        for (int i = 0; i < displays.length; i++) {
            EchoDisplay display = GeodesEntityTypes.ECHO_DISPLAY.create(mc.level, EntitySpawnReason.TRIGGERED);
            display.setPos(mc.player.getX(), mc.player.getY(), mc.player.getZ());
            display.setBlockState(ModBlocks.EMERALD_CLUSTER.defaultBlockState());
            display.tick(); // Initialize vanilla's client display render state.
            displays[i] = display;
        }
        Frustum frustum = new Frustum(new Matrix4f(), new Matrix4f());
        var renderer = mc.getEntityRenderDispatcher().getRenderer(displays[0]);
        String version = FabricLoader.getInstance().getModContainer("geodes").orElseThrow().getMetadata().getVersion().getFriendlyString();
        boolean visiblePending = renderer.shouldRender(displays[0], frustum, mc.player.getX(), mc.player.getY(), mc.player.getZ(), 0);
        if (version.endsWith("portV2") && visiblePending) throw new AssertionError("Pending display reached model extraction");
        displays[0].setInvisible(false);
        // Simulate the shared flag received from the server, rather than the
        // server-only setter (which intentionally does not set a client flag).
        var sharedFlag = net.minecraft.world.entity.Entity.class.getDeclaredMethod("setSharedFlag", int.class, boolean.class);
        sharedFlag.setAccessible(true); sharedFlag.invoke(displays[0], 6, true);
        displays[0].tick();
        if (!renderer.shouldRender(displays[0], frustum, mc.player.getX(), mc.player.getY(), mc.player.getZ(), 0))
            throw new AssertionError("Visible outline was culled");
        if (renderer.createRenderState(displays[0], 0).outlineColor == 0) throw new AssertionError("Visible outline color lost");
        displays[0].setInvisible(true);
        if (Boolean.getBoolean("geodes.tests.renderBenchmark")) {
            for (int i = 0; i < 60; i++) frame(mc, displays, frustum);
            long[] nanos = new long[120]; int states = 0;
            for (int i = 0; i < nanos.length; i++) {
                long start = System.nanoTime(); states += frame(mc, displays, frustum); nanos[i] = System.nanoTime() - start;
            }
            JsonArray samples = new JsonArray(); for (long n : nanos) samples.add(n);
            Arrays.sort(nanos);
            JsonObject out = new JsonObject(); out.addProperty("mod_version", version);
            out.addProperty("pending_displays",displays.length); out.addProperty("measured_frames",nanos.length);
            out.addProperty("created_render_states",states); out.addProperty("median_preparation_ms",(nanos[59]+nanos[60])/2_000_000.0);
            out.addProperty("p95_preparation_ms",nanos[113]/1_000_000.0); out.add("sample_nanos",samples);
            out.addProperty("scope","Synthetic pending-display visibility/extraction CPU test on the render thread, not FPS or GPU draw time");
            Files.writeString(Path.of("locator-render-benchmark.json"),new GsonBuilder().setPrettyPrinting().create().toJson(out));
        }
        for (EchoDisplay display : displays) display.discard();
        return "PASS: pending-display visibility gate and visible outline state\n";
    }

    private static int frame(Minecraft mc, EchoDisplay[] displays, Frustum frustum) {
        int states = 0;
        for (EchoDisplay display : displays) {
            var renderer = mc.getEntityRenderDispatcher().getRenderer(display);
            if (renderer.shouldRender(display, frustum, mc.player.getX(), mc.player.getY(), mc.player.getZ(), 0)) {
                sink += renderer.createRenderState(display, 0).lightCoords;
                states++;
            }
        }
        return states;
    }
}
