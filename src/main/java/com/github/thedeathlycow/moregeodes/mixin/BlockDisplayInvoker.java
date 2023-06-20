package com.github.thedeathlycow.moregeodes.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.decoration.DisplayEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DisplayEntity.BlockDisplayEntity.class)
public interface BlockDisplayInvoker {


    @Invoker("setBlockState")
    void geodes$setBlockState(BlockState state);

}
