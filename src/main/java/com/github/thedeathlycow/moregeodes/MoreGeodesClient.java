package com.github.thedeathlycow.moregeodes;

import com.github.thedeathlycow.moregeodes.blocks.Cutouts;
import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import com.github.thedeathlycow.moregeodes.features.ModFeatures;
import com.github.thedeathlycow.moregeodes.item.ModItems;
import net.fabricmc.api.ClientModInitializer;

public class MoreGeodesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MoreGeodes.LOGGER.info("Beginning More Geodes client initialization");
        Cutouts.registerCutouts();
        MoreGeodes.LOGGER.info("More Geodes client initialized!");
    }
}