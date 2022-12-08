package com.github.thedeathlycow.moregeodes.features;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {

    public static final RegistryEntry<PlacedFeature> EMERALD_GEODE;
    public static final RegistryEntry<PlacedFeature> QUARTZ_GEODE;
    public static final RegistryEntry<PlacedFeature> DIAMOND_GEODE;
    public static final RegistryEntry<PlacedFeature> ECHO_GEODE;

    public static final RegistryEntry<PlacedFeature> LAPIS_GEODE;
    public static final RegistryEntry<PlacedFeature> GYPSUM_PATCH;

    private static RegistryEntry<PlacedFeature> register(String id, PlacedFeature placedFeature) {
        return BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, new Identifier(MoreGeodes.MODID, id), placedFeature);
    }

    static {
        EMERALD_GEODE = register("emerald_geode", new PlacedFeature(ModConfiguredFeatures.EMERALD_GEODE,
                List.of(
                        RarityFilterPlacementModifier.of(24),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6), YOffset.fixed(64)),
                        BiomePlacementModifier.of()
                )));
        QUARTZ_GEODE = register("quartz_geode", new PlacedFeature(ModConfiguredFeatures.QUARTZ_GEODE,
                List.of(
                        RarityFilterPlacementModifier.of(12),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6), YOffset.belowTop(6)),
                        BiomePlacementModifier.of()
                )));
        DIAMOND_GEODE = register("diamond_geode", new PlacedFeature(ModConfiguredFeatures.DIAMOND_GEODE,
                List.of(
                        RarityFilterPlacementModifier.of(400),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(4), YOffset.aboveBottom(60)),
                        BiomePlacementModifier.of()
                )));
        ECHO_GEODE = register("echo_geode", new PlacedFeature(ModConfiguredFeatures.ECHO_GEODE,
                List.of(
                        RarityFilterPlacementModifier.of(24),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6), YOffset.fixed(32)),
                        BiomePlacementModifier.of()
                )));
        LAPIS_GEODE = register("lapis_geode", new PlacedFeature(ModConfiguredFeatures.LAPIS_GEODE,
                List.of(
                        RarityFilterPlacementModifier.of(24),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6), YOffset.fixed(32)),
                        BiomePlacementModifier.of()
                )));
        GYPSUM_PATCH = register("gypsum_patch", new PlacedFeature(
                ModConfiguredFeatures.GYPSUM_PATCH,
                List.of(
                        RarityFilterPlacementModifier.of(36),
                        SquarePlacementModifier.of(),
                        HeightmapPlacementModifier.of(Heightmap.Type.WORLD_SURFACE),
                        BiomePlacementModifier.of()
                )
        ));
    }
}
