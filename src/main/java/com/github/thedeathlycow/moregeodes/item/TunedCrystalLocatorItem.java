package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.item.tuning.Tuning;
import com.github.thedeathlycow.moregeodes.item.tuning.Tunings;
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

public class TunedCrystalLocatorItem extends CrystalLocatorItem {

    public static final String TUNING_NBT_KEY = "tuning";

    public static final String TUNING_ID_NBT_KEY = "id";

    public TunedCrystalLocatorItem(Settings settings, int range, int coolDown) {
        super(settings, range, coolDown);
    }

    @Override
    public boolean isTuned(ItemStack stack, @Nullable World world) {
        return world != null && getTuning(world.getRegistryManager(), stack) != null;
    }

    @Override
    protected boolean isPingableCrystal(ItemStack locatorStack, ServerWorld world, BlockPos pos) {
        Tuning tuning = getTuning(world.getRegistryManager(), locatorStack);
        if (tuning == null) {
            return super.isPingableCrystal(locatorStack, world, pos);
        }
        return tuning.test(world, pos);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        Tuning tuning = null;
        if (world != null) {
            tuning = getTuning(world.getRegistryManager(), stack);
        }

        if (tuning != null) {
            tooltip.add(tuning.getDescription());
        }

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
