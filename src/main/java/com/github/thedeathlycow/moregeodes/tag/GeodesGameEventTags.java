package com.github.thedeathlycow.moregeodes.tag;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.event.GameEvent;

public class GeodesGameEventTags {

    public static final TagKey<GameEvent> ECHO_LOCATOR_CAN_LISTEN = of("echo_locator_can_listen");

    private static TagKey<GameEvent> of(String id) {
        return TagKey.of(RegistryKeys.GAME_EVENT, new Identifier(MoreGeodes.MODID, id));
    }

}
