package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.blocks.entity.EchoLocatorBlockEntity;
import com.github.thedeathlycow.moregeodes.blocks.entity.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class EchoLocatorBlock extends BlockWithEntity implements Waterloggable {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty POWERED = Properties.POWERED;
    public static final EnumProperty<Direction.Axis> AXIS = Properties.HORIZONTAL_AXIS;
    public static final DirectionProperty FACING = Properties.FACING;
    protected static final VoxelShape Y_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    protected static final VoxelShape Z_SHAPE = Block.createCuboidShape(5.0D, 5.0D, 0.0D, 11.0D, 11.0D, 16.0D);
    protected static final VoxelShape X_SHAPE = Block.createCuboidShape(0.0D, 5.0D, 5.0D, 16.0D, 11.0D, 11.0D);

    private final EchoLocatorType type;

    public EchoLocatorBlock(EchoLocatorType type, Settings settings) {
        super(settings);
        this.type = type;
        this.setDefaultState(
                super.getDefaultState()
                        .with(WATERLOGGED, false)
                        .with(POWERED, false)
                        .with(AXIS, Direction.Axis.X)
                        .with(FACING, Direction.UP)
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
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean isWater = fluidState.getFluid() == Fluids.WATER;
        Direction.Axis playerAxis = ctx.getPlayerFacing().getAxis();

        BlockState state = this.getDefaultState()
                .with(FACING, ctx.getSide())
                .with(WATERLOGGED, isWater);

        if (playerAxis.isHorizontal()) {
            state = state.with(AXIS, playerAxis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X);
        }

        return state;
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
        return this.ping(world, pos, state, hit, player) ? ActionResult.success(world.isClient) : ActionResult.PASS;
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
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING).getAxis()) {
            case Y -> Y_SHAPE;
            case X -> X_SHAPE;
            case Z -> Z_SHAPE;
        };
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.with(FACING, mirror.apply(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED, WATERLOGGED, AXIS, FACING);
    }

    private boolean ping(World world, BlockPos origin, BlockState state, BlockHitResult hit, PlayerEntity player) {

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
