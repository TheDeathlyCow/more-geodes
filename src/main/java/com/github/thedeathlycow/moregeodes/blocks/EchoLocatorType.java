package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.sounds.GeodesSoundEvents;
import com.github.thedeathlycow.moregeodes.tag.ModBlockTags;
import net.minecraft.block.Block;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public record EchoLocatorType(SoundEvent activateSound, SoundEvent resonateSound, TagKey<Block> canLocate) {

    public static final EchoLocatorType ALL = new EchoLocatorType(
            GeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE,
            GeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE,
            ModBlockTags.ECHO_LOCATABLE
    );

    public static final EchoLocatorType EMPTY = new EchoLocatorType(GeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE, GeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE, ModBlockTags.ECHO_LOCATABLE_DEFAULT);

    public static final EchoLocatorType EMERALD_GEODE_LOCATOR = new EchoLocatorType(GeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE, GeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE, ModBlockTags.ECHO_LOCATABLE_EMERALD);

    public static final EchoLocatorType QUARTZ_GEODE_LOCATOR = new EchoLocatorType(GeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE, GeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE, ModBlockTags.ECHO_LOCATABLE_QUARTZ);

    public static final EchoLocatorType DIAMOND_GEODE_LOCATOR = new EchoLocatorType(GeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE, GeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE, ModBlockTags.ECHO_LOCATABLE_DIAMOND);

    public static final EchoLocatorType AMETHYST_GEODE_LOCATOR = new EchoLocatorType(GeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE, GeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE, ModBlockTags.ECHO_LOCATABLE_AMETHYST);

    public static final EchoLocatorType ECHO_GEODE_LOCATOR = new EchoLocatorType(GeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE, GeodesSoundEvents.BLOCK_ECHO_LOCATOR_RESONATE, ModBlockTags.ECHO_LOCATABLE_ECHO);


    public static EchoLocatorType fromNbt(NbtCompound nbt) {
        TagKey<Block> canLocate = TagKey.of(Registry.BLOCK_KEY, new Identifier(nbt.getString("CanLocate")));
        SoundEvent activateSound = Registry.SOUND_EVENT.get(new Identifier(nbt.getString("ActivateSound")));
        SoundEvent resonateSound = Registry.SOUND_EVENT.get(new Identifier(nbt.getString("ResonateSound")));
        return new EchoLocatorType(activateSound, resonateSound, canLocate);
    }

    public NbtCompound toNbt() {
        NbtCompound nbt = new NbtCompound();
        nbt.putString("CanLocate", this.canLocate.id().toString());
        nbt.putString("ActivateSound", this.activateSound.getId().toString());
        nbt.putString("ResonateSound", this.resonateSound.getId().toString());
        return nbt;
    }
}
