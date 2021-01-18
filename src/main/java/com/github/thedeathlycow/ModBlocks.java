package com.github.thedeathlycow;

import com.github.thedeathlycow.blocks.EmeraldGeodeBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModBlocks {

    public static Block EMERALD_GEODE = new EmeraldGeodeBlock(FabricBlockSettings .of(Material.AMETHYST, MapColor.EMERALD_GREEN).strength(1.5F).sounds(BlockSoundGroup.AMETHYST_BLOCK).breakByTool(FabricToolTags.PICKAXES).requiresTool());
    public static Block BUDDING_EMERALD = new Block(FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN));

    public static void registerBlocks() {
        register("emerald_geode", EMERALD_GEODE);
        register("budding_emerald", BUDDING_EMERALD);
    }

    private static void register(String name, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(MoreGeodes.MODID, name), block);
    }

}
