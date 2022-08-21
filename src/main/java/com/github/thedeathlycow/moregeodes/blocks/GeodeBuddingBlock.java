package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.sounds.CrystalBlockSoundGroup;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * An extension of {@link CrystalBlock} that provides for more cluster states, rather
 * than hardcoded amethyst growth states. Otherwise, this is an essence of a copy of
 * {@link BuddingAmethystBlock}.
 */
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
        if (random.nextInt(5) == 0) { // modulate the speed of the budding growth
            // the direction we want to try growing in
            Direction directionToGrow = DIRECTIONS[random.nextInt(DIRECTIONS.length)];

            // the position of the block to try growing in
            BlockPos positionToGrow = pos.offset(directionToGrow);

            // the current state of the block to try growing in
            BlockState currentStateInGrow = world.getBlockState(positionToGrow);

            // get an optional of the next bud to grow
            Optional<Block> nextBud = getNextBlockForGrowth(currentStateInGrow, directionToGrow);

            // set the next bud state if present
            nextBud.ifPresent((nextGrowth) -> {
                BlockState toSet = nextGrowth.getDefaultState()
                        .with(CrystalClusterBlock.FACING, directionToGrow)
                        .with(CrystalClusterBlock.WATERLOGGED, currentStateInGrow.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(positionToGrow, toSet);
            });
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

    private Optional<Block> getNextBlockForGrowth(BlockState currentState, Direction offsetFromSource) {
        // the current block where are trying to grow
        Block currentBlock = currentState.getBlock();
        // iterator of buds
        Iterator<Block> buds = this.clusters.iterator();

        if (stateCanGrowNewBud(currentState) && buds.hasNext()) {
            // grow a new cluster
            return Optional.of(buds.next());
        } else if (stateCanGrowBud(currentState, offsetFromSource)) {

            // iterate over buds, and look for the current block
            // then, return the next block if it exists
            for (Block bud = buds.next(); buds.hasNext(); bud = buds.next()) {
                if (bud == currentBlock) {
                    return Optional.of(buds.next());
                }
            }
        }
        return Optional.empty();
    }


    private static boolean stateCanGrowBud(BlockState state, Direction direction) {
        return state.contains(CrystalClusterBlock.FACING)
                && state.get(CrystalClusterBlock.FACING) == direction;
    }

    private static boolean stateCanGrowNewBud(BlockState state) {
        return state.isAir() || state.isOf(Blocks.WATER) && state.getFluidState().getLevel() == 8;
    }
}
