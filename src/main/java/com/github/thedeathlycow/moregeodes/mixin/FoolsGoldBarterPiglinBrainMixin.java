package com.github.thedeathlycow.moregeodes.mixin;

import com.github.thedeathlycow.moregeodes.entity.GeodesMemoryModules;
import com.github.thedeathlycow.moregeodes.tag.ModItemTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
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
public abstract class FoolsGoldBarterPiglinBrainMixin {


    @Shadow
    protected static void becomeAngryWith(AbstractPiglinEntity piglin, LivingEntity target) {
    }

    @Shadow
    protected static void angerAtCloserTargets(AbstractPiglinEntity piglin, LivingEntity target) {
    }

    @Inject(
            method = "canGather",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void piglinsRememberFoolsGold(
            PiglinEntity piglin, ItemStack stack,
            CallbackInfoReturnable<Boolean> cir
    ) {
        Brain<PiglinEntity> brain = piglin.getBrain();
        Optional<Boolean> memory = brain.getOptionalMemory(GeodesMemoryModules.REMEMBERS_FOOLS_GOLD);

        boolean remembersFoolsGold = brain.hasMemoryModule(GeodesMemoryModules.REMEMBERS_FOOLS_GOLD)
                && memory != null // NOSONAR - why tf is there a nullable optional
                && memory.isPresent()
                && memory.get();

        if (remembersFoolsGold && stack.isIn(ModItemTags.FOOLS_GOLD)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "consumeOffHandItem",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void angerPiglinWhenBarterWithFoolsGold(PiglinEntity piglin, boolean barter, CallbackInfo ci) {
        if (!barter) {
            return;
        }

        ItemStack stack = piglin.getStackInHand(Hand.OFF_HAND);
        if (stack.isIn(ModItemTags.FOOLS_GOLD)) {
            angerAtNearbyPlayers(piglin);
            piglin.getBrain().remember(GeodesMemoryModules.REMEMBERS_FOOLS_GOLD, true);
            stack.setCount(0);
            piglin.swingHand(Hand.OFF_HAND);
            ci.cancel();
        }
    }

    private static void angerAtNearbyPlayers(PiglinEntity piglin) {
        Brain<PiglinEntity> brain = piglin.getBrain();
        Optional<PlayerEntity> rememberedPlayer = brain
                .getOptionalMemory(MemoryModuleType.NEAREST_VISIBLE_PLAYER);

        rememberedPlayer.ifPresent(
                player -> {
                    becomeAngryWith(piglin, player);
                    angerAtCloserTargets(piglin, player);
                }
        );
    }
}
