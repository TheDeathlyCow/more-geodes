package com.github.thedeathlycow.moregeodes.tag;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

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
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, id));
    }
}
