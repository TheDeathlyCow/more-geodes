package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.tag.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
public class EchoLocatorItem extends CrystalLocatorItem {
    public static final int BLOCK_RANGE=6,ECHO_LOCATOR_COOL_DOWN=CRYSTAL_LOCATOR_COOL_DOWN*4;
    public EchoLocatorItem(Properties props,int range,int cooldown) {super(props,range,cooldown);}
    @Override public boolean isTuned(ItemStack stack,Level level) {return true;}
    @Override protected boolean isPingableCrystal(ItemStack stack,ServerLevel level,BlockPos pos) {return level.getBlockState(pos).is(ModBlockTags.ECHO_LOCATABLE);}
    @Override protected void setCooldown(ItemCooldowns cooldowns,int ticks) {cooldowns.addCooldown(getDefaultInstance(),ticks);}
    @Override public void appendHoverText(ItemStack s,TooltipContext c,net.minecraft.world.item.component.TooltipDisplay d,java.util.function.Consumer<net.minecraft.network.chat.Component> o,TooltipFlag f) {}
}
