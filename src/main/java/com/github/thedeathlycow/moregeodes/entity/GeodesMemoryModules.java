package com.github.thedeathlycow.moregeodes.entity;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.mojang.serialization.Codec;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Optional;

public class GeodesMemoryModules {

    public static final MemoryModuleType<Boolean> REMEMBERS_FOOLS_GOLD = new MemoryModuleType<>(Optional.of(Codec.BOOL));

    public static void registerModules() {
        register("remembers_fools_gold", REMEMBERS_FOOLS_GOLD);
    }


    private static void register(String id, MemoryModuleType<?> moduleType) {
        Registry.register(
                Registry.MEMORY_MODULE_TYPE,
                new Identifier(MoreGeodes.MODID, id),
                moduleType
        );
    }
}
