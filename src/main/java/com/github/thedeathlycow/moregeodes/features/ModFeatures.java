package com.github.thedeathlycow.moregeodes.features;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;

public class ModFeatures {
    public static void placedFeaturesInBiomes() {

        BiomeModifications.addFeature
                (
                        BiomeSelectors.categories(Biome.Category.MOUNTAIN),
                        GenerationStep.Feature.UNDERGROUND_DECORATION,
                        ModPlacedFeatures.EMERALD_GEODE.getKey().get()
                );

        BiomeModifications.addFeature
                (
                        BiomeSelectors.categories(Biome.Category.NETHER),
                        GenerationStep.Feature.UNDERGROUND_DECORATION,
                        ModPlacedFeatures.QUARTZ_GEODE.getKey().get()
                );
    }
}
