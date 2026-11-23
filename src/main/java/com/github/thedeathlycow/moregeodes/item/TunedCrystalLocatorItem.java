package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.item.tuning.*;
import java.util.function.Consumer;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
public class TunedCrystalLocatorItem extends CrystalLocatorItem {
    public static final String TUNING_NBT_KEY="tuning", TUNING_ID_NBT_KEY="id";
    public TunedCrystalLocatorItem(Properties properties,int range,int cooldown) { super(properties,range,cooldown); }
    /** Vanilla upgrades unknown pre-component item tags into custom_data. */
    public static @Nullable Identifier tuningId(ItemStack stack) {
        Identifier id=stack.get(ModComponents.TUNING);
        if(id!=null) return id;
        CustomData legacy=stack.get(DataComponents.CUSTOM_DATA);
        if(legacy==null) return null;
        CompoundTag tuning=legacy.copyTag().getCompoundOrEmpty(TUNING_NBT_KEY);
        return Identifier.tryParse(tuning.getStringOr(TUNING_ID_NBT_KEY,""));
    }
    public static @Nullable Tuning getTuning(HolderLookup.Provider registries, ItemStack stack) {
        Identifier id=tuningId(stack);
        if(id==null) return null;
        return registries.lookup(Tunings.REGISTRY_KEY).flatMap(r -> r.get(net.minecraft.resources.ResourceKey.create(Tunings.REGISTRY_KEY,id))).map(net.minecraft.core.Holder::value).orElse(null);
    }
    @Override public boolean isTuned(ItemStack stack,@Nullable Level level) { return level!=null && getTuning(level.registryAccess(),stack)!=null; }
    @Override protected boolean isPingableCrystal(ItemStack stack,ServerLevel level,BlockPos pos) {
        Tuning tuning=getTuning(level.registryAccess(),stack);
        return tuning!=null && tuning.test(level,pos);
    }
    @Override public void appendHoverText(ItemStack stack,TooltipContext context,TooltipDisplay display,Consumer<Component> output,TooltipFlag flag) {
        Tuning tuning=getTuning(context.registries(),stack);
        output.accept(tuning!=null?tuning.getDescription():Tuning.UNTUNED.getDescription());
    }
}
