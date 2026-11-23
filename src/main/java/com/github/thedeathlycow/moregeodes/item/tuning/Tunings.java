package com.github.thedeathlycow.moregeodes.item.tuning;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;

public class Tunings {

    public static final ResourceKey<Registry<Tuning>> REGISTRY_KEY = ResourceKey.createRegistryKey(
            Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "tunings")
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
