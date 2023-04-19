package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.sounds.CrystalBlockSoundGroup;
import com.github.thedeathlycow.moregeodes.sounds.ModBlockSoundGroups;
import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.registry.Registry;


public class ModBlocks {

    public static final Block EMERALD_GEODE = new CrystalBlock(CrystalBlockSoundGroup.EMERALD_GEODE, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).strength(1.5F).sounds(ModBlockSoundGroups.EMERALD_GEODE).requiresTool());

    public static final Block EMERALD_CLUSTER = new CrystalClusterBlock(CrystalBlockSoundGroup.EMERALD_GEODE, 7, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.EMERALD_CLUSTER).strength(1.5f).luminance((blockState) -> 5));
    public static final Block LARGE_EMERALD_BUD = new CrystalClusterBlock(CrystalBlockSoundGroup.EMERALD_GEODE, 5, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.LARGE_EMERALD_BUD).strength(1.5f).luminance((blockState) -> 4));
    public static final Block MEDIUM_EMERALD_BUD = new CrystalClusterBlock(CrystalBlockSoundGroup.EMERALD_GEODE, 4, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.MEDIUM_EMERALD_BUD).strength(1.5f).luminance((blockState) -> 2));
    public static final Block SMALL_EMERALD_BUD = new CrystalClusterBlock(CrystalBlockSoundGroup.EMERALD_GEODE, 3, 4, FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.SMALL_EMERALD_BUD).strength(1.5f).luminance((blockState) -> 1));

    public static final Block QUARTZ_GEODE = new CrystalBlock(CrystalBlockSoundGroup.QUARTZ_GEODE, FabricBlockSettings.of(Material.AMETHYST, MapColor.OFF_WHITE).strength(1.5F).sounds(ModBlockSoundGroups.QUARTZ_GEODE).requiresTool());

    public static final Block QUARTZ_CLUSTER = new QuartzClusterBlock(7, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.OFF_WHITE).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.QUARTZ_CLUSTER).strength(1.5f).luminance((blockState) -> 5), 15, 1);
    public static final Block LARGE_QUARTZ_BUD = new QuartzClusterBlock(5, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.OFF_WHITE).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.LARGE_QUARTZ_BUD).strength(1.5f).luminance((blockState) -> 4), 15, 5);
    public static final Block MEDIUM_QUARTZ_BUD = new QuartzClusterBlock(4, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.OFF_WHITE).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.MEDIUM_QUARTZ_BUD).strength(1.5f).luminance((blockState) -> 2), 15, 10);
    public static final Block SMALL_QUARTZ_BUD = new QuartzClusterBlock(3, 4, FabricBlockSettings.of(Material.AMETHYST, MapColor.OFF_WHITE).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.SMALL_QUARTZ_BUD).strength(1.5f).luminance((blockState) -> 1), 15, 20);

    public static final Block DIAMOND_GEODE = new CrystalBlock(CrystalBlockSoundGroup.DIAMOND_GEODE, FabricBlockSettings.of(Material.AMETHYST, MapColor.DIAMOND_BLUE).strength(1.5F).sounds(ModBlockSoundGroups.DIAMOND_GEODE).requiresTool());

    public static final Block DIAMOND_CLUSTER = new CrystalClusterBlock(CrystalBlockSoundGroup.DIAMOND_GEODE, 7, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.DIAMOND_BLUE).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.DIAMOND_CLUSTER).strength(1.5f).luminance((blockState) -> 7));

    public static final Block ECHO_BLOCK = new CrystalBlock(CrystalBlockSoundGroup.ECHO_BLOCK, FabricBlockSettings.of(Material.AMETHYST, MapColor.BLACK).strength(1.5f).sounds(ModBlockSoundGroups.ECHO_BLOCK).requiresTool());

    public static final Block ECHO_CLUSTER = new CrystalClusterBlock(CrystalBlockSoundGroup.ECHO_BLOCK, 7, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.BLACK).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.ECHO_CLUSTER).strength(1.5f).luminance((blockState) -> 5));
    public static final Block LARGE_ECHO_BUD = new CrystalClusterBlock(CrystalBlockSoundGroup.ECHO_BLOCK, 5, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.BLACK).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.LARGE_ECHO_BUD).strength(1.5f).luminance((blockState) -> 4));
    public static final Block MEDIUM_ECHO_BUD = new CrystalClusterBlock(CrystalBlockSoundGroup.ECHO_BLOCK, 4, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.BLACK).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.MEDIUM_ECHO_BUD).strength(1.5f).luminance((blockState) -> 2));
    public static final Block SMALL_ECHO_BUD = new CrystalClusterBlock(CrystalBlockSoundGroup.ECHO_BLOCK, 3, 4, FabricBlockSettings.of(Material.AMETHYST, MapColor.BLACK).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.SMALL_ECHO_BUD).strength(1.5f).luminance((blockState) -> 1));

    public static final Block LAPIS_CRYSTAL_BLOCK = new CrystalBlock(CrystalBlockSoundGroup.LAPIS, FabricBlockSettings.of(Material.AMETHYST, MapColor.LAPIS_BLUE).strength(1.5f).sounds(ModBlockSoundGroups.LAPIS_CRYSTAL_BLOCK).requiresTool());
    public static final Block LAPIS_CLUSTER = new CrystalClusterBlock(CrystalBlockSoundGroup.LAPIS, 7, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.LAPIS_BLUE).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.LAPIS_CLUSTER).strength(1.5f).luminance((blockState) -> 5));
    public static final Block LARGE_LAPIS_BUD = new CrystalClusterBlock(CrystalBlockSoundGroup.LAPIS, 5, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.LAPIS_BLUE).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.LARGE_LAPIS_BUD).strength(1.5f).luminance((blockState) -> 4));
    public static final Block MEDIUM_LAPIS_BUD = new CrystalClusterBlock(CrystalBlockSoundGroup.LAPIS, 4, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.LAPIS_BLUE).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.MEDIUM_LAPIS_BUD).strength(1.5f).luminance((blockState) -> 2));
    public static final Block SMALL_LAPIS_BUD = new CrystalClusterBlock(CrystalBlockSoundGroup.LAPIS, 3, 4, FabricBlockSettings.of(Material.AMETHYST, MapColor.LAPIS_BLUE).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.SMALL_LAPIS_BUD).strength(1.5f).luminance((blockState) -> 1));


    public static final Block GYPSUM_CRYSTAL_BLOCK = new CrystalBlock(CrystalBlockSoundGroup.GYPSUM, FabricBlockSettings.of(Material.AMETHYST, MapColor.WHITE_GRAY).strength(1.3f).sounds(ModBlockSoundGroups.GYPSUM_CRYSTAL_BLOCK).requiresTool());
    public static final Block GYPSUM_ROSE = new LargeCrystalClusterBlock(CrystalBlockSoundGroup.GYPSUM, 13, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.WHITE_GRAY).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.GYPSUM_CRYSTAL_BLOCK).strength(1.3f));
    public static final Block LARGE_GYPSUM_BUD = new LargeCrystalClusterBlock(CrystalBlockSoundGroup.GYPSUM, 5, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.WHITE_GRAY).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.GYPSUM_CRYSTAL_BLOCK).strength(1.3f));
    public static final Block MEDIUM_GYPSUM_BUD = new CrystalClusterBlock(CrystalBlockSoundGroup.LAPIS, 16, 3, FabricBlockSettings.of(Material.AMETHYST, MapColor.LAPIS_BLUE).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.GYPSUM_CRYSTAL_BLOCK).strength(1.3f));
    public static final Block SMALL_GYPSUM_BUD = new CrystalClusterBlock(CrystalBlockSoundGroup.LAPIS, 11, 4, FabricBlockSettings.of(Material.AMETHYST, MapColor.LAPIS_BLUE).nonOpaque().requiresTool().sounds(ModBlockSoundGroups.GYPSUM_CRYSTAL_BLOCK).strength(1.3f));

    public static final Block ECHO_LOCATOR = new EchoLocatorBlock(EchoLocatorType.ALL, FabricBlockSettings.of(GeodeMaterials.ECHO_LOCATOR).nonOpaque().sounds(ModBlockSoundGroups.ECHO_LOCATOR).strength(1.5F, 6.0F).requiresTool());


    public static final Block EMERALD_CRYSTAL_ECHO_LOCATOR = new EchoLocatorBlock(EchoLocatorType.EMERALD_GEODE_LOCATOR, FabricBlockSettings.of(GeodeMaterials.ECHO_LOCATOR).nonOpaque().sounds(ModBlockSoundGroups.ECHO_LOCATOR).strength(1.5F, 6.0F).requiresTool());
    public static final Block QUARTZ_CRYSTAL_ECHO_LOCATOR = new EchoLocatorBlock(EchoLocatorType.QUARTZ_GEODE_LOCATOR, FabricBlockSettings.of(GeodeMaterials.ECHO_LOCATOR).nonOpaque().sounds(ModBlockSoundGroups.ECHO_LOCATOR).strength(1.5F, 6.0F).requiresTool());
    public static final Block AMETHYST_CRYSTAL_ECHO_LOCATOR = new EchoLocatorBlock(EchoLocatorType.AMETHYST_GEODE_LOCATOR, FabricBlockSettings.of(GeodeMaterials.ECHO_LOCATOR).nonOpaque().sounds(ModBlockSoundGroups.ECHO_LOCATOR).strength(1.5F, 6.0F).requiresTool());
    public static final Block DIAMOND_CRYSTAL_ECHO_LOCATOR = new EchoLocatorBlock(EchoLocatorType.DIAMOND_GEODE_LOCATOR, FabricBlockSettings.of(GeodeMaterials.ECHO_LOCATOR).nonOpaque().sounds(ModBlockSoundGroups.ECHO_LOCATOR).strength(1.5F, 6.0F).requiresTool());
    public static final Block ECHO_CRYSTAL_ECHO_LOCATOR = new EchoLocatorBlock(EchoLocatorType.ECHO_GEODE_LOCATOR, FabricBlockSettings.of(GeodeMaterials.ECHO_LOCATOR).nonOpaque().sounds(ModBlockSoundGroups.ECHO_LOCATOR).strength(1.5F, 6.0F).requiresTool());

    public static final Block GABBRO = new Block(FabricBlockSettings.of(Material.STONE, MapColor.BLACK).requiresTool().strength(1.25F, 4.2F).sounds(BlockSoundGroup.BASALT));

    public static final GeodeBuddingBlock BUDDING_EMERALD = new GeodeBuddingBlock(
            CrystalBlockSoundGroup.EMERALD_GEODE,
            FabricBlockSettings.of(Material.AMETHYST, MapColor.EMERALD_GREEN).ticksRandomly().strength(1.5F).sounds(ModBlockSoundGroups.EMERALD_GEODE).requiresTool(),
            ImmutableList.of(SMALL_EMERALD_BUD, MEDIUM_EMERALD_BUD, LARGE_EMERALD_BUD, EMERALD_CLUSTER)
    );

    public static final GeodeBuddingBlock BUDDING_QUARTZ = new GeodeBuddingBlock(
            CrystalBlockSoundGroup.QUARTZ_GEODE,
            FabricBlockSettings.of(Material.AMETHYST, MapColor.OFF_WHITE).ticksRandomly().strength(1.5f).sounds(ModBlockSoundGroups.QUARTZ_GEODE).requiresTool(),
            ImmutableList.of(SMALL_QUARTZ_BUD, MEDIUM_QUARTZ_BUD, LARGE_QUARTZ_BUD, QUARTZ_CLUSTER)
    );

    public static final GeodeBuddingBlock BUDDING_ECHO_BLOCK = new GeodeBuddingBlock(
            CrystalBlockSoundGroup.ECHO_BLOCK,
            FabricBlockSettings.of(Material.AMETHYST, MapColor.BLACK).ticksRandomly().strength(1.5f).sounds(ModBlockSoundGroups.ECHO_BLOCK).requiresTool(),
            ImmutableList.of(SMALL_ECHO_BUD, MEDIUM_ECHO_BUD, LARGE_ECHO_BUD, ECHO_CLUSTER)
    );

    public static final GeodeBuddingBlock BUDDING_LAPIS = new GeodeBuddingBlock(
            CrystalBlockSoundGroup.LAPIS,
            FabricBlockSettings.of(Material.AMETHYST, MapColor.BLACK).ticksRandomly().strength(1.5f).sounds(ModBlockSoundGroups.LAPIS_CRYSTAL_BLOCK).requiresTool(),
            ImmutableList.of(SMALL_LAPIS_BUD, MEDIUM_LAPIS_BUD, LARGE_LAPIS_BUD, LAPIS_CLUSTER)
    );

    public static final Block BUDDING_GYPSUM = new DirectedBuddingBlock(
            Direction.UP,
            CrystalBlockSoundGroup.GYPSUM,
            FabricBlockSettings.of(Material.AMETHYST, MapColor.WHITE_GRAY).ticksRandomly().strength(1.3f).sounds(ModBlockSoundGroups.GYPSUM_CRYSTAL_BLOCK).requiresTool(),
            ImmutableList.of(SMALL_GYPSUM_BUD, MEDIUM_GYPSUM_BUD, LARGE_GYPSUM_BUD, GYPSUM_ROSE)
    );


    public static final Block PYRITE = new Block(
            FabricBlockSettings.of(Material.STONE, MapColor.GOLD)
                    .sounds(BlockSoundGroup.CALCITE)
                    .requiresTool()
                    .strength(0.75F)
    );

    public static final Block PYRITE_STAIRS = new StairsBlock(
            PYRITE.getDefaultState(),
            FabricBlockSettings.copyOf(PYRITE)
    );

    public static final Block PYRITE_SLAB = new SlabBlock(
            FabricBlockSettings.copyOf(PYRITE)
    );

    public static final Block PYRITE_WALL = new WallBlock(
            FabricBlockSettings.copyOf(PYRITE)
    );

    public static final Block CALCITE_STAIRS = new StairsBlock(
            Blocks.CALCITE.getDefaultState(),
            FabricBlockSettings.copyOf(Blocks.CALCITE)
    );

    public static final Block CALCITE_SLAB = new SlabBlock(
            FabricBlockSettings.copyOf(Blocks.CALCITE)
    );

    public static final Block CALCITE_WALL = new WallBlock(
            FabricBlockSettings.copyOf(Blocks.CALCITE)
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
        register("echo_locator", ECHO_LOCATOR);
        register("emerald_crystal_echo_locator", EMERALD_CRYSTAL_ECHO_LOCATOR);
        register("quartz_crystal_echo_locator", QUARTZ_CRYSTAL_ECHO_LOCATOR);
        register("amethyst_crystal_echo_locator", AMETHYST_CRYSTAL_ECHO_LOCATOR);
        register("diamond_crystal_echo_locator", DIAMOND_CRYSTAL_ECHO_LOCATOR);
        register("echo_crystal_echo_locator", ECHO_CRYSTAL_ECHO_LOCATOR);
        register("lapis_crystal_block", LAPIS_CRYSTAL_BLOCK);
        register("lapis_cluster", LAPIS_CLUSTER);
        register("large_lapis_bud", LARGE_LAPIS_BUD);
        register("medium_lapis_bud", MEDIUM_LAPIS_BUD);
        register("small_lapis_bud", SMALL_LAPIS_BUD);
        register("budding_lapis", BUDDING_LAPIS);
        register("pyrite", PYRITE);
        register("pyrite_stairs", PYRITE_STAIRS);
        register("pyrite_slab", PYRITE_SLAB);
        register("pyrite_wall", PYRITE_WALL);
        register("calcite_stairs", CALCITE_STAIRS);
        register("calcite_slab", CALCITE_SLAB);
        register("calcite_wall", CALCITE_WALL);
        register("gypsum_crystal_block", GYPSUM_CRYSTAL_BLOCK);
        register("gypsum_rose", GYPSUM_ROSE);
        register("large_gypsum_bud", LARGE_GYPSUM_BUD);
        register("medium_gypsum_bud", MEDIUM_GYPSUM_BUD);
        register("small_gypsum_bud", SMALL_GYPSUM_BUD);
        register("budding_gypsum", BUDDING_GYPSUM);
        register("gabbro", GABBRO);
    }

    private static void register(String name, Block block) {
        Registry.register(Registries.BLOCK, new Identifier(MoreGeodes.MODID, name), block);
    }

}
