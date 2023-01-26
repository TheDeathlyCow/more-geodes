package com.github.thedeathlycow.moregeodes.blocks.entity;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class ModBlockEntities {

    public static final BlockEntityType<EchoLocatorBlockEntity> ECHO_LOCATOR =
            FabricBlockEntityTypeBuilder.create(
                            EchoLocatorBlockEntity::new,
                            ModBlocks.EMERALD_CRYSTAL_ECHO_LOCATOR,
                            ModBlocks.QUARTZ_CRYSTAL_ECHO_LOCATOR,
                            ModBlocks.AMETHYST_CRYSTAL_ECHO_LOCATOR,
                            ModBlocks.DIAMOND_CRYSTAL_ECHO_LOCATOR,
                            ModBlocks.ECHO_CRYSTAL_ECHO_LOCATOR,
                            ModBlocks.ECHO_LOCATOR
                    )
                    .build();

    public static void registerBlockEntities() {
        register("echo_locator", ECHO_LOCATOR);
    }

    private static void register(String name, BlockEntityType<?> blockEntityType) {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MoreGeodes.MODID, name), blockEntityType);
    }

}
