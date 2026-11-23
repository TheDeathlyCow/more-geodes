package com.github.thedeathlycow.moregeodes.entity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.*;
public final class GeodesEntityTypes {
    public static final ResourceKey<EntityType<?>> ECHO_DISPLAY_KEY=ResourceKey.create(Registries.ENTITY_TYPE,Identifier.fromNamespaceAndPath("geodes","echo_display"));
    public static final EntityType<EchoDisplay> ECHO_DISPLAY=Registry.register(BuiltInRegistries.ENTITY_TYPE,ECHO_DISPLAY_KEY,
        EntityType.Builder.<EchoDisplay>of(EchoDisplay::new,MobCategory.MISC).sized(0f,0f).clientTrackingRange(10).updateInterval(1).build(ECHO_DISPLAY_KEY));
    public static void registerAll() {}
}
