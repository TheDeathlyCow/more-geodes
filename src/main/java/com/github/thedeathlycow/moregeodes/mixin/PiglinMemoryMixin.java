package com.github.thedeathlycow.moregeodes.mixin;

import com.github.thedeathlycow.moregeodes.entity.GeodesMemoryModules;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.mob.PiglinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Collection;

@Mixin(PiglinEntity.class)
public class PiglinMemoryMixin {

    @ModifyArg(
            method = "createBrainProfile",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/ai/brain/Brain;createProfile(Ljava/util/Collection;Ljava/util/Collection;)Lnet/minecraft/entity/ai/brain/Brain$Profile;"
            ),
            index = 0
    )
    private Collection<? extends MemoryModuleType<?>> registerFoolsGoldMemoryToPiglin(Collection<? extends MemoryModuleType<?>> memoryModules) {
        return new ImmutableList.Builder<MemoryModuleType<?>>()
                .addAll(memoryModules)
                .add(GeodesMemoryModules.REMEMBERS_FOOLS_GOLD)
                .build();
    }

}
