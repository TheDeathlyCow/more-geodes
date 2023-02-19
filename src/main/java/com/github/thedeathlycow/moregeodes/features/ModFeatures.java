package com.github.thedeathlycow.moregeodes.features;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.tag.ModBiomeTags;
import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.function.BiConsumer;

public class ModFeatures {

    public static void placeFeaturesInBiomes() {

        BiomeModification modification = BiomeModifications.create(
                new Identifier(MoreGeodes.MODID, "geode_additions")
        );

        modification
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(ModBiomeTags.HAS_EMERALD_GEODE),
                        modifier(
                                ModPlacedFeatures.EMERALD_GEODE,
                                GenerationStep.Feature.UNDERGROUND_DECORATION,
                                MoreGeodes.CONFIG.generateEmeraldGeodes()
                        )
                )
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(ModBiomeTags.HAS_QUARTZ_GEODE),
                        modifier(
                                ModPlacedFeatures.QUARTZ_GEODE,
                                GenerationStep.Feature.UNDERGROUND_DECORATION,
                                MoreGeodes.CONFIG.generateQuartzGeodes()
                        )
                )
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(ModBiomeTags.HAS_DIAMOND_GEODE),
                        modifier(
                                ModPlacedFeatures.DIAMOND_GEODE,
                                GenerationStep.Feature.UNDERGROUND_DECORATION,
                                MoreGeodes.CONFIG.generateDiamondGeodes()
                        )
                )
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(ModBiomeTags.HAS_ECHO_GEODE),
                        modifier(
                                ModPlacedFeatures.ECHO_GEODE,
                                GenerationStep.Feature.UNDERGROUND_DECORATION,
                                MoreGeodes.CONFIG.generateEchoGeodes()
                        )
                )
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(ModBiomeTags.HAS_LAPIS_GEODE),
                        modifier(
                                ModPlacedFeatures.LAPIS_GEODE,
                                GenerationStep.Feature.UNDERGROUND_DECORATION,
                                MoreGeodes.CONFIG.generateLapisGeodes()
                        )
                )
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(ModBiomeTags.HAS_GYPSUM_PATCH),
                        modifier(
                                ModPlacedFeatures.GYPSUM_PATCH,
                                GenerationStep.Feature.VEGETAL_DECORATION,
                                MoreGeodes.CONFIG.generateGypsumPatches()
                        )
                );

        if (MoreGeodes.isAE2Loaded()) {
            modification.add(
                    ModificationPhase.ADDITIONS,
                    BiomeSelectors.tag(ModBiomeTags.HAS_CERTUS_GEODE),
                    modifier(
                            ModPlacedFeatures.CERTUS_GEODE,
                            GenerationStep.Feature.UNDERGROUND_DECORATION,
                            MoreGeodes.CONFIG.generateCertusGeodes()
                    )
            );
        }
    }

    private static BiConsumer<BiomeSelectionContext, BiomeModificationContext> modifier(
            RegistryKey<PlacedFeature> feature,
            GenerationStep.Feature step,
            boolean shouldAdd
    ) {
        return (biomeSelectionContext, biomeModificationContext) -> {
            if (shouldAdd) {
                biomeModificationContext.getGenerationSettings().addFeature(
                        step,
                        feature
                );
            }
        };
    }
}
