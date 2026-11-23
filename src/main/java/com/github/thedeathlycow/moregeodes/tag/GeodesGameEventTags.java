package com.github.thedeathlycow.moregeodes.tag;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.gameevent.GameEvent;

public class GeodesGameEventTags {

    public static final TagKey<GameEvent> ECHO_LOCATOR_CAN_LISTEN = of("echo_locator_can_listen");

    private static TagKey<GameEvent> of(String id) {
        return TagKey.create(Registries.GAME_EVENT, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, id));
    }

}
