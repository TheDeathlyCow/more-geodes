package com.github.thedeathlycow.item;

import com.github.thedeathlycow.blocks.ModBlocks;
import com.github.thedeathlycow.MoreGeodes;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static Item EMERALD_SHARD = new Item(new FabricItemSettings().group(ItemGroup.MISC));


    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(MoreGeodes.MODID, "emerald_shard"), EMERALD_SHARD);

        // block items
        Registry.register(Registry.ITEM, new Identifier(MoreGeodes.MODID, "emerald_geode"), new BlockItem(ModBlocks.EMERALD_GEODE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(MoreGeodes.MODID, "budding_emerald"), new BlockItem(ModBlocks.BUDDING_EMERALD, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    }
}
