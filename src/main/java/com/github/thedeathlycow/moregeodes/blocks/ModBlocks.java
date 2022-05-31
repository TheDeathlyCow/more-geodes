package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.sounds.ModBlockSoundGroups;
import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModBlocks {

    public static final AmethystBlock EMERALD_GEODE = new AmethystBlock(FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).strength(1.5F).sounds(ModBlockSoundGroups.EMERALD_GEODE).requiresTool());

    public static final AmethystClusterBlock EMERALD_CLUSTER = new AmethystClusterBlock(7, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.EMERALD_CLUSTER).strength(1.5f).luminance((blockState) -> 5));
    public static final AmethystClusterBlock LARGE_EMERALD_BUD = new AmethystClusterBlock(5, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.LARGE_EMERALD_BUD).strength(1.5f).luminance((blockState) -> 4));
    public static final AmethystClusterBlock MEDIUM_EMERALD_BUD = new AmethystClusterBlock(4, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.MEDIUM_EMERALD_BUD).strength(1.5f).luminance((blockState) -> 2));
    public static final AmethystClusterBlock SMALL_EMERALD_BUD = new AmethystClusterBlock(3, 4, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.SMALL_EMERALD_BUD).strength(1.5f).luminance((blockState) -> 1));

    public static final AmethystBlock QUARTZ_GEODE = new AmethystBlock(FabricBlockSettings.of(Material.AMETHYST, MapColor.OFF_WHITE).strength(1.5F).sounds(ModBlockSoundGroups.QUARTZ_GEODE).requiresTool());

    public static final QuartzClusterBlock QUARTZ_CLUSTER = new QuartzClusterBlock(7, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.OFF_WHITE).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.QUARTZ_CLUSTER).strength(1.5f).luminance((blockState) -> 5), 15, 1);
    public static final QuartzClusterBlock LARGE_QUARTZ_BUD = new QuartzClusterBlock(5, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.OFF_WHITE).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.LARGE_QUARTZ_BUD).strength(1.5f).luminance((blockState) -> 4), 15, 5);
    public static final QuartzClusterBlock MEDIUM_QUARTZ_BUD = new QuartzClusterBlock(4, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.OFF_WHITE).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.MEDIUM_QUARTZ_BUD).strength(1.5f).luminance((blockState) -> 2), 15, 10);
    public static final QuartzClusterBlock SMALL_QUARTZ_BUD = new QuartzClusterBlock(3, 4, FabricBlockSettings.of(Material.AMETHYST, MapColor.OFF_WHITE).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.SMALL_QUARTZ_BUD).strength(1.5f).luminance((blockState) -> 1), 15, 20);

    public static final AmethystBlock DIAMOND_GEODE = new AmethystBlock(FabricBlockSettings.of(Material.AMETHYST, MapColor.DIAMOND_BLUE).strength(1.5F).sounds(ModBlockSoundGroups.DIAMOND_GEODE).requiresTool());

    public static final AmethystClusterBlock DIAMOND_CLUSTER = new AmethystClusterBlock(7, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.DIAMOND_BLUE).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.DIAMOND_CLUSTER).strength(1.5f).luminance((blockState) -> 7));

    public static final AmethystBlock ECHO_BLOCK = new AmethystBlock(FabricBlockSettings.of(Material.AMETHYST, MapColor.BLACK).strength(1.5f).sounds(BlockSoundGroup.SCULK_SHRIEKER).requiresTool());

    public static final AmethystClusterBlock ECHO_CLUSTER = new AmethystClusterBlock(7, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.BLACK).nonOpaque().requiresTool().sounds(BlockSoundGroup.SCULK_SHRIEKER).strength(1.5f).luminance((blockState) -> 5));
    public static final AmethystClusterBlock LARGE_ECHO_BUD = new AmethystClusterBlock(5, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.BLACK).nonOpaque().requiresTool().sounds(BlockSoundGroup.SCULK_SHRIEKER).strength(1.5f).luminance((blockState) -> 4));
    public static final AmethystClusterBlock MEDIUM_ECHO_BUD = new AmethystClusterBlock(4, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.BLACK).nonOpaque().requiresTool().sounds(BlockSoundGroup.SCULK_SHRIEKER).strength(1.5f).luminance((blockState) -> 2));
    public static final AmethystClusterBlock SMALL_ECHO_BUD = new AmethystClusterBlock(3, 4, FabricBlockSettings.of(Material.AMETHYST, MapColor.BLACK).nonOpaque().requiresTool().sounds(BlockSoundGroup.SCULK_SHRIEKER).strength(1.5f).luminance((blockState) -> 1));

    public static final Block EMERALD_CRYSTAL_ECHO_LOCATOR = new EchoLocatorBlock(EchoLocatorType.EMERALD_GEODE_LOCATOR, FabricBlockSettings.of(Material.STONE).nonOpaque());
    public static final Block QUARTZ_CRYSTAL_ECHO_LOCATOR = new EchoLocatorBlock(EchoLocatorType.QUARTZ_GEODE_LOCATOR, FabricBlockSettings.of(Material.STONE).nonOpaque());
    public static final Block AMETHYST_CRYSTAL_ECHO_LOCATOR = new EchoLocatorBlock(EchoLocatorType.AMETHYST_GEODE_LOCATOR, FabricBlockSettings.of(Material.STONE).nonOpaque());
    public static final Block DIAMOND_CRYSTAL_ECHO_LOCATOR = new EchoLocatorBlock(EchoLocatorType.DIAMOND_GEODE_LOCATOR, FabricBlockSettings.of(Material.STONE).nonOpaque());
    public static final Block ECHO_CRYSTAL_ECHO_LOCATOR = new EchoLocatorBlock(EchoLocatorType.ECHO_GEODE_LOCATOR, FabricBlockSettings.of(Material.STONE).nonOpaque());

    public static final GeodeBuddingBlock BUDDING_EMERALD = new GeodeBuddingBlock(
            FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).ticksRandomly().strength(1.5F).sounds(ModBlockSoundGroups.EMERALD_GEODE).requiresTool(),
            ImmutableList.of(SMALL_EMERALD_BUD, MEDIUM_EMERALD_BUD, LARGE_EMERALD_BUD, EMERALD_CLUSTER)
    );

    public static final GeodeBuddingBlock BUDDING_QUARTZ = new GeodeBuddingBlock(
            FabricBlockSettings.of(Material.AMETHYST, MapColor.OFF_WHITE).ticksRandomly().strength(1.5f).sounds(ModBlockSoundGroups.QUARTZ_GEODE).requiresTool(),
            ImmutableList.of(SMALL_QUARTZ_BUD, MEDIUM_QUARTZ_BUD, LARGE_QUARTZ_BUD, QUARTZ_CLUSTER)
    );

    public static final GeodeBuddingBlock BUDDING_ECHO_BLOCK = new GeodeBuddingBlock(
            FabricBlockSettings.of(Material.AMETHYST, MapColor.BLACK).ticksRandomly().strength(1.5f).sounds(BlockSoundGroup.SCULK_SHRIEKER).requiresTool(),
            ImmutableList.of(SMALL_ECHO_BUD, MEDIUM_ECHO_BUD, LARGE_ECHO_BUD, ECHO_CLUSTER)
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
        register("diamond_geode", DIAMOND_GEODE);
        register("diamond_cluster", DIAMOND_CLUSTER);
        register("echo_block", ECHO_BLOCK);
        register("budding_echo_block", BUDDING_ECHO_BLOCK);
        register("echo_cluster", ECHO_CLUSTER);
        register("large_echo_bud", LARGE_ECHO_BUD);
        register("medium_echo_bud", MEDIUM_ECHO_BUD);
        register("small_echo_bud", SMALL_ECHO_BUD);
        register("emerald_crystal_echo_locator", EMERALD_CRYSTAL_ECHO_LOCATOR);
        register("quartz_crystal_echo_locator", QUARTZ_CRYSTAL_ECHO_LOCATOR);
        register("amethyst_crystal_echo_locator", AMETHYST_CRYSTAL_ECHO_LOCATOR);
        register("diamond_crystal_echo_locator", DIAMOND_CRYSTAL_ECHO_LOCATOR);
        register("echo_crystal_echo_locator", ECHO_CRYSTAL_ECHO_LOCATOR);
    }

    private static void register(String name, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(MoreGeodes.MODID, name), block);
    }

}
