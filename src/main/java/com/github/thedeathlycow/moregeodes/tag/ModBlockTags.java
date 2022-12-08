package com.github.thedeathlycow.moregeodes.tag;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockTags {

    public static final TagKey<Block> ECHO_LOCATABLE_DEFAULT = create("echo_locatable/default");
    public static final TagKey<Block> ECHO_LOCATABLE_EMERALD = create("echo_locatable/emerald");
    public static final TagKey<Block> ECHO_LOCATABLE_AMETHYST = create("echo_locatable/amethyst");
    public static final TagKey<Block> ECHO_LOCATABLE_QUARTZ = create("echo_locatable/quartz");
    public static final TagKey<Block> ECHO_LOCATABLE_DIAMOND = create("echo_locatable/diamond");
    public static final TagKey<Block> ECHO_LOCATABLE_ECHO = create("echo_locatable/echo");

    public static final TagKey<Block> ECHO_LOCATABLE = create("echo_locatable");

    public static final TagKey<Block> CUSTOM_CRYSTAL_SOUND_BLOCKS = create("custom_crystal_sound_blocks");

    public static final TagKey<Block> GYPSUM_PATCH_REPLACEABLE = create("gypsum_patch_replaceable");

    private static TagKey<Block> create(String id) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier(MoreGeodes.MODID, id));
    }
}
