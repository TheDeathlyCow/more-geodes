package com.github.thedeathlycow.moregeodes.datafixers;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class GeodeBlockRenaming {

    public static final Map<String, String> MAP = ImmutableMap.<String, String>builder()
            .put("geodes:emerald_geode", "geodes:emerald_crystal_block")
            .put("geodes:quartz_geode", "geodes:quartz_crystal_block")
            .put("geodes:diamond_geode", "geodes:diamond_crystal_block")
            .build();

}
