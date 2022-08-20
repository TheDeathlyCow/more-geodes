package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.sounds.CrystalBlockSoundGroup;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
public class GeodeBuddingBlock extends CrystalBlock {

    private static final Direction[] DIRECTIONS = Direction.values();

    private final List<Block> clusters;

    public GeodeBuddingBlock(CrystalBlockSoundGroup hitSoundGroup, AbstractBlock.Settings settings, List<Block> clusters) {
        super(hitSoundGroup, settings);
        assert clusters.size() > 0;
        this.clusters = clusters;
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
            Block blockStateBlock = blockState.getBlock();
            Block nextBlock = null;

            if (canGrowNewClusterIn(blockState)) {
                nextBlock = clusters.get(0);
            } else if (blockState.contains(CrystalClusterBlock.FACING) && blockState.get(CrystalClusterBlock.FACING) == direction) {
                if (clusters.contains(blockStateBlock)) {
                    int nextBlockIndex = clusters.indexOf(blockStateBlock) + 1;
                    if (nextBlockIndex < clusters.size()) {
                        nextBlock = clusters.get(nextBlockIndex);
                    }
                }
            }

            if (nextBlock != null) {
                BlockState toSet = nextBlock.getDefaultState()
                        .with(AmethystClusterBlock.FACING, direction)
                        .with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(blockPos, toSet);
            }

        }
    }

    public List<Block> getClusters() {
        return Collections.unmodifiableList(clusters);
    }

    public List<BlockState> getClusterStates() {
        return this.getClusters().stream()
                .map((Block::getDefaultState))
                .collect(Collectors.toList());
    }

    public static boolean canGrowNewClusterIn(BlockState state) {
        return state.isAir() || state.isOf(Blocks.WATER) && state.getFluidState().getLevel() == 8;
    }
}
