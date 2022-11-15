package com.github.thedeathlycow.moregeodes.mixin;

import com.github.thedeathlycow.moregeodes.entity.FoolsGoldBarterer;
import com.github.thedeathlycow.moregeodes.tag.ModItemTags;
import net.minecraft.entity.ItemEntity;
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

import java.util.Optional;

@Mixin(PiglinBrain.class)
public abstract class FoolsGoldBarterPiglinBrainMixin {


    @Shadow
    protected static void becomeAngryWith(AbstractPiglinEntity piglin, LivingEntity target) {
    }

    @Shadow
    protected static void angerAtCloserTargets(AbstractPiglinEntity piglin, LivingEntity target) {
    }

    @Inject(
            method = "loot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/PiglinBrain;setAdmiringItem(Lnet/minecraft/entity/LivingEntity;)V",
                    shift = At.Shift.AFTER
            )
    )
    private static void piglinsRememberFoolsGold(PiglinEntity piglin, ItemEntity drop, CallbackInfo ci) {
        if (((FoolsGoldBarterer) piglin).geodes$remembersFoolsGold()) {
            piglin.getBrain().forget(MemoryModuleType.ADMIRING_ITEM);
            angerAtNearbyPlayers(piglin);
        }
    }

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
            angerAtNearbyPlayers(piglin);
            ((FoolsGoldBarterer) piglin).geodes$setRemembersFoolsGood(true);
        }
    }

    private static void angerAtNearbyPlayers(PiglinEntity piglin) {
        var brain = piglin.getBrain();
        Optional<PlayerEntity> rememberedPlayer = brain
                .getOptionalMemory(MemoryModuleType.NEAREST_VISIBLE_PLAYER);

        rememberedPlayer.ifPresent(player -> {
            becomeAngryWith(piglin, player);
            angerAtCloserTargets(piglin, player);
        });
    }
}
