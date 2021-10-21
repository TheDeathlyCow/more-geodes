package com.github.thedeathlycow.moregeodes;

import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import com.github.thedeathlycow.moregeodes.features.ModFeatures;
import com.github.thedeathlycow.moregeodes.item.ModItems;

import net.fabricmc.api.DedicatedServerModInitializer;

public class MoreGeodesDedicatedServer implements DedicatedServerModInitializer {

    public static String MODID = "geodes";

    @Override
    public void onInitializeServer() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        System.out.println("Registering More Geodes on server...");
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        //ModBlocks.registerCutouts();
        ModFeatures.registerFeatures();
        System.out.println("More Geodes registered on server!");
    }
}