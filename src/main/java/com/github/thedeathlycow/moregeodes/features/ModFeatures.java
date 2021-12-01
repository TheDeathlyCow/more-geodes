package com.github.thedeathlycow.moregeodes.features;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;

public class ModFeatures {


    public static void registerFeatures() {
    }


    private static void register(String name, Feature<?> feature) {
        Registry.register(Registry.FEATURE, name, feature);
    }
}
