package com.github.thedeathlycow.moregeodes;

import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import com.github.thedeathlycow.moregeodes.features.ModFeatures;
import com.github.thedeathlycow.moregeodes.item.ModItems;
import net.fabricmc.api.ModInitializer;

public class MoreGeodes implements ModInitializer {

    public static String MODID = "geodes";

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        System.out.println("Registering More Geodes...");
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        ModBlocks.registerCutouts();
        ModFeatures.registerFeatures();
        System.out.println("More Geodes registered!");
    }
}
