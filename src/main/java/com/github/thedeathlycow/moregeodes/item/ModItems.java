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

    public static final Item EMERALD_SHARD = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item EMERALD_GEODE = new BlockItem(ModBlocks.EMERALD_GEODE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item BUDDING_EMERALD = new BlockItem(ModBlocks.BUDDING_EMERALD, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item SMALL_EMERALD_BUD = new BlockItem(ModBlocks.SMALL_EMERALD_BUD, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item MEDIUM_EMERALD_BUD = new BlockItem(ModBlocks.MEDIUM_EMERALD_BUD, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item LARGE_EMERALD_BUD = new BlockItem(ModBlocks.LARGE_EMERALD_BUD, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item EMERALD_CLUSTER = new BlockItem(ModBlocks.EMERALD_CLUSTER, new Item.Settings().group(ItemGroup.DECORATIONS));

    public static final Item QUARTZ_GEODE = new BlockItem(ModBlocks.QUARTZ_GEODE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item BUDDING_QUARTZ = new BlockItem(ModBlocks.BUDDING_QUARTZ, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item SMALL_QUARTZ_BUD = new BlockItem(ModBlocks.SMALL_QUARTZ_BUD, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item MEDIUM_QUARTZ_BUD = new BlockItem(ModBlocks.MEDIUM_QUARTZ_BUD, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item LARGE_QUARTZ_BUD = new BlockItem(ModBlocks.LARGE_QUARTZ_BUD, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item QUARTZ_CLUSTER = new BlockItem(ModBlocks.QUARTZ_CLUSTER, new Item.Settings().group(ItemGroup.DECORATIONS));

    public static final Item DIAMOND_GEODE = new BlockItem(ModBlocks.DIAMOND_GEODE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item DIAMOND_CLUSTER = new BlockItem(ModBlocks.DIAMOND_CLUSTER, new Item.Settings().group(ItemGroup.DECORATIONS));

    public static final Item ECHO_BLOCK = new BlockItem(ModBlocks.ECHO_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item BUDDING_ECHO_BLOCK = new BlockItem(ModBlocks.BUDDING_ECHO_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item SMALL_ECHO_BUD = new BlockItem(ModBlocks.SMALL_ECHO_BUD, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item MEDIUM_ECHO_BUD = new BlockItem(ModBlocks.MEDIUM_ECHO_BUD, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item LARGE_ECHO_BUD = new BlockItem(ModBlocks.LARGE_ECHO_BUD, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item ECHO_CLUSTER = new BlockItem(ModBlocks.ECHO_CLUSTER, new Item.Settings().group(ItemGroup.DECORATIONS));

    public static final Item ECHO_LOCATOR = new BlockItem(ModBlocks.ECHO_LOCATOR, new FabricItemSettings().group(ItemGroup.DECORATIONS));

    public static void registerItems() {
        register("emerald_shard", EMERALD_SHARD);
        register("emerald_geode", EMERALD_GEODE);
        register("budding_emerald", BUDDING_EMERALD);
        register("small_emerald_bud", SMALL_EMERALD_BUD);
        register("medium_emerald_bud", MEDIUM_EMERALD_BUD);
        register("large_emerald_bud", LARGE_EMERALD_BUD);
        register("emerald_cluster", EMERALD_CLUSTER);
        register("quartz_geode", QUARTZ_GEODE);
        register("budding_quartz", BUDDING_QUARTZ);
        register("small_quartz_bud", SMALL_QUARTZ_BUD);
        register("medium_quartz_bud", MEDIUM_QUARTZ_BUD);
        register("large_quartz_bud", LARGE_QUARTZ_BUD);
        register("quartz_cluster", QUARTZ_CLUSTER);
        register("diamond_geode", DIAMOND_GEODE);
        register("diamond_cluster", DIAMOND_CLUSTER);
        register("echo_block", ECHO_BLOCK);
        register("budding_echo_block", BUDDING_ECHO_BLOCK);
        register("small_echo_bud", SMALL_ECHO_BUD);
        register("medium_echo_bud", MEDIUM_ECHO_BUD);
        register("large_echo_bud", LARGE_ECHO_BUD);
        register("echo_cluster", ECHO_CLUSTER);
        register("echo_locator", ECHO_LOCATOR);
    }

    private static void register(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(MoreGeodes.MODID, name), item);
    }
}
