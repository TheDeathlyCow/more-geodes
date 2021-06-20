package com.github.thedeathlycow.moregeodes.features;

import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class ModConfiguredFeatures {

    private static final GeodeLayerConfig emeraldGeodeLayerConfig = new GeodeLayerConfig(
            new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()),
            new SimpleBlockStateProvider(ModBlocks.EMERALD_GEODE.getDefaultState()),
            new SimpleBlockStateProvider(ModBlocks.BUDDING_EMERALD.getDefaultState()),
            new SimpleBlockStateProvider(Blocks.CALCITE.getDefaultState()),
            new SimpleBlockStateProvider(Blocks.SMOOTH_BASALT.getDefaultState()),
            ImmutableList.of(
                    ModBlocks.SMALL_EMERALD_BUD.getDefaultState(),
                    ModBlocks.MEDIUM_EMERALD_BUD.getDefaultState(),
                    ModBlocks.LARGE_EMERALD_BUD.getDefaultState(),
                    ModBlocks.EMERALD_CLUSTER.getDefaultState()
            ),
            BlockTags.FEATURES_CANNOT_REPLACE.getId(),
            BlockTags.GEODE_INVALID_BLOCKS.getId()
    );

    private static final GeodeFeatureConfig emeraldGeodeFeatureConfig = new GeodeFeatureConfig(
            emeraldGeodeLayerConfig,
            new GeodeLayerThicknessConfig(1.7D, 2.2D, 3.2D, 4.2D),
            new GeodeCrackConfig(0.95D, 2.0D, 2),
            0.35D, 0.083D, true,
            UniformIntProvider.create(4, 6),
            UniformIntProvider.create(3, 4),
            UniformIntProvider.create(1, 2),
            -16, 16, 0.05D, 1
    );

    public static final ConfiguredFeature<?, ?> EMERALD_GEODE = ModFeatures.EMERALD_GEODE
            .configure(emeraldGeodeFeatureConfig)
            .uniformRange(YOffset.aboveBottom(6), YOffset.fixed(46))
            .spreadHorizontally()
            .applyChance(53);


    private static void register(String name, ConfiguredFeature<?, ?> configuredFeature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, name, configuredFeature);
    }
}
