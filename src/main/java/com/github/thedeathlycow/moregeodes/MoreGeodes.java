package com.github.thedeathlycow.moregeodes;

import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import com.github.thedeathlycow.moregeodes.features.ModFeatures;
import com.github.thedeathlycow.moregeodes.item.ModItems;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.minecraft.block.Blocks;

import java.util.logging.Logger;

public class MoreGeodes implements DedicatedServerModInitializer {

    public static String MODID = "geodes";

    @Override
    public void onInitializeServer() {
        System.out.println("Registering More Geodes on server...");
        ModBlocks.registerBlocks();
        ModItems.registerItems();
        ModFeatures.placedFeaturesInBiomes();
        System.out.println("More Geodes registered on server!");
    }
}