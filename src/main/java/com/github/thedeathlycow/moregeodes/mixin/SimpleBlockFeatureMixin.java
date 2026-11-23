package com.github.thedeathlycow.moregeodes.mixin;

import com.github.thedeathlycow.moregeodes.blocks.*;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(SimpleBlockFeature.class)
public class SimpleBlockFeatureMixin {
    @Inject(method="place",at=@At(value="INVOKE",target="Lnet/minecraft/world/level/WorldGenLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"),cancellable=true)
    private void geodes$placeTallCrystal(WorldGenLevel level,ChunkGenerator generator,RandomSource random,BlockPos pos,
            CallbackInfoReturnable<Boolean> result,@Local(ordinal=0) BlockState state) {
        if(state.getBlock() instanceof LargeCrystalClusterBlock) {
            if(!level.isEmptyBlock(pos.relative(state.getValue(CrystalClusterBlock.FACING)))) {
                result.setReturnValue(false);
            } else {
                LargeCrystalClusterBlock.placeAt(level,state,pos,Block.UPDATE_CLIENTS);
                if(((SimpleBlockFeature)(Object)this).scheduleTick()) level.scheduleTick(pos,state.getBlock(),1);
                result.setReturnValue(true);
            }
        }
    }
}
