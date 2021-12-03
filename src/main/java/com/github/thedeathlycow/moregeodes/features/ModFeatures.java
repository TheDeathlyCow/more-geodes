package com.github.thedeathlycow.moregeodes.features;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;

public class ModFeatures {

    public static final CustomGeode EMERALD_GEODE = registerFeature("emerald_geode", new CustomGeode(
            ModBlocks.BUDDING_EMERALD,
            ModBlocks.EMERALD_GEODE
    ));

    public static CustomGeode QUARTZ_GEODE = new CustomGeode(
            ModBlocks.BUDDING_QUARTZ,
            ModBlocks.QUARTZ_GEODE,
            Blocks.TUFF,
            Blocks.SMOOTH_BASALT
    );


    public static void registerFeatures() {

        ConfiguredFeature<?, ?> emeraldGeode = registerConfiguredFeature(
                "emerald_geode",
                EMERALD_GEODE.configure(EMERALD_GEODE.getFeatureConfig())
        );

        PlacedFeature placedEmeraldGeode = registerPlacedFeature("emerald_geode", emeraldGeode
                .withPlacement(
                        RarityFilterPlacementModifier.of(24),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-16), YOffset.fixed(256)),
                        BiomePlacementModifier.of()
                ));

        BiomeModifications.addFeature(
                BiomeSelectors.categories(Biome.Category.MOUNTAIN),
                GenerationStep.Feature.UNDERGROUND_ORES,
                BuiltinRegistries.PLACED_FEATURE.getKey(placedEmeraldGeode).get()
        );


//        register("quartz_geode", QUARTZ_GEODE);
//        RegistryKey<ConfiguredFeature<?, ?>> quartzGeode = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MoreGeodesDedicatedServer.MODID, "quartz_geode"));
//        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, quartzGeode.getValue(), ModConfiguredFeatures.QUARTZ_GEODE);
//
//        BiomeModifications.addFeature(
//                BiomeSelectors.categories(Biome.Category.NETHER),
//                GenerationStep.Feature.UNDERGROUND_ORES,
//                quartzGeode
//        );
    }


    private static <C extends FeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature) {
        return register(Registry.FEATURE, name, feature);
    }

    private static <C extends ConfiguredFeature<?, ?>> C registerConfiguredFeature(String name, C cFeature) {
        return register(BuiltinRegistries.CONFIGURED_FEATURE, name, cFeature);
    }

    private static <P extends PlacedFeature> P registerPlacedFeature(String name, P pFeature) {
        return register(BuiltinRegistries.PLACED_FEATURE, name, pFeature);
    }

    private static <V, T extends V> T register(Registry<V> registry, String name, T toRegister) {
        return Registry.register(registry, new Identifier(MoreGeodes.MODID, name), toRegister);
    }
}
