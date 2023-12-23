package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.entity.EchoDisplay;
import com.github.thedeathlycow.moregeodes.entity.GeodesEntityTypes;
import com.github.thedeathlycow.moregeodes.mixin.BlockDisplayInvoker;
import com.github.thedeathlycow.moregeodes.sounds.GeodesSoundEvents;
import com.github.thedeathlycow.moregeodes.tag.ModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class CrystalLocator extends Item {

    private static final int COOL_DOWN = 20;
    private final int range;

    public CrystalLocator(Settings settings, int range) {
        super(settings);
        this.range = range;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            this.activate(world, user.getBlockPos());
            itemStack.damage(1, user, player -> player.sendToolBreakStatus(hand));
            user.getItemCooldownManager().set(this, COOL_DOWN);
        }
        user.playSound(GeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE, user.getSoundCategory(), 1.0f, 1.0f);

        return TypedActionResult.success(itemStack, world.isClient());
    }

    protected boolean isPingableCrystal(BlockState state) {
        return state.isIn(ModBlockTags.ECHO_LOCATABLE);
    }

    private void activate(World world, BlockPos origin) {

        var scanVector = new Vec3i(range, range, range);
        BlockPos from = origin.subtract(scanVector);
        BlockPos to = origin.add(scanVector);
        final int rangeSquared = this.range * this.range;

        for (BlockPos pos : BlockPos.iterate(from, to)) {
            double distanceToPos = origin.getSquaredDistance(pos);
            if (distanceToPos <= rangeSquared) {
                this.tryPing(world, pos, distanceToPos);
            }
        }
    }

    private void tryPing(World world, BlockPos pos, double distanceToPosSquared) {
        if (this.isPingableCrystal(world.getBlockState(pos))) {
            int delay = MathHelper.ceil(Math.sqrt(distanceToPosSquared)) * 10;
            highlightCrystal(
                    world,
                    pos,
                    world.getBlockState(pos),
                    delay
            );
        }
    }

    private static void highlightCrystal(
            World world,
            BlockPos pos,
            BlockState state,
            int delay
    ) {
        EchoDisplay blockDisplay = GeodesEntityTypes.ECHO_DISPLAY.create(world);

        if (blockDisplay == null) {
            return;
        }

        ((BlockDisplayInvoker) blockDisplay).geodes$setBlockState(state);
        blockDisplay.setPos(pos.getX(), pos.getY(), pos.getZ());
        blockDisplay.setGlowDelay(delay);

        world.spawnEntity(blockDisplay);
    }
}
