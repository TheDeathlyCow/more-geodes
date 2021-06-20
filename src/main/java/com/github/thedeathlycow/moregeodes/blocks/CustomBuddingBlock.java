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

    private final Block SMALL_CLUSTER;
    private final Block MEDIUM_CLUSTER;
    private final Block LARGE_CLUSTER;
    private final Block FULL_BUD;

    public CustomBuddingBlock(AbstractBlock.Settings settings, List<AmethystClusterBlock> clusters) {
        super(settings);
        if (clusters.size() != 4) {
            throw new IllegalArgumentException("Crystals must have exactly 4 stages of growth!");
        }
        this.SMALL_CLUSTER = clusters.get(0);
        this.MEDIUM_CLUSTER = clusters.get(1);
        this.LARGE_CLUSTER = clusters.get(2);
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
                block = this.SMALL_CLUSTER;
            } else if (blockState.isOf(ModBlocks.SMALL_EMERALD_BUD) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = this.MEDIUM_CLUSTER;
            } else if (blockState.isOf(ModBlocks.MEDIUM_EMERALD_BUD) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = this.LARGE_CLUSTER;
            } else if (blockState.isOf(ModBlocks.LARGE_EMERALD_BUD) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = this.FULL_BUD;
            }

            if (block != null) {
                BlockState blockState2 = block.getDefaultState().with(AmethystClusterBlock.FACING, direction).with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(blockPos, blockState2);
            }

        }
    }

    public List<Block> getClusters() {
        return ImmutableList.of(SMALL_CLUSTER, MEDIUM_CLUSTER, LARGE_CLUSTER, FULL_BUD);
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
