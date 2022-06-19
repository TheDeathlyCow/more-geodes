package com.github.thedeathlycow.moregeodes.features;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.List;

public class ModConfiguredFeatures {

    public static final RegistryEntry<ConfiguredFeature<?, ?>> EMERALD_GEODE;
    public static final RegistryEntry<ConfiguredFeature<?, ?>> QUARTZ_GEODE;
    public static final RegistryEntry<ConfiguredFeature<?, ?>> DIAMOND_GEODE;
    public static final RegistryEntry<ConfiguredFeature<?, ?>> ECHO_GEODE;

    private static RegistryEntry<ConfiguredFeature<?, ?>> register(String id, ConfiguredFeature<?, ?> configuredFeature) {
        return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MoreGeodes.MODID, id), configuredFeature);
    }

    static {
        EMERALD_GEODE = register("emerald_geode", new ConfiguredFeature<>(Feature.GEODE, new GeodeFeatureConfig
                (
                        new GeodeLayerConfig
                                (
                                        SimpleBlockStateProvider.of(Blocks.AIR.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.EMERALD_GEODE.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.BUDDING_EMERALD.getDefaultState()),
                                        SimpleBlockStateProvider.of(Blocks.CALCITE.getDefaultState()),
                                        SimpleBlockStateProvider.of(Blocks.SMOOTH_BASALT.getDefaultState()),
                                        ModBlocks.BUDDING_EMERALD.getClusterStates(),
                                        BlockTags.FEATURES_CANNOT_REPLACE,
                                        BlockTags.GEODE_INVALID_BLOCKS
                                ),
                        new GeodeLayerThicknessConfig(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackConfig(0.95D, 2.0D, 2),
                        0.35D, 0.083D, true,
                        UniformIntProvider.create(4, 6),
                        UniformIntProvider.create(3, 4),
                        UniformIntProvider.create(1, 2),
                        -16, 16, 0.05D, 1
                )
        ));

        QUARTZ_GEODE = register("quartz_geode", new ConfiguredFeature<>(Feature.GEODE, new GeodeFeatureConfig
                (
                        new GeodeLayerConfig
                                (
                                        SimpleBlockStateProvider.of(Blocks.AIR.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.QUARTZ_GEODE.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.BUDDING_QUARTZ.getDefaultState()),
                                        SimpleBlockStateProvider.of(Blocks.TUFF.getDefaultState()),
                                        SimpleBlockStateProvider.of(Blocks.SMOOTH_BASALT.getDefaultState()),
                                        ModBlocks.BUDDING_QUARTZ.getClusterStates(),
                                        BlockTags.FEATURES_CANNOT_REPLACE,
                                        BlockTags.GEODE_INVALID_BLOCKS
                                ),
                        new GeodeLayerThicknessConfig(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackConfig(0.95D, 2.0D, 2),
                        0.35D, 0.083D, true,
                        UniformIntProvider.create(4, 6),
                        UniformIntProvider.create(3, 4),
                        UniformIntProvider.create(1, 2),
                        -16, 16, 0.05D, 1
                )
        ));

        BlockStateProvider middleLayer = new WeightedBlockStateProvider(
                DataPool.<BlockState>builder()
                        .add(Blocks.DEEPSLATE_COAL_ORE.getDefaultState(), 3)
                        .add(Blocks.SMOOTH_BASALT.getDefaultState(), 1)
                        .build()
        );

        DIAMOND_GEODE = register("diamond_geode", new ConfiguredFeature<>(Feature.GEODE, new GeodeFeatureConfig
                (
                        new GeodeLayerConfig
                                (
                                        SimpleBlockStateProvider.of(Blocks.WATER.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.DIAMOND_GEODE.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.DIAMOND_GEODE.getDefaultState()),
                                        middleLayer,
                                        SimpleBlockStateProvider.of(Blocks.SMOOTH_BASALT.getDefaultState()),
                                        List.of(ModBlocks.DIAMOND_CLUSTER.getDefaultState(), ModBlocks.DIAMOND_CLUSTER.getDefaultState()),
                                        BlockTags.FEATURES_CANNOT_REPLACE,
                                        BlockTags.GEODE_INVALID_BLOCKS
                                ),
                        new GeodeLayerThicknessConfig(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackConfig(0.0D, 1.0D, 2),
                        0.35D, 0.083D, true,
                        UniformIntProvider.create(4, 6),
                        UniformIntProvider.create(3, 4),
                        UniformIntProvider.create(1, 2),
                        -16, 16, 0.05D, 1
                )
        ));

        ECHO_GEODE = register("echo_geode", new ConfiguredFeature<>(Feature.GEODE, new GeodeFeatureConfig
                (
                        new GeodeLayerConfig
                                (
                                        SimpleBlockStateProvider.of(Blocks.AIR.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.ECHO_BLOCK.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.BUDDING_ECHO_BLOCK.getDefaultState()),
                                        SimpleBlockStateProvider.of(Blocks.SCULK.getDefaultState()),
                                        SimpleBlockStateProvider.of(Blocks.BLACKSTONE.getDefaultState()),
                                        ModBlocks.BUDDING_ECHO_BLOCK.getClusterStates(),
                                        BlockTags.FEATURES_CANNOT_REPLACE,
                                        BlockTags.GEODE_INVALID_BLOCKS
                                ),
                        new GeodeLayerThicknessConfig(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackConfig(0.95D, 2.0D, 2),
                        0.35D, 0.083D, true,
                        UniformIntProvider.create(4, 6),
                        UniformIntProvider.create(3, 4),
                        UniformIntProvider.create(1, 2),
                        -16, 16, 0.05D, 1
                )
        ));
    }

}
