package com.github.thedeathlycow.moregeodes.sounds;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class GeodesSoundEvents {

    public static final SoundEvent BLOCK_ECHO_LOCATOR_RESONATE = GeodesSoundEvents.of("block.geodes.echo_locator.resonate");
    public static final SoundEvent BLOCK_ECHO_LOCATOR_USE = GeodesSoundEvents.of("block.geodes.echo_locator.use");

    public static final SoundEvent BLOCK_EMERALD_GEODE_CHIME = GeodesSoundEvents.of("block.geodes.emerald_geode.chime");
    public static final SoundEvent BLOCK_QUARTZ_GEODE_CHIME = GeodesSoundEvents.of("block.geodes.quartz_geode.chime");
    public static final SoundEvent BLOCK_DIAMOND_GEODE_CHIME = GeodesSoundEvents.of("block.geodes.diamond_geode.chime");
    public static final SoundEvent BLOCK_LAPIS_CRYSTAL_BLOCK_CHIME = GeodesSoundEvents.of("block.geodes.lapis_crystal_block.chime");
    public static final SoundEvent BLOCK_GYPSUM_CRYSTAL_BLOCK_CHIME = GeodesSoundEvents.of("block.geodes.gypsum_crystal_block.chime");


    public static final SoundEvent BLOCK_ECHO_BLOCK_BREAK = GeodesSoundEvents.of("block.geodes.echo_block.break");
    public static final SoundEvent BLOCK_ECHO_BLOCK_CHIME = GeodesSoundEvents.of("block.geodes.echo_block.chime");
    public static final SoundEvent BLOCK_ECHO_BLOCK_FALL = GeodesSoundEvents.of("block.geodes.echo_block.fall");
    public static final SoundEvent BLOCK_ECHO_BLOCK_HIT = GeodesSoundEvents.of("block.geodes.echo_block.hit");
    public static final SoundEvent BLOCK_ECHO_BLOCK_PLACE = GeodesSoundEvents.of("block.geodes.echo_block.place");
    public static final SoundEvent BLOCK_ECHO_BLOCK_STEP = GeodesSoundEvents.of("block.geodes.echo_block.step");

    public static final SoundEvent BLOCK_ECHO_CLUSTER_BREAK = GeodesSoundEvents.of("block.geodes.echo_cluster.break");
    public static final SoundEvent BLOCK_ECHO_CLUSTER_FALL = GeodesSoundEvents.of("block.geodes.echo_cluster.fall");
    public static final SoundEvent BLOCK_ECHO_CLUSTER_HIT = GeodesSoundEvents.of("block.geodes.echo_cluster.hit");
    public static final SoundEvent BLOCK_ECHO_CLUSTER_PLACE = GeodesSoundEvents.of("block.geodes.echo_cluster.place");
    public static final SoundEvent BLOCK_ECHO_CLUSTER_STEP = GeodesSoundEvents.of("block.geodes.echo_cluster.step");

    public static final SoundEvent BLOCK_LARGE_ECHO_BUD_BREAK = GeodesSoundEvents.of("block.geodes.large_echo_bud.break");
    public static final SoundEvent BLOCK_LARGE_ECHO_BUD_PLACE = GeodesSoundEvents.of("block.geodes.large_echo_bud.place");

    public static final SoundEvent BLOCK_MEDIUM_ECHO_BUD_BREAK = GeodesSoundEvents.of("block.geodes.medium_echo_bud.break");
    public static final SoundEvent BLOCK_MEDIUM_ECHO_BUD_PLACE = GeodesSoundEvents.of("block.geodes.medium_echo_bud.place");

    public static final SoundEvent BLOCK_SMALL_ECHO_BUD_BREAK = GeodesSoundEvents.of("block.geodes.small_echo_bud.break");
    public static final SoundEvent BLOCK_SMALL_ECHO_BUD_PLACE = GeodesSoundEvents.of("block.geodes.small_echo_bud.place");


    public static void registerSoundEvents() {
        register(BLOCK_ECHO_LOCATOR_RESONATE);
        register(BLOCK_ECHO_LOCATOR_USE);

        register(BLOCK_EMERALD_GEODE_CHIME);
        register(BLOCK_QUARTZ_GEODE_CHIME);
        register(BLOCK_DIAMOND_GEODE_CHIME);
        register(BLOCK_LAPIS_CRYSTAL_BLOCK_CHIME);
        register(BLOCK_GYPSUM_CRYSTAL_BLOCK_CHIME);

        register(BLOCK_ECHO_BLOCK_BREAK);
        register(BLOCK_ECHO_BLOCK_CHIME);
        register(BLOCK_ECHO_BLOCK_FALL);
        register(BLOCK_ECHO_BLOCK_HIT);
        register(BLOCK_ECHO_BLOCK_PLACE);
        register(BLOCK_ECHO_BLOCK_STEP);

        register(BLOCK_ECHO_CLUSTER_BREAK);
        register(BLOCK_ECHO_CLUSTER_FALL);
        register(BLOCK_ECHO_CLUSTER_HIT);
        register(BLOCK_ECHO_CLUSTER_PLACE);
        register(BLOCK_ECHO_CLUSTER_STEP);

        register(BLOCK_LARGE_ECHO_BUD_BREAK);
        register(BLOCK_LARGE_ECHO_BUD_PLACE);

        register(BLOCK_MEDIUM_ECHO_BUD_BREAK);
        register(BLOCK_MEDIUM_ECHO_BUD_PLACE);

        register(BLOCK_SMALL_ECHO_BUD_BREAK);
        register(BLOCK_SMALL_ECHO_BUD_PLACE);
    }

    private static void register(SoundEvent event) {
        Registry.register(Registries.SOUND_EVENT, event.getId(), event);
    }

    private static SoundEvent of(String name) {
        return SoundEvent.of(new Identifier(MoreGeodes.MODID, name));
    }

}
