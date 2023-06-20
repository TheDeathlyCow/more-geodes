package com.github.thedeathlycow.moregeodes.entity;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class GeodesEntityTypes {

    public static final EntityType<EchoDisplay> ECHO_DISPLAY = FabricEntityTypeBuilder.create(
                    SpawnGroup.CREATURE,
                    EchoDisplay::new
            )
            .dimensions(EntityDimensions.fixed(0.0f, 0.0f))
            .build();

    public static void registerAll() {
        register("echo_display", ECHO_DISPLAY);
    }

    private static <T extends Entity> void register(String id, EntityType<T> type) {
        Registry.register(Registries.ENTITY_TYPE, new Identifier(MoreGeodes.MODID, id), type);
    }

    private GeodesEntityTypes() {

    }
}
