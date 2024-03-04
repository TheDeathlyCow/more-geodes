package com.github.thedeathlycow.moregeodes.item.tuning;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class Tunings {

    public static final RegistryKey<Registry<Tuning>> REGISTRY_KEY = RegistryKey.ofRegistry(
            new Identifier(MoreGeodes.MODID, "tunings")
    );

    public static void onInitialize() {
        DynamicRegistries.registerSynced(
                REGISTRY_KEY,
                Tuning.DATAPACK_CODEC,
                Tuning.NETWORK_CODEC
        );
    }

    private Tunings() {

    }
}
