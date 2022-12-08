package com.github.thedeathlycow.moregeodes.mixin;

import com.github.thedeathlycow.moregeodes.blocks.CrystalClusterBlock;
import com.github.thedeathlycow.moregeodes.blocks.LargeCrystalClusterBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.SimpleBlockFeature;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SimpleBlockFeature.class)
public class SimpleBlockFeatureMixin {

    @Inject(
            method = "generate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/StructureWorldAccess;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z",
                    shift = At.Shift.BEFORE
            ),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILEXCEPTION
    )
    private void placeTallClusterBlockUpperHalf(FeatureContext<SimpleBlockFeatureConfig> context, CallbackInfoReturnable<Boolean> cir, SimpleBlockFeatureConfig simpleBlockFeatureConfig, StructureWorldAccess structureWorldAccess, BlockPos blockPos, BlockState blockState) {
        Block block = blockState.getBlock();
        if (block instanceof LargeCrystalClusterBlock) {
            Direction facing = blockState.get(CrystalClusterBlock.FACING);
            if (!structureWorldAccess.isAir(blockPos.offset(facing))) {
                cir.setReturnValue(false);
                return;
            }

            LargeCrystalClusterBlock.placeAt(structureWorldAccess, blockState, blockPos, Block.NOTIFY_LISTENERS);

            cir.setReturnValue(true);
        }


    }

}
