package com.github.thedeathlycow.moregeodes.features;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.Feature;

public class ModConfiguredFeatures {

    public static final ResourceKey<Feature> EMERALD_GEODE = of("emerald_geode");
    public static final ResourceKey<Feature> QUARTZ_GEODE = of("quartz_geode");
    public static final ResourceKey<Feature> DIAMOND_GEODE = of("diamond_geode");
    public static final ResourceKey<Feature> ECHO_GEODE = of("echo_geode");
    public static final ResourceKey<Feature> LAPIS_GEODE = of("lapis_geode");

    public static final ResourceKey<Feature> GYPSUM_PATCH = of("gypsum_patch");


    public static ResourceKey<Feature> of(String id) {
        return ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, id));
    }

}
