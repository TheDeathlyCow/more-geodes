package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.entity.EchoDisplay;
import com.github.thedeathlycow.moregeodes.entity.GeodesEntityTypes;
import com.github.thedeathlycow.moregeodes.mixin.BlockDisplayInvoker;
import com.github.thedeathlycow.moregeodes.sounds.GeodesSoundEvents;
import com.github.thedeathlycow.moregeodes.tag.ModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class CrystalLocator extends Item {

    public static final int BASE_RANGE = 48;
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
            int blocksPinged = this.activate((ServerWorld) world, user.getBlockPos(), itemStack);

            if (blocksPinged > 0) {
                itemStack.damage(1, user, player -> player.sendToolBreakStatus(hand));
            }

            ItemCooldownManager cooldownManager = user.getItemCooldownManager();
            cooldownManager.set(ModItems.CRYSTAL_LOCATOR, COOL_DOWN);
            cooldownManager.set(ModItems.TUNED_CRYSTAL_LOCATOR, COOL_DOWN);
            user.playSound(GeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE, user.getSoundCategory(), 1.0f, 1.0f);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    protected boolean isPingableCrystal(ItemStack locatorStack, ServerWorld world, BlockPos pos) {
        return world.getBlockState(pos).isIn(ModBlockTags.ECHO_LOCATABLE);
    }

    private int activate(ServerWorld world, BlockPos origin, ItemStack itemStack) {

        var scanVector = new Vec3i(range, range, range);
        BlockPos from = origin.subtract(scanVector);
        BlockPos to = origin.add(scanVector);
        final int rangeSquared = this.range * this.range;
        int blocksPinged = 0;

        for (BlockPos pos : BlockPos.iterate(from, to)) {
            double distanceToPos = origin.getSquaredDistance(pos) * 3;
            if (distanceToPos <= rangeSquared) {
                blocksPinged += this.tryPing(world, pos, itemStack, distanceToPos);
            }
        }

        return blocksPinged;
    }

    private int tryPing(ServerWorld world, BlockPos pos, ItemStack itemStack, double distanceToPosSquared) {
        if (this.isPingableCrystal(itemStack, world, pos)) {
            int delay = MathHelper.ceil(Math.sqrt(distanceToPosSquared));
            highlightCrystal(
                    world,
                    pos,
                    world.getBlockState(pos),
                    delay
            );
            return 1;
        }
        return 0;
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
