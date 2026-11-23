package com.github.thedeathlycow.moregeodes.datafixers;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import net.minecraft.resources.Identifier;

public class GeodeBlockRenaming {

    public static final Map<Identifier, Identifier> MAP = ImmutableMap.<Identifier, Identifier>builder()
            .put(
                    Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "emerald_geode"),
                    Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "emerald_crystal_block")
            )
            .put(
                    Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "quartz_geode"),
                    Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "quartz_crystal_block")
            )
            .put(
                    Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "diamond_geode"),
                    Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "diamond_crystal_block")
            )
            .put(
                    Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "amethyst_crystal_echo_locator"),
                    Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "echo_locator")
            )
            .put(
                    Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "emerald_crystal_echo_locator"),
                    Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "echo_locator")
            )
            .put(
                    Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "quartz_crystal_echo_locator"),
                    Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "echo_locator")
            )
            .put(
                    Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "diamond_crystal_echo_locator"),
                    Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "echo_locator")
            )
            .put(
                    Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "echo_crystal_echo_locator"),
                    Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "echo_locator")
            )
            .build();

    private GeodeBlockRenaming() {
        
    }
}
