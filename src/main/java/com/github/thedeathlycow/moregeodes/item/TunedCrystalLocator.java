package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.item.tuning.Tuning;
import com.github.thedeathlycow.moregeodes.item.tuning.TuningRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class TunedCrystalLocator extends CrystalLocator {

    public static final String TUNING_NBT_KEY = "tuning";

    public static final String TUNING_ID_NBT_KEY = "id";

    public TunedCrystalLocator(Settings settings, int range) {
        super(settings, range);
    }

    @Override
    protected boolean isPingableCrystal(ItemStack itemStack, BlockState state) {
        NbtCompound nbt = itemStack.getSubNbt(TUNING_NBT_KEY);

        if (nbt == null) {
            return false;
        }

        Tuning tuning = this.getTuningFromNbt(nbt);
        if (tuning == null) {
            return false;
        }

        return tuning.test(itemStack);
    }

    @Nullable
    private Tuning getTuningFromNbt(NbtCompound nbt) {
        TuningRegistry registry = TuningRegistry.getInstance();

        return registry.getTuning(new Identifier(nbt.getString(TUNING_ID_NBT_KEY)));
    }
}
