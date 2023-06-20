package com.github.thedeathlycow.moregeodes.mixin;

import com.github.thedeathlycow.moregeodes.blocks.CrystalBlock;
import com.github.thedeathlycow.moregeodes.tag.ModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class StepSoundMixin {

    @Shadow private float lastChimeIntensity;

    @Shadow private int lastChimeAge;

    @Shadow public int age;

    @Shadow @Final protected Random random;

    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Inject(
            method = "playStepSounds",
            at = @At("TAIL")
    )
    private void playCustomCrystalBlockChime(BlockPos pos, BlockState state, CallbackInfo ci) {

        if (state.isIn(ModBlockTags.CUSTOM_CRYSTAL_SOUND_BLOCKS) && this.age >= this.lastChimeAge + 20) {
            if (!(state.getBlock() instanceof CrystalBlock crystalBlock)) {
                return;
            }

            this.lastChimeIntensity *= (float)Math.pow(0.997, this.age - this.lastChimeAge);
            this.lastChimeIntensity = Math.min(1.0F, this.lastChimeIntensity + 0.07F);
            float f = 0.5F + this.lastChimeIntensity * this.random.nextFloat() * 1.2F;
            float g = 0.1F + this.lastChimeIntensity * 1.2F;
            this.playSound(crystalBlock.getHitSoundGroup().chimeSound(), g, f);
            this.lastChimeAge = this.age;
        }
    }

}
