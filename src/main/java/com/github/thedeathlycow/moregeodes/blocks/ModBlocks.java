package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.MoreGeodesDedicatedServer;
import com.github.thedeathlycow.moregeodes.sounds.ModBlockSoundGroups;
import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModBlocks {

    public static Block EMERALD_GEODE = new AmethystBlock(FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).strength(1.5F).sounds(ModBlockSoundGroups.EMERALD_GEODE).breakByTool(FabricToolTags.PICKAXES).requiresTool());

    public static AmethystClusterBlock EMERALD_CLUSTER = new AmethystClusterBlock(7, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool().ticksRandomly().sounds(ModBlockSoundGroups.EMERALD_CLUSTER).strength(1.5f).luminance((blockState) -> 5));
    public static AmethystClusterBlock LARGE_EMERALD_BUD = new AmethystClusterBlock(5, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool().ticksRandomly().sounds(ModBlockSoundGroups.LARGE_EMERALD_BUD).strength(1.5f).luminance((blockState) -> 4));
    public static AmethystClusterBlock MEDIUM_EMERALD_BUD = new AmethystClusterBlock(4, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool().ticksRandomly().sounds(ModBlockSoundGroups.MEDIUM_EMERALD_BUD).strength(1.5f).luminance((blockState) -> 2));
    public static AmethystClusterBlock SMALL_EMERALD_BUD = new AmethystClusterBlock(3, 4, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool().ticksRandomly().sounds(ModBlockSoundGroups.SMALL_EMERALD_BUD).strength(1.5f).luminance((blockState) -> 1));

    public static Block QUARTZ_GEODE = new AmethystBlock(FabricBlockSettings.of(Material.AMETHYST, MapColor.IRON_GRAY).strength(1.5F).sounds(ModBlockSoundGroups.QUARTZ_GEODE).breakByTool(FabricToolTags.PICKAXES).requiresTool());

    public static QuartzClusterBlock QUARTZ_CLUSTER = new QuartzClusterBlock(7, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.IRON_GRAY).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool().ticksRandomly().sounds(ModBlockSoundGroups.QUARTZ_CLUSTER).strength(1.5f).luminance((blockState) -> 5), 15, 5);
    public static QuartzClusterBlock LARGE_QUARTZ_BUD = new QuartzClusterBlock(5, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.IRON_GRAY).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool().ticksRandomly().sounds(ModBlockSoundGroups.LARGE_QUARTZ_BUD).strength(1.5f).luminance((blockState) -> 4), 10, 10);
    public static QuartzClusterBlock MEDIUM_QUARTZ_BUD = new QuartzClusterBlock(4, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.IRON_GRAY).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool().ticksRandomly().sounds(ModBlockSoundGroups.MEDIUM_QUARTZ_BUD).strength(1.5f).luminance((blockState) -> 2), 5, 20);
    public static QuartzClusterBlock SMALL_QUARTZ_BUD = new QuartzClusterBlock(3, 4, FabricBlockSettings.of(Material.AMETHYST, MapColor.IRON_GRAY).nonOpaque().breakByTool(FabricToolTags.PICKAXES).requiresTool().ticksRandomly().sounds(ModBlockSoundGroups.SMALL_QUARTZ_BUD).strength(1.5f).luminance((blockState) -> 1), 2, 40);

    public static CustomBuddingBlock BUDDING_EMERALD = new CustomBuddingBlock(
            FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).ticksRandomly().strength(1.5F).sounds(ModBlockSoundGroups.EMERALD_GEODE).breakByTool(FabricToolTags.PICKAXES).requiresTool(),
            ImmutableList.of(SMALL_EMERALD_BUD, MEDIUM_EMERALD_BUD, LARGE_EMERALD_BUD, EMERALD_CLUSTER)
    );

    public static CustomBuddingBlock BUDDING_QUARTZ = new CustomBuddingBlock(
            FabricBlockSettings.of(Material.AMETHYST, MapColor.IRON_GRAY).ticksRandomly().strength(1.5f).sounds(ModBlockSoundGroups.QUARTZ_GEODE).breakByTool(FabricToolTags.PICKAXES).requiresTool(),
            ImmutableList.of(SMALL_QUARTZ_BUD, MEDIUM_QUARTZ_BUD, LARGE_QUARTZ_BUD, QUARTZ_CLUSTER)
    );

    public static void registerBlocks() {
        register("emerald_geode", EMERALD_GEODE);
        register("budding_emerald", BUDDING_EMERALD);
        register("emerald_cluster", EMERALD_CLUSTER);
        register("large_emerald_bud", LARGE_EMERALD_BUD);
        register("medium_emerald_bud", MEDIUM_EMERALD_BUD);
        register("small_emerald_bud", SMALL_EMERALD_BUD);
        register("quartz_geode", QUARTZ_GEODE);
        register("budding_quartz", BUDDING_QUARTZ);
        register("quartz_cluster", QUARTZ_CLUSTER);
        register("large_quartz_bud", LARGE_QUARTZ_BUD);
        register("medium_quartz_bud", MEDIUM_QUARTZ_BUD);
        register("small_quartz_bud", SMALL_QUARTZ_BUD);
    }

    public static void registerCutouts() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EMERALD_CLUSTER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LARGE_EMERALD_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MEDIUM_EMERALD_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SMALL_EMERALD_BUD, RenderLayer.getCutout());
    }

    private static void register(String name, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(MoreGeodesDedicatedServer.MODID, name), block);
    }

}
