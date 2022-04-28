package com.github.thedeathlycow.moregeodes.blocks;

import net.minecraft.block.AmethystClusterBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

@SuppressWarnings("deprecation")
public class QuartzClusterBlock extends AmethystClusterBlock {

    public static final BooleanProperty POWERED;
    private final int SIGNAL_STRENGTH;
    private final int ON_TIME;

    public QuartzClusterBlock(int height, int xzOffset, Settings settings, int signalStrength, int onTime) {
        super(height, xzOffset, settings);
        this.SIGNAL_STRENGTH = signalStrength;
        this.ON_TIME = onTime;
        this.setDefaultState(this.getDefaultState().with(POWERED, false));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState placementState = super.getPlacementState(ctx);
        boolean poweredState = ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos());
        return placementState != null ? placementState.with(POWERED, poweredState) : null;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        world.createAndScheduleBlockTick(pos, this, ON_TIME);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isReceivingRedstonePower(pos)) {
            boolean powered = state.get(POWERED);
            world.setBlockState(pos, state.with(POWERED, !powered));
            world.createAndScheduleBlockTick(pos, this, ON_TIME);
        } else {
            world.setBlockState(pos, state.with(POWERED, false));
        }
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(POWERED) ? SIGNAL_STRENGTH : 0;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(POWERED);
    }

    static {
        POWERED = Properties.POWERED;
    }
}

