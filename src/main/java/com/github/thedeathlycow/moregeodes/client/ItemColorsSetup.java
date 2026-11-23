package com.github.thedeathlycow.moregeodes.client;

import com.github.thedeathlycow.moregeodes.item.TunedCrystalLocatorItem;
import com.github.thedeathlycow.moregeodes.item.tuning.Tuning;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.color.item.*;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
public final class ItemColorsSetup {
    public static void onInitialize() {ItemTintSources.ID_MAPPER.put(Identifier.fromNamespaceAndPath("geodes","tuning"),TuningTint.CODEC);}
    public record TuningTint() implements ItemTintSource {
        public static final MapCodec<TuningTint> CODEC=MapCodec.unit(new TuningTint());
        @Override public int calculate(ItemStack stack,ClientLevel level,LivingEntity owner) {
            Tuning tuning=level==null?null:TunedCrystalLocatorItem.getTuning(level.registryAccess(),stack);
            return 0xff000000 | (tuning==null?Tuning.UNTUNED.getColor():tuning.getColor());
        }
        @Override public MapCodec<TuningTint> type() {return CODEC;}
    }
}
