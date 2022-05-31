package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.blocks.entity.EchoLocatorBlockEntity;
import com.github.thedeathlycow.moregeodes.blocks.entity.ModBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class EchoLocatorBlock extends BlockWithEntity implements Waterloggable {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty POWERED = Properties.POWERED;

    private final EchoLocatorType type;

    public EchoLocatorBlock(EchoLocatorType type, Settings settings) {
        super(settings);
        this.type = type;
        this.setDefaultState(
                super.getDefaultState()
                        .with(WATERLOGGED, false)
                        .with(POWERED, false)
        );
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        EchoLocatorBlockEntity blockEntity = new EchoLocatorBlockEntity(pos, state);
        blockEntity.setType(this.type);
        return blockEntity;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.ECHO_LOCATOR, world.isClient ? EchoLocatorBlockEntity::clientTick : EchoLocatorBlockEntity::serverTick);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        this.powerLocator(world, state, pos);
        return this.ding(world, pos, state, hit, player) ? ActionResult.success(world.isClient) : ActionResult.PASS;
    }

    public void powerLocator(World world, BlockState state, BlockPos pos) {
        world.setBlockState(pos, state.with(POWERED, true));
        this.updateNeighbors(state, world, pos);
        world.createAndScheduleBlockTick(pos, this, 8);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, state.with(POWERED, false), Block.NOTIFY_ALL);
        this.updateNeighbors(state, world, pos);
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(POWERED) ? 15 : 0;
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(POWERED) ? 15 : 0;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED, WATERLOGGED);
    }

    private boolean ding(World world, BlockPos origin, BlockState state, BlockHitResult hit, PlayerEntity player) {

        world.playSound(null, origin, SoundEvents.BLOCK_BELL_USE, SoundCategory.BLOCKS, 2.0F, 1.0F);

        EchoLocatorBlockEntity blockEntity = (EchoLocatorBlockEntity) world.getBlockEntity(origin);

        if (blockEntity != null && !state.get(POWERED)) {
            blockEntity.activate(world, origin);
            return true;
        }

        return false;
    }

    private void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos.down(), this);
    }
}
