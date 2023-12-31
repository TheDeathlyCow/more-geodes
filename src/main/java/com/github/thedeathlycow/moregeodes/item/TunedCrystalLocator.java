package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.item.tuning.Tuning;
import com.github.thedeathlycow.moregeodes.item.tuning.Tunings;
import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TunedCrystalLocator extends CrystalLocator {

    public static final String TUNING_NBT_KEY = "tuning";

    public static final String TUNING_ID_NBT_KEY = "id";

    public TunedCrystalLocator(Settings settings, int range) {
        super(settings, range);
    }

    @Override
    protected boolean isPingableCrystal(ItemStack locatorStack, ServerWorld world, BlockPos pos) {
        Tuning tuning = this.getTuning(world.getRegistryManager(), locatorStack);
        if (tuning == null) {
            return super.isPingableCrystal(locatorStack, world, pos);
        }
        return tuning.test(world, pos);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        Tuning tuning = null;
        if (world != null) {
            tuning = this.getTuning(world.getRegistryManager(), stack);
        }
        tooltip.add(tuning != null ? tuning.getDescription() : Tuning.UNTUNED.getDescription());

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Nullable
    public static Tuning getTuning(DynamicRegistryManager registryManager, ItemStack stack) {

        Registry<Tuning> tuningRegistry = registryManager.get(Tunings.REGISTRY_KEY);
        if (tuningRegistry == null) {
            return null;
        }

        NbtCompound nbt = stack.getSubNbt(TUNING_NBT_KEY);
        if (nbt == null) {
            return null;
        }

        return tuningRegistry.get(new Identifier(nbt.getString(TUNING_ID_NBT_KEY)));
    }
}
