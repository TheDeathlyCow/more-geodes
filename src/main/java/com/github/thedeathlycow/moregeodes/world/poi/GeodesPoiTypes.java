package com.github.thedeathlycow.moregeodes.world.poi;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

public class GeodesPoiTypes {

    public static final PointOfInterestType EMERALD_CRYSTAL = PointOfInterestHelper.register(
            new Identifier(MoreGeodes.MODID, "emerald_crystal"),
            0, 30,
            ModBlocks.EMERALD_CLUSTER, ModBlocks.LARGE_EMERALD_BUD, ModBlocks.MEDIUM_EMERALD_BUD, ModBlocks.SMALL_EMERALD_BUD
    );

    public static final PointOfInterestType AMETHYST_CRYSTAL = PointOfInterestHelper.register(
            new Identifier(MoreGeodes.MODID, "amethyst_crystal"),
            0, 30, Blocks.AMETHYST_CLUSTER, Blocks.LARGE_AMETHYST_BUD, Blocks.MEDIUM_AMETHYST_BUD, Blocks.SMALL_AMETHYST_BUD
    );

    public static final PointOfInterestType QUARTZ_CRYSTAL = PointOfInterestHelper.register(
            new Identifier(MoreGeodes.MODID, "quartz_crystal"),
            0, 30, ModBlocks.QUARTZ_CLUSTER, ModBlocks.LARGE_QUARTZ_BUD, ModBlocks.MEDIUM_QUARTZ_BUD, ModBlocks.SMALL_QUARTZ_BUD
    );

    public static final PointOfInterestType DIAMOND_CRYSTAL = PointOfInterestHelper.register(
            new Identifier(MoreGeodes.MODID, "diamond_crystal"),
            0, 30,
            ModBlocks.DIAMOND_CLUSTER
    );

    public static final PointOfInterestType ECHO_CRYSTAL = PointOfInterestHelper.register(
            new Identifier(MoreGeodes.MODID, "echo_crystal"),
            0, 30,
            ModBlocks.ECHO_CLUSTER, ModBlocks.LARGE_ECHO_BUD, ModBlocks.MEDIUM_ECHO_BUD, ModBlocks.SMALL_ECHO_BUD
    );
}
