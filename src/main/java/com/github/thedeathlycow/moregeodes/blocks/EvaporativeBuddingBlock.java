package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.sounds.CrystalBlockSoundGroup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LightType;
import net.minecraft.world.WorldView;

import java.util.List;

public class EvaporativeBuddingBlock extends GeodeBuddingBlock {

    public static final IntProperty MOISTURE = Properties.MOISTURE;
    public static final int MAX_MOISTURE = 7;

    public EvaporativeBuddingBlock(CrystalBlockSoundGroup hitSoundGroup, Settings settings, List<Block> clusters) {
        super(hitSoundGroup, settings, clusters);
        this.setDefaultState(
                this.getDefaultState()
                        .with(MOISTURE, 0)
        );
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.updateMoisture(state, world, pos, random);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(MOISTURE);
    }

    private void updateMoisture(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        final int currentMoisture = state.get(MOISTURE);
        boolean isWet = world.hasRain(pos.up()) || isNextToWater(world, pos);
        if (isWet) {
            // increase moisture
            if (currentMoisture < MAX_MOISTURE) {
                world.setBlockState(
                        pos,
                        state.with(MOISTURE, currentMoisture + 1),
                        Block.NOTIFY_LISTENERS
                );
            }
        } else if (world.getLightLevel(LightType.SKY, pos.up()) > 0) {
            // decrease moisture
            if (currentMoisture > 0) {
                world.setBlockState(
                        pos,
                        state.with(MOISTURE, currentMoisture - 1),
                        Block.NOTIFY_LISTENERS
                );

                if (random.nextInt(currentMoisture) == 0) {
                    this.growCrystalOnce(state, world, pos, random);
                }
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
