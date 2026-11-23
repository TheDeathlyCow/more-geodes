package com.github.thedeathlycow.moregeodes.features;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.tag.ModBiomeTags;
import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import java.util.function.BiConsumer;

public class ModFeatures {

    public static void placeFeaturesInBiomes() {

        BiomeModification modification = BiomeModifications.create(
                Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "geode_additions")
        );

        modification
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(ModBiomeTags.HAS_EMERALD_GEODE),
                        modifier(
                                ModPlacedFeatures.EMERALD_GEODE,
                                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                MoreGeodes.CONFIG.generateEmeraldGeodes()
                        )
                )
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(ModBiomeTags.HAS_EXTRA_EMERALD_GEODES),
                        modifier(
                                ModPlacedFeatures.EMERALD_GEODE_EXTRA,
                                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                MoreGeodes.CONFIG.generateEmeraldGeodes()
                        )
                )
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(ModBiomeTags.HAS_QUARTZ_GEODE),
                        modifier(
                                ModPlacedFeatures.QUARTZ_GEODE,
                                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                MoreGeodes.CONFIG.generateQuartzGeodes()
                        )
                )
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(ModBiomeTags.HAS_DIAMOND_GEODE),
                        modifier(
                                ModPlacedFeatures.DIAMOND_GEODE,
                                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                MoreGeodes.CONFIG.generateDiamondGeodes()
                        )
                )
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(ModBiomeTags.HAS_ECHO_GEODE),
                        modifier(
                                ModPlacedFeatures.ECHO_GEODE,
                                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                MoreGeodes.CONFIG.generateEchoGeodes()
                        )
                )
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(ModBiomeTags.HAS_LAPIS_GEODE),
                        modifier(
                                ModPlacedFeatures.LAPIS_GEODE,
                                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                MoreGeodes.CONFIG.generateLapisGeodes()
                        )
                )
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(ModBiomeTags.HAS_EXTRA_LAPIS_GEODES),
                        modifier(
                                ModPlacedFeatures.LAPIS_GEODE_EXTRA,
                                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                MoreGeodes.CONFIG.generateLapisGeodes()
                        )
                )
                .add(
                        ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(ModBiomeTags.HAS_GYPSUM_PATCH),
                        modifier(
                                ModPlacedFeatures.GYPSUM_PATCH,
                                GenerationStep.Decoration.VEGETAL_DECORATION,
                                MoreGeodes.CONFIG.generateGypsumPatches()
                        )
                );

        if (MoreGeodes.isAE2Loaded()) {
            modification.add(
                    ModificationPhase.ADDITIONS,
                    BiomeSelectors.tag(ModBiomeTags.HAS_CERTUS_GEODE),
                    modifier(
                            ModPlacedFeatures.CERTUS_GEODE,
                            GenerationStep.Decoration.UNDERGROUND_DECORATION,
                            MoreGeodes.CONFIG.generateCertusGeodes()
                    )
            );
        }

        if (MoreGeodes.isSpectrumLoaded()) {
            modification.add(
                    ModificationPhase.ADDITIONS,
                    BiomeSelectors.tag(ModBiomeTags.HAS_BISMUTH_GEODE),
                    modifier(
                            ModPlacedFeatures.BISMUTH_GEODE,
                            GenerationStep.Decoration.VEGETAL_DECORATION,
                            MoreGeodes.CONFIG.generateBismuthGeodes()
                    )
            );
        }
    }

    private static BiConsumer<BiomeSelectionContext, BiomeModificationContext> modifier(
            ResourceKey<PlacedFeature> feature,
            GenerationStep.Decoration step,
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
