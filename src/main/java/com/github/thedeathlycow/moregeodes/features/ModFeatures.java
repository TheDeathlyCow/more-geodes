package com.github.thedeathlycow.moregeodes.features;

import com.github.thedeathlycow.moregeodes.tag.ModBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class ModFeatures {

    public static void placedFeaturesInBiomes() {

        BiomeModifications.addFeature
                (
                        BiomeSelectors.tag(ModBiomeTags.HAS_EMERALD_GEODE),
                        GenerationStep.Feature.UNDERGROUND_DECORATION,
                        ModPlacedFeatures.EMERALD_GEODE.getKey().get()
                );

        BiomeModifications.addFeature
                (
                        BiomeSelectors.tag(ModBiomeTags.HAS_QUARTZ_GEODE),
                        GenerationStep.Feature.UNDERGROUND_DECORATION,
                        ModPlacedFeatures.QUARTZ_GEODE.getKey().get()
                );
    }
}
