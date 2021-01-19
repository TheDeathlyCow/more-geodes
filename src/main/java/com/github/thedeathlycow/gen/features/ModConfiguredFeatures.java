package com.github.thedeathlycow.gen.features;

import com.github.thedeathlycow.blocks.ModBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class ModConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> EMERALD_GEODE = ModFeatures.EMERALD_GEODE.configure(new GeodeFeatureConfig(new GeodeLayerConfig(new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()), new SimpleBlockStateProvider(ModBlocks.EMERALD_GEODE.getDefaultState()), new SimpleBlockStateProvider(ModBlocks.BUDDING_EMERALD.getDefaultState()), new SimpleBlockStateProvider(Blocks.CALCITE.getDefaultState()), new SimpleBlockStateProvider(Blocks.TUFF.getDefaultState()), ImmutableList.of(ModBlocks.SMALL_EMERALD_BUD.getDefaultState(), ModBlocks.MEDIUM_EMERALD_BUD.getDefaultState(), ModBlocks.LARGE_EMERALD_BUD.getDefaultState(), ModBlocks.EMERALD_CLUSTER.getDefaultState())), new GeodeLayerThicknessConfig(1.7d, 2.2d, 3.2d, 4.2d), new GeodeCrackConfig(0.95D, 2.0D, 2), 0.35D, 0.083D, true, 4, 7, 3, 5, 1, 3, -16, 16, 0.05D)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(6, 0, 47))).spreadHorizontally().applyChance(48);


    private static void register(String name, ConfiguredFeature<?, ?> configuredFeature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, name, configuredFeature);
    }
}
