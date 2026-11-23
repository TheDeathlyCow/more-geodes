package com.github.thedeathlycow.moregeodes.entity;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.mojang.serialization.Codec;
import java.util.Optional;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

public class GeodesMemoryModules {

    public static final MemoryModuleType<Boolean> REMEMBERS_FOOLS_GOLD = new MemoryModuleType<>(Optional.of(Codec.BOOL));

    public static void registerModules() {
        register("remembers_fools_gold", REMEMBERS_FOOLS_GOLD);
    }


    private static void register(String id, MemoryModuleType<?> moduleType) {
        Registry.register(
                BuiltInRegistries.MEMORY_MODULE_TYPE,
                Identifier.fromNamespaceAndPath(MoreGeodes.MODID, id),
                moduleType
        );
    }
}
