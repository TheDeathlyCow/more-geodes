package com.github.thedeathlycow.moregeodes.mixin;

import com.github.thedeathlycow.moregeodes.blocks.CrystalBlock;
import com.github.thedeathlycow.moregeodes.tag.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class StepSoundMixin {

    @Shadow private float crystalSoundIntensity;

    @Shadow private int lastCrystalSoundPlayTick;

    @Shadow public int tickCount;

    @Shadow @Final protected RandomSource random;

    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Inject(
            method = "walkingStepSound",
            at = @At("TAIL")
    )
    private void playCustomCrystalBlockChime(BlockPos pos, BlockState state, CallbackInfo ci) {

        if (state.is(ModBlockTags.CUSTOM_CRYSTAL_SOUND_BLOCKS) && this.tickCount >= this.lastCrystalSoundPlayTick + 20) {
            if (!(state.getBlock() instanceof CrystalBlock crystalBlock)) {
                return;
            }

            this.crystalSoundIntensity *= (float)Math.pow(0.997, this.tickCount - this.lastCrystalSoundPlayTick);
            this.crystalSoundIntensity = Math.min(1.0F, this.crystalSoundIntensity + 0.07F);
            float f = 0.5F + this.crystalSoundIntensity * this.random.nextFloat() * 1.2F;
            float g = 0.1F + this.crystalSoundIntensity * 1.2F;
            this.playSound(crystalBlock.getHitSoundGroup().chimeSound(), g, f);
            this.lastCrystalSoundPlayTick = this.tickCount;
        }
    }

}
