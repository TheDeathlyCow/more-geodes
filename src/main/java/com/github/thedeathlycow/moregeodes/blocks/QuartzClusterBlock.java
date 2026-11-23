package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.sounds.CrystalBlockSoundGroup;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class QuartzClusterBlock extends CrystalClusterBlock {

    public static final BooleanProperty POWERED;
    private final int SIGNAL_STRENGTH;
    private final int ON_TIME;

    public QuartzClusterBlock(int height, int xzOffset, Properties settings, int signalStrength, int onTime) {
        super(CrystalBlockSoundGroup.QUARTZ_GEODE, height, xzOffset, settings);
        this.SIGNAL_STRENGTH = signalStrength;
        this.ON_TIME = onTime;
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState placementState = super.getStateForPlacement(ctx);
        boolean poweredState = ctx.getLevel().hasNeighborSignal(ctx.getClickedPos());
        return placementState != null ? placementState.setValue(POWERED, poweredState) : null;
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, net.minecraft.world.level.redstone.Orientation orientation, boolean notify) {
        world.scheduleTick(pos, this, ON_TIME);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (world.hasNeighborSignal(pos)) {
            boolean powered = state.getValue(POWERED);
            world.setBlockAndUpdate(pos, state.setValue(POWERED, !powered));
            world.scheduleTick(pos, this, ON_TIME);
        } else {
            world.setBlockAndUpdate(pos, state.setValue(POWERED, false));
        }
    }

    @Override
    public int getSignal(BlockState state, BlockGetter world, BlockPos pos, Direction direction) {
        return state.getValue(POWERED) ? SIGNAL_STRENGTH : 0;
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWERED);
    }

    static {
        POWERED = BlockStateProperties.POWERED;
    }
}

