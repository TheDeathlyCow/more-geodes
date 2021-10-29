package com.github.thedeathlycow.moregeodes.features;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class ModConfiguredFeatures {


    public static final ConfiguredFeature<?, ?> EMERALD_GEODE = ModFeatures.EMERALD_GEODE
            .configure(ModFeatures.EMERALD_GEODE.getFeatureConfig())
            .uniformRange(YOffset.aboveBottom(6), YOffset.fixed(46))
            .spreadHorizontally()
            .applyChance(53);

    public static final ConfiguredFeature<?, ?> QUARTZ_GEODE = ModFeatures.QUARTZ_GEODE
            .configure(ModFeatures.QUARTZ_GEODE.getFeatureConfig())
            .uniformRange(YOffset.aboveBottom(12), YOffset.fixed(80))
            .spreadHorizontally()
            .applyChance(53);

    private static void register(String name, ConfiguredFeature<?, ?> configuredFeature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, name, configuredFeature);
    }
}
