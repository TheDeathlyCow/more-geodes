package com.github.thedeathlycow.moregeodes.sounds;

import net.minecraft.sound.SoundEvent;

public record CrystalBlockSoundGroup(
        SoundEvent hitSound,
        SoundEvent chimeSound
) {

    public static final CrystalBlockSoundGroup ECHO_BLOCK = new CrystalBlockSoundGroup(
            GeodesSoundEvents.BLOCK_ECHO_BLOCK_HIT,
            GeodesSoundEvents.BLOCK_ECHO_BLOCK_CHIME
    );

}
