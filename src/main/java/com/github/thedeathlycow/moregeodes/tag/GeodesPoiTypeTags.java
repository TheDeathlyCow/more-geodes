package com.github.thedeathlycow.moregeodes.tag;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.poi.PointOfInterestType;

public class GeodesPoiTypeTags {

    public static final TagKey<PointOfInterestType> CRYSTAL = of("crystal");
    private static TagKey<PointOfInterestType> of(String id) {
        return TagKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY, new Identifier(MoreGeodes.MODID, id));
    }

}
