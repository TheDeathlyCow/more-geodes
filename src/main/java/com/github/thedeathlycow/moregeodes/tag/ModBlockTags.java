package com.github.thedeathlycow.moregeodes.tag;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockTags {

    public static final TagKey<Block> ECHO_LOCATOR_DEFAULT = create("echo_locator/default");
    public static final TagKey<Block> ECHO_LOCATOR_EMERALD = create("echo_locator/emerald");
    public static final TagKey<Block> ECHO_LOCATOR_AMETHYST = create("echo_locator/amethyst");
    public static final TagKey<Block> ECHO_LOCATOR_QUARTZ = create("echo_locator/quartz");
    public static final TagKey<Block> ECHO_LOCATOR_DIAMOND = create("echo_locator/diamond");
    public static final TagKey<Block> ECHO_LOCATOR_ECHO = create("echo_locator/echo");

    private static TagKey<Block> create(String id) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier(MoreGeodes.MODID, id));
    }
}
