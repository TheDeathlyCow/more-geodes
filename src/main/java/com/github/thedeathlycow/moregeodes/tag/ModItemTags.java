package com.github.thedeathlycow.moregeodes.tag;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItemTags {

    public static final TagKey<Item> FOOLS_GOLD = create("fools_gold");

    private static TagKey<Item> create(String id) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(MoreGeodes.MODID, id));
    }

}
