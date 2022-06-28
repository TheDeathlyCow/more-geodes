package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.sounds.GeodesSoundEvents;
import com.github.thedeathlycow.moregeodes.tag.ModBlockTags;
import com.github.thedeathlycow.moregeodes.world.poi.GeodesPoiTypes;
import net.minecraft.block.Block;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.poi.PointOfInterestType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public record EchoLocatorType(SoundEvent activateSound, SoundEvent resonateSound, Set<PointOfInterestType> pointsOfInterest, TagKey<Block> validBlocks) {

    public static final EchoLocatorType CRYSTAL = new EchoLocatorType(SoundEvents.BLOCK_BELL_USE, GeodesSoundEvents.CRYSTAL_RESONATE, Set.of(GeodesPoiTypes.EMERALD_CRYSTAL, GeodesPoiTypes.QUARTZ_CRYSTAL, GeodesPoiTypes.DIAMOND_CRYSTAL, GeodesPoiTypes.AMETHYST_CRYSTAL, GeodesPoiTypes.ECHO_CRYSTAL), ModBlockTags.ECHO_LOCATABLE_DEFAULT);

    public static final EchoLocatorType EMERALD_GEODE_LOCATOR = new EchoLocatorType(SoundEvents.BLOCK_BELL_USE, GeodesSoundEvents.CRYSTAL_RESONATE, Set.of(GeodesPoiTypes.EMERALD_CRYSTAL), ModBlockTags.ECHO_LOCATABLE_EMERALD);

    public static final EchoLocatorType QUARTZ_GEODE_LOCATOR = new EchoLocatorType(SoundEvents.BLOCK_BELL_USE, GeodesSoundEvents.CRYSTAL_RESONATE, Set.of(GeodesPoiTypes.QUARTZ_CRYSTAL), ModBlockTags.ECHO_LOCATABLE_QUARTZ);

    public static final EchoLocatorType DIAMOND_GEODE_LOCATOR = new EchoLocatorType(SoundEvents.BLOCK_BELL_USE, GeodesSoundEvents.CRYSTAL_RESONATE, Set.of(GeodesPoiTypes.DIAMOND_CRYSTAL), ModBlockTags.ECHO_LOCATABLE_DIAMOND);

    public static final EchoLocatorType AMETHYST_GEODE_LOCATOR = new EchoLocatorType(SoundEvents.BLOCK_BELL_USE, GeodesSoundEvents.CRYSTAL_RESONATE, Set.of(GeodesPoiTypes.AMETHYST_CRYSTAL), ModBlockTags.ECHO_LOCATABLE_AMETHYST);

    public static final EchoLocatorType ECHO_GEODE_LOCATOR = new EchoLocatorType(SoundEvents.BLOCK_BELL_USE, GeodesSoundEvents.CRYSTAL_RESONATE, Set.of(GeodesPoiTypes.ECHO_CRYSTAL), ModBlockTags.ECHO_LOCATABLE_ECHO);


    public static EchoLocatorType fromNbt(NbtCompound nbt) {
        TagKey<Block> validBlocks = TagKey.of(Registry.BLOCK_KEY, new Identifier(nbt.getString("ValidBlocks")));
        SoundEvent activateSound = Registry.SOUND_EVENT.get(new Identifier(nbt.getString("ActivateSound")));
        SoundEvent resonateSound = Registry.SOUND_EVENT.get(new Identifier(nbt.getString("ResonateSound")));

        Set<PointOfInterestType> pois = new HashSet<>();
        NbtList poisNbt = nbt.getList("PointsOfInterest", NbtElement.STRING_TYPE);
        for (int i = 0; i < poisNbt.size(); i++) {
            Identifier id = new Identifier(poisNbt.getString(i));
            PointOfInterestType poi = Objects.requireNonNull(Registry.POINT_OF_INTEREST_TYPE.get(id));
            pois.add(poi);
        }

        return new EchoLocatorType(activateSound, resonateSound, pois, validBlocks);
    }

    public NbtCompound toNbt() {
        NbtCompound nbt = new NbtCompound();
        nbt.putString("ActivateSound", this.activateSound.getId().toString());
        nbt.putString("ResonateSound", this.resonateSound.getId().toString());
        nbt.putString("ValidBlocks", this.validBlocks.id().toString());

        NbtList pois = new NbtList();
        for (PointOfInterestType poi : this.pointsOfInterest) {
            Identifier poiID = Objects.requireNonNull(Registry.POINT_OF_INTEREST_TYPE.getId(poi));
            pois.add(NbtString.of(poiID.toString()));
        }
        nbt.put("PointsOfInterest", pois);
        return nbt;
    }
}
