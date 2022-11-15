package com.github.thedeathlycow.moregeodes.mixin;

import com.github.thedeathlycow.moregeodes.entity.FoolsGoldBarterer;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiglinEntity.class)
public class PiglinMixin implements FoolsGoldBarterer {

    private boolean geodes$remembersFoolsGold = false;

    @Override
    public void geodes$setRemembersFoolsGood(boolean value) {
        geodes$remembersFoolsGold = value;
    }

    @Override
    public boolean geodes$remembersFoolsGold() {
        return geodes$remembersFoolsGold;
    }

    @Inject(
            method = "writeCustomDataToNbt",
            at = @At("TAIL")
    )
    private void writeModData(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("Geodes$RemembersFoolsGold", this.geodes$remembersFoolsGold());
    }

    @Inject(
            method = "readCustomDataFromNbt",
            at = @At("TAIL")
    )
    private void readModData(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("Geodes$RemembersFoolsGold")) {
            this.geodes$setRemembersFoolsGood(nbt.getBoolean("Geodes$RemembersFoolsGold"));
        } else {
            this.geodes$setRemembersFoolsGood(false);
        }
    }
}
