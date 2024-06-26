package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.tag.ModBlockTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EchoLocatorItem extends CrystalLocatorItem {

    public static final int BLOCK_RANGE = 6;

    public static final int ECHO_LOCATOR_COOL_DOWN = CrystalLocatorItem.CRYSTAL_LOCATOR_COOL_DOWN * 4;

    public EchoLocatorItem(Settings settings, int range, int coolDown) {
        super(settings, range, coolDown);
    }

    @Override
    public boolean isTuned(ItemStack stack, @Nullable World world) {
        return true;
    }

    @Override
    protected boolean isPingableCrystal(ItemStack locatorStack, ServerWorld world, BlockPos pos) {
        return world.getBlockState(pos).isIn(ModBlockTags.ECHO_LOCATABLE);
    }

    @Override
    protected void setCooldown(ItemCooldownManager cooldownManager, int ticks) {
        cooldownManager.set(this, ticks);
    }
}
