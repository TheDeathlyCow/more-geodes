package com.github.thedeathlycow.moregeodes.client;

import com.github.thedeathlycow.moregeodes.item.ModItems;
import com.github.thedeathlycow.moregeodes.item.TunedCrystalLocator;
import com.github.thedeathlycow.moregeodes.item.tuning.Tuning;
import com.github.thedeathlycow.moregeodes.item.tuning.Tunings;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.DynamicRegistryManager;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class ItemColorsSetup {

    @Nullable
    private static DynamicRegistryManager registryManager = null;

    public static void onInitialize() {
        DynamicRegistrySetupCallback.EVENT.register(
                registryView -> {
                    if (registryView.getOptional(Tunings.REGISTRY_KEY).isPresent()) {
                        registryManager = registryView.asDynamicRegistryManager();
                    }
                }
        );
    }

    public static void registerItemColors(ItemColors itemColors) {
        itemColors.register(
                ItemColorsSetup::getColor,
                ModItems.TUNED_CRYSTAL_LOCATOR
        );
    }

    private static int getColor(ItemStack stack, int tintIndex) {
        if (tintIndex == 0 && registryManager != null) {
            Tuning tuning = TunedCrystalLocator.getTuning(
                    registryManager, stack
            );
            return tuning != null
                    ? tuning.getColor()
                    : Tuning.UNTUNED.getColor();
        }
        return -1;
    }


    private ItemColorsSetup() {

    }

}
