package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item PYRITE_CHUNK = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item EMERALD_CRYSTAL_BLOCK = new BlockItem(ModBlocks.EMERALD_CRYSTAL_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item BUDDING_EMERALD = new BlockItem(ModBlocks.BUDDING_EMERALD, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item SMALL_EMERALD_BUD = new BlockItem(ModBlocks.SMALL_EMERALD_BUD, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item MEDIUM_EMERALD_BUD = new BlockItem(ModBlocks.MEDIUM_EMERALD_BUD, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item LARGE_EMERALD_BUD = new BlockItem(ModBlocks.LARGE_EMERALD_BUD, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item EMERALD_CLUSTER = new BlockItem(ModBlocks.EMERALD_CLUSTER, new FabricItemSettings().group(ItemGroup.DECORATIONS));

    public static final Item QUARTZ_CRYSTAL_BLOCK = new BlockItem(ModBlocks.QUARTZ_CRYSTAL_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item BUDDING_QUARTZ = new BlockItem(ModBlocks.BUDDING_QUARTZ, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item SMALL_QUARTZ_BUD = new BlockItem(ModBlocks.SMALL_QUARTZ_BUD, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item MEDIUM_QUARTZ_BUD = new BlockItem(ModBlocks.MEDIUM_QUARTZ_BUD, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item LARGE_QUARTZ_BUD = new BlockItem(ModBlocks.LARGE_QUARTZ_BUD, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item QUARTZ_CLUSTER = new BlockItem(ModBlocks.QUARTZ_CLUSTER, new FabricItemSettings().group(ItemGroup.DECORATIONS));

    public static final Item DIAMOND_CRYSTAL_BLOCK = new BlockItem(ModBlocks.DIAMOND_CRYSTAL_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item DIAMOND_CLUSTER = new BlockItem(ModBlocks.DIAMOND_CLUSTER, new FabricItemSettings().group(ItemGroup.DECORATIONS));

    public static final Item ECHO_BLOCK = new BlockItem(ModBlocks.ECHO_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item BUDDING_ECHO_BLOCK = new BlockItem(ModBlocks.BUDDING_ECHO_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item SMALL_ECHO_BUD = new BlockItem(ModBlocks.SMALL_ECHO_BUD, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item MEDIUM_ECHO_BUD = new BlockItem(ModBlocks.MEDIUM_ECHO_BUD, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item LARGE_ECHO_BUD = new BlockItem(ModBlocks.LARGE_ECHO_BUD, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item ECHO_CLUSTER = new BlockItem(ModBlocks.ECHO_CLUSTER, new FabricItemSettings().group(ItemGroup.DECORATIONS));

    public static final Item LAPIS_CRYSTAL_BLOCK = new BlockItem(ModBlocks.LAPIS_CRYSTAL_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));

    public static final Item BUDDING_LAPIS = new BlockItem(ModBlocks.BUDDING_LAPIS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));

    public static final Item SMALL_LAPIS_BUD = new BlockItem(ModBlocks.SMALL_LAPIS_BUD, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item MEDIUM_LAPIS_BUD = new BlockItem(ModBlocks.MEDIUM_LAPIS_BUD, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item LARGE_LAPIS_BUD = new BlockItem(ModBlocks.LARGE_LAPIS_BUD, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item LAPIS_CLUSTER = new BlockItem(ModBlocks.LAPIS_CLUSTER, new FabricItemSettings().group(ItemGroup.DECORATIONS));

    public static final Item PYRITE = new BlockItem(ModBlocks.PYRITE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item PYRITE_STAIRS = new BlockItem(ModBlocks.PYRITE_STAIRS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item PYRITE_SLAB = new BlockItem(ModBlocks.PYRITE_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item PYRITE_WALL = new BlockItem(ModBlocks.PYRITE_WALL, new FabricItemSettings().group(ItemGroup.DECORATIONS));

    public static final Item CALCITE_STAIRS = new BlockItem(ModBlocks.CALCITE_STAIRS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item CALCITE_SLAB = new BlockItem(ModBlocks.CALCITE_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item CALCITE_WALL = new BlockItem(ModBlocks.CALCITE_WALL, new FabricItemSettings().group(ItemGroup.DECORATIONS));

    public static final Item ECHO_LOCATOR = new BlockItem(ModBlocks.ECHO_LOCATOR, new FabricItemSettings().group(ItemGroup.DECORATIONS).maxCount(1));

    public static final Item EMERALD_CRYSTAL_ECHO_LOCATOR = new BlockItem(ModBlocks.EMERALD_CRYSTAL_ECHO_LOCATOR, new FabricItemSettings().maxCount(1));
    public static final Item QUARTZ_CRYSTAL_ECHO_LOCATOR = new BlockItem(ModBlocks.QUARTZ_CRYSTAL_ECHO_LOCATOR, new FabricItemSettings().maxCount(1));
    public static final Item AMETHYST_CRYSTAL_ECHO_LOCATOR = new BlockItem(ModBlocks.AMETHYST_CRYSTAL_ECHO_LOCATOR, new FabricItemSettings().maxCount(1));
    public static final Item DIAMOND_CRYSTAL_ECHO_LOCATOR = new BlockItem(ModBlocks.DIAMOND_CRYSTAL_ECHO_LOCATOR, new FabricItemSettings().maxCount(1));
    public static final Item ECHO_CRYSTAL_ECHO_LOCATOR = new BlockItem(ModBlocks.ECHO_CRYSTAL_ECHO_LOCATOR, new FabricItemSettings().maxCount(1));

    public static void registerItems() {
        register("pyrite_chunk", PYRITE_CHUNK);
        register("emerald_crystal_block", EMERALD_CRYSTAL_BLOCK);
        register("budding_emerald", BUDDING_EMERALD);
        register("small_emerald_bud", SMALL_EMERALD_BUD);
        register("medium_emerald_bud", MEDIUM_EMERALD_BUD);
        register("large_emerald_bud", LARGE_EMERALD_BUD);
        register("emerald_cluster", EMERALD_CLUSTER);
        register("quartz_crystal_block", QUARTZ_CRYSTAL_BLOCK);
        register("budding_quartz", BUDDING_QUARTZ);
        register("small_quartz_bud", SMALL_QUARTZ_BUD);
        register("medium_quartz_bud", MEDIUM_QUARTZ_BUD);
        register("large_quartz_bud", LARGE_QUARTZ_BUD);
        register("quartz_cluster", QUARTZ_CLUSTER);
        register("diamond_crystal_block", DIAMOND_CRYSTAL_BLOCK);
        register("diamond_cluster", DIAMOND_CLUSTER);
        register("echo_block", ECHO_BLOCK);
        register("budding_echo_block", BUDDING_ECHO_BLOCK);
        register("small_echo_bud", SMALL_ECHO_BUD);
        register("medium_echo_bud", MEDIUM_ECHO_BUD);
        register("large_echo_bud", LARGE_ECHO_BUD);
        register("echo_cluster", ECHO_CLUSTER);
        register("echo_locator", ECHO_LOCATOR);
        register("emerald_crystal_echo_locator", EMERALD_CRYSTAL_ECHO_LOCATOR);
        register("quartz_crystal_echo_locator", QUARTZ_CRYSTAL_ECHO_LOCATOR);
        register("amethyst_crystal_echo_locator", AMETHYST_CRYSTAL_ECHO_LOCATOR);
        register("diamond_crystal_echo_locator", DIAMOND_CRYSTAL_ECHO_LOCATOR);
        register("echo_crystal_echo_locator", ECHO_CRYSTAL_ECHO_LOCATOR);
        register("lapis_crystal_block", LAPIS_CRYSTAL_BLOCK);
        register("small_lapis_bud", SMALL_LAPIS_BUD);
        register("medium_lapis_bud", MEDIUM_LAPIS_BUD);
        register("large_lapis_bud", LARGE_LAPIS_BUD);
        register("lapis_cluster", LAPIS_CLUSTER);
        register("budding_lapis", BUDDING_LAPIS);
        register("pyrite", PYRITE);
        register("pyrite_stairs", PYRITE_STAIRS);
        register("pyrite_slab", PYRITE_SLAB);
        register("pyrite_wall", PYRITE_WALL);
        register("calcite_stairs", CALCITE_STAIRS);
        register("calcite_slab", CALCITE_SLAB);
        register("calcite_wall", CALCITE_WALL);
    }

    private static void register(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(MoreGeodes.MODID, name), item);
    }
}
