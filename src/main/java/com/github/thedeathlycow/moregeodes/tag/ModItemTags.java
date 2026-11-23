package com.github.thedeathlycow.moregeodes.tag;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {

    public static final TagKey<Item> FOOLS_GOLD = create("fools_gold");

    private static TagKey<Item> create(String id) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, id));
    }

}
