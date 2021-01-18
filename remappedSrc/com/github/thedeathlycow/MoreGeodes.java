package com.github.thedeathlycow;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MoreGeodes implements ModInitializer {

    public static String MODID = "geodes";

    public static Item EMERALD_SHARD = new Item(new FabricItemSettings().group(ItemGroup.MISC));

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        System.out.println("Hello Fabric world!");

        Registry.register(Registry.ITEM, new Identifier(MODID, "emerald_shard"), EMERALD_SHARD);
    }
}
