package com.github.thedeathlycow.moregeodes.tag;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {

    public static final TagKey<Item> FOOLS_GOLD = create("fools_gold");

    private static TagKey<Item> create(String id) {
        return TagKey.of(RegistryKeys.ITEM, new Identifier(MoreGeodes.MODID, id));
    }

}
