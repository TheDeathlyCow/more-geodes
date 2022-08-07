package com.github.thedeathlycow.moregeodes.sounds;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class GeodesSoundEvents {

    public static final SoundEvent CRYSTAL_RESONATE = GeodesSoundEvents.of("block.geodes.echo_locator.resonate");
    public static final SoundEvent BLOCK_ECHO_BLOCK_BREAK = GeodesSoundEvents.of("block.geodes.echo_block.break");
    public static final SoundEvent BLOCK_ECHO_BLOCK_PLACE = GeodesSoundEvents.of("block.geodes.echo_block.place");

    public static void registerSoundEvents() {
        register(CRYSTAL_RESONATE);
        register(BLOCK_ECHO_BLOCK_BREAK);
        register(BLOCK_ECHO_BLOCK_PLACE);
    }

    private static void register(SoundEvent event) {
        Registry.register(Registry.SOUND_EVENT, event.getId(), event);
    }

    private static SoundEvent of(String name) {
        return new SoundEvent(new Identifier(MoreGeodes.MODID, name));
    }

}
