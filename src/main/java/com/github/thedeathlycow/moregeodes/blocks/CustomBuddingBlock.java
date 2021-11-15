package com.github.thedeathlycow.moregeodes.blocks;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomBuddingBlock extends Block {

    private static final Direction[] DIRECTIONS = Direction.values();

    private final Block SMALL_BUD;
    private final Block MEDIUM_BUD;
    private final Block LARGE_BUD;
    private final Block FULL_BUD;

    public CustomBuddingBlock(AbstractBlock.Settings settings, List<AmethystClusterBlock> clusters) {
        super(settings);
        assert clusters.size() == 4;
        this.SMALL_BUD = clusters.get(0);
        this.MEDIUM_BUD = clusters.get(1);
        this.LARGE_BUD = clusters.get(2);
        this.FULL_BUD = clusters.get(3);
    }

    public PistonBehavior getPistonBehavior() {
        return PistonBehavior.DESTROY;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            Block block = null;
            if (canGrowIn(blockState)) {
                block = this.SMALL_BUD;
            } else if (blockState.isOf(this.SMALL_BUD) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = this.MEDIUM_BUD;
            } else if (blockState.isOf(this.MEDIUM_BUD) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = this.LARGE_BUD;
            } else if (blockState.isOf(this.LARGE_BUD) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = this.FULL_BUD;
            }

            if (block != null) {
                BlockState toSet = block.getDefaultState().with(AmethystClusterBlock.FACING, direction).with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(blockPos, toSet);
            }

        }
    }

    public List<Block> getClusters() {
        return ImmutableList.of(SMALL_BUD, MEDIUM_BUD, LARGE_BUD, FULL_BUD);
    }

    public List<BlockState> getClusterStates() {
        List<BlockState> states = new ArrayList<>();
        getClusters().forEach(
                s -> states.add(s.getDefaultState())
        );
        return states;
    }

    public static boolean canGrowIn(BlockState state) {
        return state.isAir() || state.isOf(Blocks.WATER) && state.getFluidState().getLevel() == 8;
    }
}
