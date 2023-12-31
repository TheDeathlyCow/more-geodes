package com.github.thedeathlycow.moregeodes.mixin.client;

import com.github.thedeathlycow.moregeodes.client.ItemColorsSetup;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(ItemColors.class)
public class ItemColorsMixin {


    @Inject(
            method = "create",
            at = @At("TAIL")
    )
    private static void registerCustomColors(BlockColors blockColors, CallbackInfoReturnable<ItemColors> cir) {
        ItemColors colors = cir.getReturnValue();
        ItemColorsSetup.registerItemColors(colors);
    }

}
