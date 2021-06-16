package com.github.thedeathlycow.gen.features;

import com.github.thedeathlycow.MoreGeodes;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;

public class ModFeatures {

    public static Feature<GeodeFeatureConfig> EMERALD_GEODE = new GeodeFeature(GeodeFeatureConfig.CODEC);

    public static void registerFeatures() {
        register("emerald_geode", EMERALD_GEODE);
        RegistryKey<ConfiguredFeature<?, ?>> emeraldGeode = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MoreGeodes.MODID, "emerald_geode"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, emeraldGeode.getValue(), ModConfiguredFeatures.EMERALD_GEODE);

        BiomeModifications.addFeature(
                BiomeSelectors.categories(Biome.Category.EXTREME_HILLS),
                GenerationStep.Feature.UNDERGROUND_ORES,
                emeraldGeode
        );
    }


    private static void register(String name, Feature<?> feature) {
        Registry.register(Registry.FEATURE, name, feature);
    }
}
