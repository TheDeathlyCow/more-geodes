package com.github.thedeathlycow;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static Block EMERALD_GEODE = new Block(FabricBlockSettings.of(Material.AMETHYST));
    public static Block BUDDING_EMERALD = new Block(FabricBlockSettings.of(Material.AMETHYST));

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(MoreGeodes.MODID, "emerald_geode"), EMERALD_GEODE);
        Registry.register(Registry.BLOCK, new Identifier(MoreGeodes.MODID, "budding_emerald"), BUDDING_EMERALD);
    }

}
