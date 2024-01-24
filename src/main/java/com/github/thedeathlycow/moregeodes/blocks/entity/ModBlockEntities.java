package com.github.thedeathlycow.moregeodes.blocks.entity;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class ModBlockEntities {


    public static void registerBlockEntities() {
        // no block entities anymore!
        // I am keeping the code so that I can use it in the future if needed
    }

    private static void register(String name, BlockEntityType<?> blockEntityType) {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MoreGeodes.MODID, name), blockEntityType);
    }

}
