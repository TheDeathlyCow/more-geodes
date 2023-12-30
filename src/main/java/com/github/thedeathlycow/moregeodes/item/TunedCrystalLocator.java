package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.item.tuning.Tuning;
import com.github.thedeathlycow.moregeodes.item.tuning.TuningRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
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
        Tuning tuning = this.getTuning(locatorStack);
        if (tuning == null) {
            return false;
        }
        return tuning.test(world, pos);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        Tuning tuning = this.getTuning(stack);
        if (tuning != null) {
            tooltip.add(tuning.getDescription());
        }
    }


    @Nullable
    private Tuning getTuning(ItemStack stack) {
        NbtCompound nbt = stack.getSubNbt(TUNING_NBT_KEY);

        if (nbt == null) {
            return null;
        }

        TuningRegistry registry = TuningRegistry.getInstance();

        return registry.getTuning(new Identifier(nbt.getString(TUNING_ID_NBT_KEY)));
    }
}
