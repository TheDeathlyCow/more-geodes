package com.github.thedeathlycow.moregeodes.world;

import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gameevent.GameEvent;
public final class GeodesGameEvents {
    public static final Holder.Reference<GameEvent> CRYSTAL_RESONATE=Registry.registerForHolder(BuiltInRegistries.GAME_EVENT,Identifier.fromNamespaceAndPath("geodes","crystal_resonate"),new GameEvent(16));
    public static void registerEvents() {}
}
