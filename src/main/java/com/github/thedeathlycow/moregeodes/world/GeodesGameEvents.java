package com.github.thedeathlycow.moregeodes.world;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.event.GameEvent;

public class GeodesGameEvents {

    public static final GameEvent CRYSTAL_RESONATE = of("crystal_resonate");
    public static void registerEvents() {
        register(CRYSTAL_RESONATE);
    }

    private static void register(GameEvent event) {
        Registry.register(Registries.GAME_EVENT, event.getId(), event);
    }

    public static GameEvent of(String name) {
        return of(name, 16);
    }

    public static GameEvent of(String name, int range) {
        return new GameEvent(String.format("%s:%s", MoreGeodes.MODID, name), range);
    }
}
