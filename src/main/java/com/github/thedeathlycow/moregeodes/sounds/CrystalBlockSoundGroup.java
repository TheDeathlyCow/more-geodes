package com.github.thedeathlycow.moregeodes.sounds;

import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public record CrystalBlockSoundGroup(
        SoundEvent hitSound,
        SoundEvent chimeSound
) {

    public static final CrystalBlockSoundGroup ECHO_BLOCK = new CrystalBlockSoundGroup(
            GeodesSoundEvents.BLOCK_ECHO_BLOCK_HIT,
            GeodesSoundEvents.BLOCK_ECHO_BLOCK_CHIME
    );

    public static final CrystalBlockSoundGroup DIAMOND_GEODE = new CrystalBlockSoundGroup(
            SoundEvents.BLOCK_AMETHYST_BLOCK_HIT,
            GeodesSoundEvents.BLOCK_DIAMOND_GEODE_CHIME
    );

    public static final CrystalBlockSoundGroup EMERALD_GEODE = new CrystalBlockSoundGroup(
            SoundEvents.BLOCK_AMETHYST_BLOCK_HIT,
            GeodesSoundEvents.BLOCK_EMERALD_GEODE_CHIME
    );

    public static final CrystalBlockSoundGroup QUARTZ_GEODE = new CrystalBlockSoundGroup(
            SoundEvents.BLOCK_AMETHYST_BLOCK_HIT,
            GeodesSoundEvents.BLOCK_QUARTZ_GEODE_CHIME
    );

    public static final CrystalBlockSoundGroup LAPIS = new CrystalBlockSoundGroup(
            SoundEvents.BLOCK_AMETHYST_BLOCK_HIT,
            GeodesSoundEvents.BLOCK_LAPIS_CRYSTAL_BLOCK_CHIME
    );
}
