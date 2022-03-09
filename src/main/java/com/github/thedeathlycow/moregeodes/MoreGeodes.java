package com.github.thedeathlycow.moregeodes;

import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import com.github.thedeathlycow.moregeodes.features.ModFeatures;
import com.github.thedeathlycow.moregeodes.item.ModItems;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.DedicatedServerModInitializer;
import org.slf4j.Logger;

public class MoreGeodes implements DedicatedServerModInitializer {

    public static final String MODID = "geodes";
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitializeServer() {
        LOGGER.info("Beginning More Geodes dedicated server initialization");
        ModBlocks.registerBlocks();
        ModItems.registerItems();
        ModFeatures.placedFeaturesInBiomes();
        LOGGER.info("More Geodes dedicated server initialized!");
    }
}