package com.github.thedeathlycow.moregeodes.features;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.tag.ModBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.Objects;
import java.util.function.Predicate;

public class ModFeatures {

    public static void placeFeaturesInBiomes() {

        addGeodeToBiomes(
                BiomeSelectors.tag(ModBiomeTags.HAS_EMERALD_GEODE),
                ModPlacedFeatures.EMERALD_GEODE,
                MoreGeodes.CONFIG.generateEmeraldGeodes()
        );

        addGeodeToBiomes(
                BiomeSelectors.tag(ModBiomeTags.HAS_QUARTZ_GEODE),
                ModPlacedFeatures.QUARTZ_GEODE,
                MoreGeodes.CONFIG.generateQuartzGeodes()
        );

        addGeodeToBiomes(
                BiomeSelectors.tag(ModBiomeTags.HAS_DIAMOND_GEODE),
                ModPlacedFeatures.DIAMOND_GEODE,
                MoreGeodes.CONFIG.generateDiamondGeodes()
        );

        addGeodeToBiomes(
                BiomeSelectors.tag(ModBiomeTags.HAS_ECHO_GEODE),
                ModPlacedFeatures.ECHO_GEODE,
                MoreGeodes.CONFIG.generateEchoGeodes()
        );

        addGeodeToBiomes(
                BiomeSelectors.tag(ModBiomeTags.HAS_LAPIS_GEODE),
                ModPlacedFeatures.LAPIS_GEODE,
                MoreGeodes.CONFIG.generateLapisGeodes()
        );
        addGeodeToBiomes(
                BiomeSelectors.tag(ModBiomeTags.HAS_GYPSUM_PATCH),
                ModPlacedFeatures.GYPSUM_PATCH,
                MoreGeodes.CONFIG.generateGypsumPatches(),
                GenerationStep.Feature.VEGETAL_DECORATION
        );
    }

    private static void addGeodeToBiomes(
            Predicate<BiomeSelectionContext> biomeSelector,
            RegistryKey<PlacedFeature> geode,
            boolean shouldAdd
    ) {
        addGeodeToBiomes(biomeSelector, geode, shouldAdd, GenerationStep.Feature.UNDERGROUND_DECORATION);
    }

    private static void addGeodeToBiomes(
            Predicate<BiomeSelectionContext> biomeSelector,
            RegistryKey<PlacedFeature> geode,
            boolean shouldAdd,
            GenerationStep.Feature step
    ) {
        if (shouldAdd) {
            BiomeModifications.addFeature(
                    biomeSelector,
                    step,
                    Objects.requireNonNull(geode)
            );
        }
    }
}
