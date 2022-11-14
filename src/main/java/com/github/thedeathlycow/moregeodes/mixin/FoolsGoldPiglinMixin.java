package com.github.thedeathlycow.moregeodes.mixin;

import com.github.thedeathlycow.moregeodes.entity.GeodesMemoryModuleTypes;
import com.github.thedeathlycow.moregeodes.tag.ModItemTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PiglinBrain.class)
public abstract class FoolsGoldPiglinMixin {


    @Shadow
    protected static void becomeAngryWith(AbstractPiglinEntity piglin, LivingEntity target) {
    }

    @Shadow
    protected static void angerAtCloserTargets(AbstractPiglinEntity piglin, LivingEntity target) {
    }

//    @Inject(
//            method = "isGoldenItem",
//            at = @At("HEAD"),
//            cancellable = true
//    )
//    private static void piglinsRememberFoolsGold(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
//
//    }

    @Inject(
            method = "consumeOffHandItem",
            at = @At("HEAD")
    )
    private static void angerPiglinWhenBarterWithFoolsGold(PiglinEntity piglin, boolean barter, CallbackInfo ci) {
        if (!barter) {
            return;
        }

        ItemStack holding = piglin.getStackInHand(Hand.OFF_HAND);


        if (holding.isIn(ModItemTags.FOOLS_GOLD)) {
            var brain = piglin.getBrain();
            Optional<PlayerEntity> rememberedPlayer = brain
                    .getOptionalMemory(MemoryModuleType.NEAREST_VISIBLE_PLAYER);

            rememberedPlayer.ifPresent(player -> {
                becomeAngryWith(piglin, player);
                angerAtCloserTargets(piglin, player);
            });

            brain.remember(GeodesMemoryModuleTypes.PICKED_UP_FOOLS_GOLD, true);
        }
    }
}
