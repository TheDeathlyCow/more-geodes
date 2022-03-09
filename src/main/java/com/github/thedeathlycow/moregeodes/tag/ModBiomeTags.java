package com.github.thedeathlycow.moregeodes.tag;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class ModBiomeTags {

    public static final TagKey<Biome> HAS_EMERALD_GEODE = of("has_emerald_geode");
    public static final TagKey<Biome> HAS_QUARTZ_GEODE = of("has_quartz_geode");

    private static TagKey<Biome> of(String id) {
        return TagKey.of(Registry.BIOME_KEY, new Identifier(MoreGeodes.MODID, id));
    }

}
