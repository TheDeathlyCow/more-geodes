package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModBlocks {

    public static Block EMERALD_GEODE = new EmeraldGeodeBlock(FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).strength(1.5F).sounds(BlockSoundGroup.AMETHYST_BLOCK).breakByTool(FabricToolTags.PICKAXES).requiresTool());

    public static Block BUDDING_EMERALD = new BuddingEmeraldBlock(FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).ticksRandomly().strength(1.5F).sounds(BlockSoundGroup.AMETHYST_BLOCK).breakByTool(FabricToolTags.PICKAXES).requiresTool());

    public static Block EMERALD_CLUSTER = new AmethystClusterBlock(7, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool().ticksRandomly().sounds(BlockSoundGroup.AMETHYST_CLUSTER).strength(1.5f).luminance((blockState) -> 5));

    public static Block LARGE_EMERALD_BUD = new AmethystClusterBlock(5, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool().ticksRandomly().sounds(BlockSoundGroup.AMETHYST_CLUSTER).strength(1.5f).luminance((blockState) -> 4));

    public static Block MEDIUM_EMERALD_BUD = new AmethystClusterBlock(4, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool().ticksRandomly().sounds(BlockSoundGroup.AMETHYST_CLUSTER).strength(1.5f).luminance((blockState) -> 2));

    public static Block SMALL_EMERALD_BUD = new AmethystClusterBlock(3, 4, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool().ticksRandomly().sounds(BlockSoundGroup.AMETHYST_CLUSTER).strength(1.5f).luminance((blockState) -> 1));

    public static void registerBlocks() {
        register("emerald_geode", EMERALD_GEODE);
        register("budding_emerald", BUDDING_EMERALD);
        register("emerald_cluster", EMERALD_CLUSTER);
        register("large_emerald_bud", LARGE_EMERALD_BUD);
        register("medium_emerald_bud", MEDIUM_EMERALD_BUD);
        register("small_emerald_bud", SMALL_EMERALD_BUD);
    }

    public static void registerCutouts() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EMERALD_CLUSTER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LARGE_EMERALD_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MEDIUM_EMERALD_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SMALL_EMERALD_BUD, RenderLayer.getCutout());
    }

    private static void register(String name, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(MoreGeodes.MODID, name), block);
    }

}
