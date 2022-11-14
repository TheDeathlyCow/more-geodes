package com.github.thedeathlycow.moregeodes.entity;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.mojang.serialization.Codec;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Optional;

public class GeodesMemoryModuleTypes {

    public static final MemoryModuleType<Boolean> PICKED_UP_FOOLS_GOLD = new MemoryModuleType<>(Optional.of(Codec.BOOL));

    public static void registerMemoryModuleTypes() {
        register("picked_up_fools_gold", PICKED_UP_FOOLS_GOLD);
    }

    private static void register(String name, MemoryModuleType<?> memoryModuleType) {
        Registry.register(Registry.MEMORY_MODULE_TYPE, new Identifier(MoreGeodes.MODID, name), memoryModuleType);
    }

}
