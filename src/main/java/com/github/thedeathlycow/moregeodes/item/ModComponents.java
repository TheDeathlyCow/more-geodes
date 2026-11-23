package com.github.thedeathlycow.moregeodes.item;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
public final class ModComponents {
    public static final DataComponentType<Identifier> TUNING = Registry.register(
        BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath("geodes", "tuning"),
        DataComponentType.<Identifier>builder().persistent(Identifier.CODEC).networkSynchronized(Identifier.STREAM_CODEC).build());
    public static void register() {}
}
