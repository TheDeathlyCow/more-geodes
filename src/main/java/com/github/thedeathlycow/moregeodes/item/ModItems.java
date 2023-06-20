package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item EMERALD_SHARD = new Item(new FabricItemSettings());
    public static final Item PYRITE_CHUNK = new Item(new FabricItemSettings());
    public static final Item GYPSUM_SHARD = new Item(new FabricItemSettings());

    public static final Item EMERALD_CRYSTAL_BLOCK = new BlockItem(ModBlocks.EMERALD_CRYSTAL_BLOCK, new FabricItemSettings());
    public static final Item BUDDING_EMERALD = new BlockItem(ModBlocks.BUDDING_EMERALD, new FabricItemSettings());
    public static final Item SMALL_EMERALD_BUD = new BlockItem(ModBlocks.SMALL_EMERALD_BUD, new FabricItemSettings());
    public static final Item MEDIUM_EMERALD_BUD = new BlockItem(ModBlocks.MEDIUM_EMERALD_BUD, new FabricItemSettings());
    public static final Item LARGE_EMERALD_BUD = new BlockItem(ModBlocks.LARGE_EMERALD_BUD, new FabricItemSettings());
    public static final Item EMERALD_CLUSTER = new BlockItem(ModBlocks.EMERALD_CLUSTER, new FabricItemSettings());

    public static final Item QUARTZ_CRYSTAL_BLOCK = new BlockItem(ModBlocks.QUARTZ_CRYSTAL_BLOCK, new FabricItemSettings());
    public static final Item BUDDING_QUARTZ = new BlockItem(ModBlocks.BUDDING_QUARTZ, new FabricItemSettings());
    public static final Item SMALL_QUARTZ_BUD = new BlockItem(ModBlocks.SMALL_QUARTZ_BUD, new FabricItemSettings());
    public static final Item MEDIUM_QUARTZ_BUD = new BlockItem(ModBlocks.MEDIUM_QUARTZ_BUD, new FabricItemSettings());
    public static final Item LARGE_QUARTZ_BUD = new BlockItem(ModBlocks.LARGE_QUARTZ_BUD, new FabricItemSettings());
    public static final Item QUARTZ_CLUSTER = new BlockItem(ModBlocks.QUARTZ_CLUSTER, new FabricItemSettings());

    public static final Item DIAMOND_CRYSTAL_BLOCK = new BlockItem(ModBlocks.DIAMOND_CRYSTAL_BLOCK, new FabricItemSettings());
    public static final Item DIAMOND_CLUSTER = new BlockItem(ModBlocks.DIAMOND_CLUSTER, new FabricItemSettings());

    public static final Item ECHO_BLOCK = new BlockItem(ModBlocks.ECHO_BLOCK, new FabricItemSettings());
    public static final Item BUDDING_ECHO_BLOCK = new BlockItem(ModBlocks.BUDDING_ECHO_BLOCK, new FabricItemSettings());
    public static final Item SMALL_ECHO_BUD = new BlockItem(ModBlocks.SMALL_ECHO_BUD, new FabricItemSettings());
    public static final Item MEDIUM_ECHO_BUD = new BlockItem(ModBlocks.MEDIUM_ECHO_BUD, new FabricItemSettings());
    public static final Item LARGE_ECHO_BUD = new BlockItem(ModBlocks.LARGE_ECHO_BUD, new FabricItemSettings());
    public static final Item ECHO_CLUSTER = new BlockItem(ModBlocks.ECHO_CLUSTER, new FabricItemSettings());

    public static final Item LAPIS_CRYSTAL_BLOCK = new BlockItem(ModBlocks.LAPIS_CRYSTAL_BLOCK, new FabricItemSettings());

    public static final Item BUDDING_LAPIS = new BlockItem(ModBlocks.BUDDING_LAPIS, new FabricItemSettings());

    public static final Item SMALL_LAPIS_BUD = new BlockItem(ModBlocks.SMALL_LAPIS_BUD, new FabricItemSettings());
    public static final Item MEDIUM_LAPIS_BUD = new BlockItem(ModBlocks.MEDIUM_LAPIS_BUD, new FabricItemSettings());
    public static final Item LARGE_LAPIS_BUD = new BlockItem(ModBlocks.LARGE_LAPIS_BUD, new FabricItemSettings());
    public static final Item LAPIS_CLUSTER = new BlockItem(ModBlocks.LAPIS_CLUSTER, new FabricItemSettings());

    public static final Item GYPSUM_CRYSTAL_BLOCK = new BlockItem(ModBlocks.GYPSUM_CRYSTAL_BLOCK, new FabricItemSettings());
    public static final Item BUDDING_GYPSUM = new BlockItem(ModBlocks.BUDDING_GYPSUM, new FabricItemSettings());
    public static final Item SMALL_GYPSUM_BUD = new BlockItem(ModBlocks.SMALL_GYPSUM_BUD, new FabricItemSettings());
    public static final Item MEDIUM_GYPSUM_BUD = new BlockItem(ModBlocks.MEDIUM_GYPSUM_BUD, new FabricItemSettings());
    public static final Item LARGE_GYPSUM_BUD = new BlockItem(ModBlocks.LARGE_GYPSUM_BUD, new FabricItemSettings());
    public static final Item GYPSUM_ROSE = new BlockItem(ModBlocks.GYPSUM_ROSE, new FabricItemSettings());


    public static final Item PYRITE = new BlockItem(ModBlocks.PYRITE, new FabricItemSettings());
    public static final Item PYRITE_STAIRS = new BlockItem(ModBlocks.PYRITE_STAIRS, new FabricItemSettings());
    public static final Item PYRITE_SLAB = new BlockItem(ModBlocks.PYRITE_SLAB, new FabricItemSettings());
    public static final Item PYRITE_WALL = new BlockItem(ModBlocks.PYRITE_WALL, new FabricItemSettings());

    public static final Item CALCITE_STAIRS = new BlockItem(ModBlocks.CALCITE_STAIRS, new FabricItemSettings());
    public static final Item CALCITE_SLAB = new BlockItem(ModBlocks.CALCITE_SLAB, new FabricItemSettings());
    public static final Item CALCITE_WALL = new BlockItem(ModBlocks.CALCITE_WALL, new FabricItemSettings());

    public static final Item ECHO_LOCATOR = new BlockItem(ModBlocks.ECHO_LOCATOR, new FabricItemSettings().maxCount(1));
    public static final Item GABRRO = new BlockItem(ModBlocks.GABBRO, new FabricItemSettings());

    public static void registerItems() {
        register("emerald_shard", EMERALD_SHARD);
        register("pyrite_chunk", PYRITE_CHUNK);
        register("gypsum_shard", GYPSUM_SHARD);
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
        register("lapis_crystal_block", LAPIS_CRYSTAL_BLOCK);
        register("small_lapis_bud", SMALL_LAPIS_BUD);
        register("medium_lapis_bud", MEDIUM_LAPIS_BUD);
        register("large_lapis_bud", LARGE_LAPIS_BUD);
        register("lapis_cluster", LAPIS_CLUSTER);
        register("budding_lapis", BUDDING_LAPIS);
        register("gypsum_crystal_block", GYPSUM_CRYSTAL_BLOCK);
        register("budding_gypsum", BUDDING_GYPSUM);
        register("small_gypsum_bud", SMALL_GYPSUM_BUD);
        register("medium_gypsum_bud", MEDIUM_GYPSUM_BUD);
        register("large_gypsum_bud", LARGE_GYPSUM_BUD);
        register("gypsum_rose", GYPSUM_ROSE);
        register("echo_locator", ECHO_LOCATOR);
        register("pyrite", PYRITE);
        register("pyrite_stairs", PYRITE_STAIRS);
        register("pyrite_slab", PYRITE_SLAB);
        register("pyrite_wall", PYRITE_WALL);
        register("calcite_stairs", CALCITE_STAIRS);
        register("calcite_slab", CALCITE_SLAB);
        register("calcite_wall", CALCITE_WALL);
        register("gabbro", GABRRO);

        CompostingChanceRegistry.INSTANCE.add(GYPSUM_SHARD, 1.0f);

        GeodesItemGroup.register();
    }

    private static void register(String name, Item item) {
        Registry.register(Registries.ITEM, new Identifier(MoreGeodes.MODID, name), item);
    }
}
