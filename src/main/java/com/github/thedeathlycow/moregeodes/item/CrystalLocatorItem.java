package com.github.thedeathlycow.moregeodes.item;

import com.github.thedeathlycow.moregeodes.entity.EchoDisplay;
import com.github.thedeathlycow.moregeodes.entity.GeodesEntityTypes;
import com.github.thedeathlycow.moregeodes.item.tuning.Tuning;
import com.github.thedeathlycow.moregeodes.mixin.BlockDisplayInvoker;
import com.github.thedeathlycow.moregeodes.sounds.GeodesSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrystalLocatorItem extends Item {

    public static final int CRYSTAL_LOCATOR_RANGE = 48;
    public static final int CRYSTAL_LOCATOR_COOL_DOWN = 20;
    private final int range;
    private final int coolDown;

    public CrystalLocatorItem(Settings settings, int range, int coolDown) {
        super(settings);
        this.range = range;
        this.coolDown = coolDown;
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

            if (this == ModItems.CRYSTAL_LOCATOR || this == ModItems.TUNED_CRYSTAL_LOCATOR) {
                cooldownManager.set(ModItems.CRYSTAL_LOCATOR, this.coolDown);
                cooldownManager.set(ModItems.TUNED_CRYSTAL_LOCATOR, this.coolDown);
            } else {
                cooldownManager.set(this, this.coolDown);
            }

            user.playSound(GeodesSoundEvents.BLOCK_ECHO_LOCATOR_USE, user.getSoundCategory(), 1.0f, 1.0f);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (!this.isTuned(stack, world)) {
            tooltip.add(Tuning.UNTUNED.getDescription());
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

    public boolean isTuned(ItemStack stack, @Nullable World world) {
        return false;
    }

    protected boolean isPingableCrystal(ItemStack locatorStack, ServerWorld world, BlockPos pos) {
        return false;
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
