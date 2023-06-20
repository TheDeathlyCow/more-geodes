package com.github.thedeathlycow.moregeodes.datafixers;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.Identifier;

import java.util.Map;

public class GeodeBlockRenaming {

    public static final Map<Identifier, Identifier> MAP = ImmutableMap.<Identifier, Identifier>builder()
            .put(
                    new Identifier(MoreGeodes.MODID, "emerald_geode"),
                    new Identifier(MoreGeodes.MODID, "emerald_crystal_block")
            )
            .put(
                    new Identifier(MoreGeodes.MODID, "quartz_geode"),
                    new Identifier(MoreGeodes.MODID, "quartz_crystal_block")
            )
            .put(
                    new Identifier(MoreGeodes.MODID, "diamond_geode"),
                    new Identifier(MoreGeodes.MODID, "diamond_crystal_block")
            )
            .put(
                    new Identifier(MoreGeodes.MODID, "amethyst_crystal_echo_locator"),
                    new Identifier(MoreGeodes.MODID, "echo_locator")
            )
            .put(
                    new Identifier(MoreGeodes.MODID, "emerald_crystal_echo_locator"),
                    new Identifier(MoreGeodes.MODID, "echo_locator")
            )
            .put(
                    new Identifier(MoreGeodes.MODID, "quartz_crystal_echo_locator"),
                    new Identifier(MoreGeodes.MODID, "echo_locator")
            )
            .put(
                    new Identifier(MoreGeodes.MODID, "diamond_crystal_echo_locator"),
                    new Identifier(MoreGeodes.MODID, "echo_locator")
            )
            .put(
                    new Identifier(MoreGeodes.MODID, "echo_crystal_echo_locator"),
                    new Identifier(MoreGeodes.MODID, "echo_locator")
            )
            .build();

    private GeodeBlockRenaming() {
        
    }
}
