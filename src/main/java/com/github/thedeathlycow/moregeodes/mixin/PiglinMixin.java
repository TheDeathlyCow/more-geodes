package com.github.thedeathlycow.moregeodes.mixin;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.entity.FoolsGoldBarterer;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiglinEntity.class)
public class PiglinMixin implements FoolsGoldBarterer {

    private static final String REMEMBERS_FOOLS_GOLD_KEY = "RemembersFoolsGold";

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
        NbtCompound geodes = new NbtCompound();
        geodes.putBoolean(REMEMBERS_FOOLS_GOLD_KEY, this.geodes$remembersFoolsGold());
        nbt.put(MoreGeodes.MODID, geodes);
    }

    @Inject(
            method = "readCustomDataFromNbt",
            at = @At("TAIL")
    )
    private void readModData(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("geodes")) {
            NbtCompound geodes = nbt.getCompound(MoreGeodes.MODID);
            if (geodes.contains(REMEMBERS_FOOLS_GOLD_KEY)) {
                this.geodes$setRemembersFoolsGood(geodes.getBoolean(REMEMBERS_FOOLS_GOLD_KEY));
            }
        } else {
            this.geodes$setRemembersFoolsGood(false);
        }
    }
}
