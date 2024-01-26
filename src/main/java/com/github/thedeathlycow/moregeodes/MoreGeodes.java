package com.github.thedeathlycow.moregeodes;

import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import com.github.thedeathlycow.moregeodes.blocks.entity.ModBlockEntities;
import com.github.thedeathlycow.moregeodes.config.GeodesConfig;
import com.github.thedeathlycow.moregeodes.entity.GeodesEntityTypes;
import com.github.thedeathlycow.moregeodes.entity.GeodesMemoryModules;
import com.github.thedeathlycow.moregeodes.features.ModFeatures;
import com.github.thedeathlycow.moregeodes.item.ModItems;
import com.github.thedeathlycow.moregeodes.item.recipe.GeodesRecipeSerializers;
import com.github.thedeathlycow.moregeodes.item.tuning.Tunings;
import com.github.thedeathlycow.moregeodes.sounds.GeodesSoundEvents;
import com.github.thedeathlycow.moregeodes.world.GeodesGameEvents;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;

public class MoreGeodes implements ModInitializer {

    public static final String MODID = "geodes";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final GeodesConfig CONFIG = new GeodesConfig();

    @Override
    public void onInitialize() {
        LOGGER.info("Beginning More Geodes initialization");
        CONFIG.loadConfig();

        ModBlocks.registerBlocks();
        ModItems.registerItems();
        ModFeatures.placeFeaturesInBiomes();
        ModBlockEntities.registerBlockEntities();
        GeodesEntityTypes.registerAll();
        GeodesSoundEvents.registerSoundEvents();
        GeodesGameEvents.registerEvents();
        GeodesMemoryModules.registerModules();
        GeodesRecipeSerializers.registerAll();
        Tunings.onInitialize();

        LOGGER.info("More Geodes initialized!");
    }

    public static boolean isCarpetLoaded() {
        return FabricLoader.getInstance().isModLoaded("carpet");
    }

    public static boolean isAE2Loaded() {
        return FabricLoader.getInstance().isModLoaded("ae2");
    }

    public static boolean isSpectrumLoaded() {
        return FabricLoader.getInstance().isModLoaded("spectrum");
    }
}