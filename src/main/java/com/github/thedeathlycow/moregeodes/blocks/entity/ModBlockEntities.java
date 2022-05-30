package com.github.thedeathlycow.moregeodes.blocks.entity;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {

    public static final BlockEntityType<EchoLocatorBlockEntity> ECHO_LOCATOR = FabricBlockEntityTypeBuilder.create(EchoLocatorBlockEntity::new, ModBlocks.ECHO_LOCATOR).build();

    public static void registerBlockEntities() {
        register("echo_locator", ECHO_LOCATOR);
    }

    private static void register(String name, BlockEntityType<?> blockEntityType) {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MoreGeodes.MODID, name), blockEntityType);
    }

}
