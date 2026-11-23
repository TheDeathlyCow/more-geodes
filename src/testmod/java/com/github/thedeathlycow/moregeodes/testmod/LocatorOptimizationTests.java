package com.github.thedeathlycow.moregeodes.testmod;

import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import com.github.thedeathlycow.moregeodes.entity.EchoDisplay;
import com.github.thedeathlycow.moregeodes.entity.GeodesEntityTypes;
import com.github.thedeathlycow.moregeodes.item.*;
import com.github.thedeathlycow.moregeodes.item.tuning.Tuning;
import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import java.util.*;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.phys.AABB;

/** Behavior comparisons against V1's independent iterator and predicate paths. */
final class LocatorOptimizationTests {
    private static RecordingLocator wide, narrow;
    private static AddonLocator addon;

    static void registerFixtures() {
        wide = register("scan_48", new RecordingLocator(48));
        narrow = register("scan_6", new RecordingLocator(6));
        addon = register("addon_hook", new AddonLocator());
    }

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath("geodes-test", name), item);
    }

    private static final class RecordingLocator extends CrystalLocatorItem {
        Iterator<BlockPos> reference;
        BlockPos origin;
        int visits, range;
        RecordingLocator(int range) { super(testProperties("scan_" + range), range, 20); this.range = range; }
        @Override protected boolean isPingableCrystal(ItemStack stack, ServerLevel world, BlockPos actual) {
            BlockPos expected = null;
            while (reference.hasNext()) {
                BlockPos candidate = reference.next();
                if (origin.distSqr(candidate) * 3 <= range * range) { expected = candidate; break; }
            }
            require(expected != null && expected.equals(actual), "Search position/order changed at " + actual);
            visits++;
            return false;
        }
    }

    private static final class AddonLocator extends TunedCrystalLocatorItem {
        int visits;
        AddonLocator() { super(testProperties("addon_hook"), 6, 20); }
        @Override protected boolean isPingableCrystal(ItemStack stack, ServerLevel world, BlockPos pos) { visits++; return false; }
    }

    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    static void orderedSearch(ServerLevel level) {
        for (int range : new int[]{48, 6}) {
            for (BlockPos origin : List.of(BlockPos.ZERO, new BlockPos(-37, 220, -91),
                    new BlockPos(29_999_950, -40, -29_999_950))) {
                int extent = (int)Math.floor(range / Math.sqrt(3));
                Iterator<BlockPos> reference = BlockPos.betweenClosed(
                        origin.offset(-extent, -extent, -extent), origin.offset(extent, extent, extent)).iterator();
                var locator = range == 48 ? wide : narrow;
                locator.origin = origin; locator.reference = reference; locator.visits = 0;
                require(locator.activate(level, origin, ModItems.CRYSTAL_LOCATOR.getDefaultInstance()) == 0,
                        "Recording hook unexpectedly matched");
                while (reference.hasNext()) {
                    require(origin.distSqr(reference.next()) * 3 > range * range, "Search omitted an eligible position");
                }
                require(locator.visits == (range == 48 ? 88_959 : 179), "Wrong search size");
            }
        }
        addon.visits = 0;
        addon.activate(level, BlockPos.ZERO, ModItems.TUNED_CRYSTAL_LOCATOR.getDefaultInstance());
        require(addon.visits == 179, "Existing tuned-locator subclass hook was bypassed");
    }

    private static Item.Properties testProperties(String name) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("geodes-test", name)));
    }

    static void predicates(ServerLevel level) {
        BlockPos origin = new BlockPos(8, 280, 48);
        List<Tuning> tunings = new ArrayList<>();
        for (String id : List.of("amethyst", "emerald", "quartz", "diamond", "echo", "lapis", "gypsum")) {
            tunings.add(TunedCrystalLocatorItem.getTuning(level.registryAccess(), GeodesItemGroup.makeTunedLocatorStack("geodes:" + id)));
        }
        Block[] blocks = {Blocks.AMETHYST_CLUSTER, ModBlocks.EMERALD_CLUSTER, ModBlocks.QUARTZ_CLUSTER,
                ModBlocks.DIAMOND_CLUSTER, ModBlocks.ECHO_CLUSTER, ModBlocks.LAPIS_CLUSTER, ModBlocks.GYPSUM_ROSE};
        for (int i = 0; i < blocks.length; i++) level.setBlock(origin.offset(i * 2, 0, 0), Blocks.AIR.defaultBlockState(), 2);
        // Keep fixtures in the already entity-accessible chunk. Synchronously
        // loading a neighboring terrain chunk does not finish its entity load.
        for (int i = 0; i < blocks.length; i++) level.setBlock(origin.offset((i % 4) * 2, 0, (i / 4) * 2), blocks[i].defaultBlockState(), 2);
        for (String json : List.of(
                "{\"block\":{\"blocks\":\"#geodes:echo_locatable\"}}",
                "{\"dimension\":\"minecraft:the_nether\",\"block\":{\"blocks\":[\"geodes:emerald_cluster\"]}}",
                "{\"position\":{\"y\":{\"max\":250}},\"block\":{\"blocks\":[\"geodes:emerald_cluster\"]}}",
                "{\"can_see_sky\":false,\"block\":{\"blocks\":[\"geodes:emerald_cluster\"]}}",
                "{\"light\":{\"light\":{\"min\":0,\"max\":15}},\"block\":{\"blocks\":[\"geodes:emerald_cluster\"]}}",
                "{\"block\":{\"blocks\":[\"geodes:emerald_cluster\"],\"state\":{\"facing\":\"down\"}}}",
                "{}")) {
            LocationPredicate predicate = LocationPredicate.CODEC.parse(level.registryAccess().createSerializationContext(JsonOps.INSTANCE),
                    JsonParser.parseString(json)).getOrThrow();
            tunings.add(new Tuning(0xffffffff, Component.literal("test predicate"), Optional.of(predicate)));
        }
        List<BlockPos> exceptional = List.of(new BlockPos(2_000_000, 100, 2_000_000),
                new BlockPos(0, level.getMinY() - 1, 0), new BlockPos(0, level.getMaxY(), 0));
        require(!level.isLoaded(exceptional.getFirst()), "Unloaded-boundary fixture is already loaded");
        for (Tuning tuning : tunings) {
            require(tuning != null, "Missing core tuning");
            var prepared = tuning.prepareMatcher(level);
            require(prepared != null, "Valid tuning lost its matcher");
            for (BlockPos pos : BlockPos.betweenClosed(origin.offset(-8, -2, -8), origin.offset(16, 2, 8))) {
                require(prepared.test(pos) == tuning.test(level, pos), "Predicate semantics changed at " + pos);
            }
            for (BlockPos pos : exceptional) require(prepared.test(pos) == tuning.test(level, pos), "Boundary predicate changed");
        }
        require(!level.isLoaded(exceptional.getFirst()), "Matcher forced an unloaded chunk to load");
        require(Tuning.UNTUNED.prepareMatcher(level) == null, "Untuned scan was not eliminated");
    }

    private record Expected(BlockPos pos, BlockState state, int delay) {}

    static void activation(ServerLevel level) {
        BlockPos origin = new BlockPos(8, 280, 48);
        for (String tuning : List.of("amethyst", "emerald", "quartz", "diamond", "echo", "lapis", "gypsum")) {
            ItemStack stack = GeodesItemGroup.makeTunedLocatorStack("geodes:" + tuning);
            Tuning predicate = TunedCrystalLocatorItem.getTuning(level.registryAccess(), stack);
            List<Expected> expected = new ArrayList<>();
            // V1's bounding-box iterator and full location predicate are the oracle.
            for (BlockPos pos : BlockPos.betweenClosed(origin.offset(-27, -27, -27), origin.offset(27, 27, 27))) {
                double distance = origin.distSqr(pos) * 3;
                if (distance <= 48 * 48 && predicate.test(level, pos)) {
                    expected.add(new Expected(pos.immutable(), level.getBlockState(pos), Mth.ceil(Math.sqrt(distance))));
                }
            }
            require(!expected.isEmpty(), "Activation fixture has no " + tuning + " target");
            int count = ((CrystalLocatorItem)ModItems.TUNED_CRYSTAL_LOCATOR).activate(level, origin, stack);
            var actual = level.getEntitiesOfClass(EchoDisplay.class, new AABB(origin).inflate(29));
            actual.sort(Comparator.comparingInt(EchoDisplay::getId));
            require(count == expected.size() && actual.size() == expected.size(), "Activation count changed for " + tuning
                    + ": reference=" + expected.size() + ", returned=" + count + ", queried=" + actual.size());
            for (int i = 0; i < actual.size(); i++) {
                EchoDisplay display = actual.get(i); Expected reference = expected.get(i);
                require(display.blockPosition().equals(reference.pos) && display.getBlockState() == reference.state,
                        "Display position/state/order changed for " + tuning);
                var saved = save(level, display);
                require(saved.getIntOr("time_to_live", 0) == 30 + reference.delay
                        && saved.getIntOr("glow_time", 0) == 30 && display.isInvisible(), "Wave delay changed");
                display.discard();
            }
        }
        require(((CrystalLocatorItem)ModItems.CRYSTAL_LOCATOR).activate(level, origin, ModItems.CRYSTAL_LOCATOR.getDefaultInstance()) == 0,
                "Untuned locator unexpectedly detected a target");
        ItemStack invalid = ModItems.TUNED_CRYSTAL_LOCATOR.getDefaultInstance();
        invalid.set(ModComponents.TUNING, Identifier.fromNamespaceAndPath("geodes-test", "missing"));
        require(((CrystalLocatorItem)ModItems.TUNED_CRYSTAL_LOCATOR).activate(level, origin, invalid) == 0, "Invalid tuning matched");
    }

    static void metadata(ServerLevel level) {
        require(GeodesEntityTypes.ECHO_DISPLAY.clientTrackingRange() == 5 && GeodesEntityTypes.ECHO_DISPLAY.updateInterval() == 3,
                "Original entity tracking settings were not restored");
        EchoDisplay display = GeodesEntityTypes.ECHO_DISPLAY.create(level, EntitySpawnReason.TRIGGERED);
        require(display != null, "Display creation failed");
        display.setBlockState(ModBlocks.EMERALD_CLUSTER.defaultBlockState());
        display.setPos(8, 285, 48); display.setGlowDelay(12); display.getEntityData().packDirty();
        for (int tick = 1; tick < 12; tick++) {
            display.tick();
            require(display.getEntityData().packDirty() == null && display.isInvisible(), "Pending countdown dirtied metadata or pinged early");
        }
        var saved = save(level, display);
        EchoDisplay restored = GeodesEntityTypes.ECHO_DISPLAY.create(level, EntitySpawnReason.LOAD);
        restored.load(TagValueInput.create(ProblemReporter.DISCARDING, level.registryAccess(), saved));
        require(save(level, restored).getIntOr("time_to_live", 0) == 31, "Saved countdown changed");
        restored.getEntityData().packDirty(); restored.tick();
        require(!restored.isInvisible() && restored.isCurrentlyGlowing() && restored.getEntityData().packDirty() != null,
                "Reloaded wave failed to synchronize its first visible tick");
        for (int tick = 0; tick < 29; tick++) {
            restored.tick();
            require(restored.getEntityData().packDirty() == null && !restored.isRemoved(), "Visible countdown dirtied metadata or expired early");
        }
        restored.tick(); require(restored.isRemoved(), "Reloaded display did not expire on the original tick");
        display.discard();
    }

    private static net.minecraft.nbt.CompoundTag save(ServerLevel level, EchoDisplay display) {
        TagValueOutput out = TagValueOutput.createWithContext(ProblemReporter.DISCARDING, level.registryAccess());
        display.saveWithoutId(out);
        return out.buildResult();
    }
}
