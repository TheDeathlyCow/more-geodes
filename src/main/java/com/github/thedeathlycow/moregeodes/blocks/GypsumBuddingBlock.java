package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.sounds.CrystalBlockSoundGroup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

import java.util.List;

public class GypsumBuddingBlock extends GeodeBuddingBlock {

    public static final IntProperty MOISTURE = Properties.MOISTURE;
    public static final BooleanProperty HAS_EVAPORATED = GeodesBlockStateProperties.HAS_EVAPORATED;
    public static final int MAX_MOISTURE = 7;

    public GypsumBuddingBlock(CrystalBlockSoundGroup hitSoundGroup, Settings settings, List<Block> clusters) {
        super(hitSoundGroup, settings, clusters);
        this.setDefaultState(
                this.getDefaultState()
                        .with(MOISTURE, 0)
                        .with(HAS_EVAPORATED, false)
        );
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        this.updateMoisture(state, world, pos, random);
    }

    @Override
    protected void growCrystalOnce(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(HAS_EVAPORATED)) {
            super.growCrystalOnce(state, world, pos, random);
            world.setBlockState(pos, state.with(HAS_EVAPORATED, false), Block.NOTIFY_LISTENERS);
        }
    }

    private void updateMoisture(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        final int currentMoisture = state.get(MOISTURE);
        boolean isWet = world.hasRain(pos.up()) || isNextToWater(world, pos);
        if (isWet) {
            // increase moisture
            if (currentMoisture < MAX_MOISTURE) {
                world.setBlockState(
                        pos,
                        state.with(MOISTURE, currentMoisture + 1)
                                .with(HAS_EVAPORATED, false),
                        Block.NOTIFY_LISTENERS
                );
            }
        } else {
            // decrease moisture
            if (currentMoisture > 0 && random.nextInt(5) == 0) {
                world.setBlockState(
                        pos,
                        state.with(MOISTURE, currentMoisture - 1)
                                .with(HAS_EVAPORATED, true),
                        Block.NOTIFY_LISTENERS
                );
            }
        }
    }


    private static boolean isNextToWater(WorldView world, BlockPos pos) {
        BlockPos.Mutable mutable = pos.mutableCopy();

        for (var direction : Direction.values()) {
            BlockState neighbourState = world.getBlockState(mutable);
            if (direction != Direction.DOWN || isWater(neighbourState)) {
                mutable.set(pos, direction);
                neighbourState = world.getBlockState(mutable);
                if (isWater(neighbourState) && !neighbourState.isSideSolidFullSquare(world, pos, direction.getOpposite())) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isWater(BlockState state) {
        return state.getFluidState().isIn(FluidTags.WATER);
    }
}
