package com.github.thedeathlycow.moregeodes.features;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> EMERALD_GEODE = of("emerald_geode");
    public static final ResourceKey<PlacedFeature> EMERALD_GEODE_EXTRA = of("emerald_geode_extra");
    public static final ResourceKey<PlacedFeature> QUARTZ_GEODE = of("quartz_geode");
    public static final ResourceKey<PlacedFeature> DIAMOND_GEODE = of("diamond_geode");
    public static final ResourceKey<PlacedFeature> ECHO_GEODE = of("echo_geode");

    public static final ResourceKey<PlacedFeature> LAPIS_GEODE = of("lapis_geode");
    public static final ResourceKey<PlacedFeature> LAPIS_GEODE_EXTRA = of("lapis_geode_extra");
    public static final ResourceKey<PlacedFeature> GYPSUM_PATCH = of("gypsum_patch");
    public static final ResourceKey<PlacedFeature> CERTUS_GEODE = of("certus_geode");
    public static final ResourceKey<PlacedFeature> BISMUTH_GEODE = of("bismuth_geode");

    public static ResourceKey<PlacedFeature> of(String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, id));
    }

}
