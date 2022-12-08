package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.sounds.CrystalBlockSoundGroup;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

public class LargeCrystalClusterBlock extends CrystalClusterBlock {

    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    protected final VoxelShape lowerNorthShape;
    protected final VoxelShape lowerSouthShape;
    protected final VoxelShape lowerEastShape;
    protected final VoxelShape lowerWestShape;
    protected final VoxelShape lowerUpShape;
    protected final VoxelShape lowerDownShape;

    public LargeCrystalClusterBlock(CrystalBlockSoundGroup hitSoundGroup, int height, int xzOffset, Settings settings) {
        super(hitSoundGroup, height, xzOffset, settings);
        this.setDefaultState(this.getDefaultState().with(HALF, DoubleBlockHalf.LOWER));
        height = 16; // imagine reassigning params lol
        this.lowerUpShape = Block.createCuboidShape(xzOffset, 0.0, xzOffset, (16 - xzOffset), height, (16 - xzOffset));
        this.lowerDownShape = Block.createCuboidShape(xzOffset, (16 - height), xzOffset, (16 - xzOffset), 16.0, (16 - xzOffset));
        this.lowerNorthShape = Block.createCuboidShape(xzOffset, xzOffset, (16 - height), (16 - xzOffset), (16 - xzOffset), 16.0);
        this.lowerSouthShape = Block.createCuboidShape(xzOffset, xzOffset, 0.0, (16 - xzOffset), (16 - xzOffset), height);
        this.lowerEastShape = Block.createCuboidShape(0.0, xzOffset, xzOffset, height, (16 - xzOffset), (16 - xzOffset));
        this.lowerWestShape = Block.createCuboidShape((16 - height), xzOffset, xzOffset, 16.0, (16 - xzOffset), (16 - xzOffset));
    }

    public static void placeAt(WorldAccess world, BlockState state, BlockPos pos, int flags) {
        BlockPos headPos = pos.offset(state.get(FACING));
        world.setBlockState(
                pos,
                TallPlantBlock.withWaterloggedState(world, pos, state.with(HALF, DoubleBlockHalf.LOWER)),
                flags
        );
        world.setBlockState(
                headPos,
                TallPlantBlock.withWaterloggedState(world, headPos, state.with(HALF, DoubleBlockHalf.UPPER)),
                flags
        );
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {

        @Nullable BlockState baseState = super.getPlacementState(ctx);
        if (baseState == null) {
            return null;
        }

        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();

        boolean canPlace = blockPos.getY() < world.getTopY() - 1
                && world.getBlockState(blockPos.offset(baseState.get(FACING))).canReplace(ctx);

        return canPlace ? baseState : null;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(HALF);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(HALF) == DoubleBlockHalf.UPPER) {
            return super.getOutlineShape(state, world, pos, context);
        } else {
            return switch (state.get(FACING)) {
                case NORTH -> this.lowerNorthShape;
                case SOUTH -> this.lowerSouthShape;
                case EAST -> this.lowerEastShape;
                case WEST -> this.lowerWestShape;
                case DOWN -> this.lowerDownShape;
                case UP -> this.lowerUpShape;
            };
        }
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        BlockPos headPos = pos.offset(state.get(CrystalClusterBlock.FACING));
        world.setBlockState(
                headPos,
                TallPlantBlock.withWaterloggedState(world, headPos, state.with(HALF, DoubleBlockHalf.UPPER)),
                Block.NOTIFY_ALL
        );
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return switch (state.get(HALF)) {
            case LOWER -> super.canPlaceAt(state, world, pos);
            case UPPER -> this.canPlaceUpper(state, world, pos);
        };
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction dirToNeighbour, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        DoubleBlockHalf half = state.get(HALF);

        boolean shouldBreakBlock = half == DoubleBlockHalf.LOWER
                && dirToNeighbour == state.get(FACING)
                && (!neighborState.isOf(this) || neighborState.get(HALF) == half);

        if (shouldBreakBlock) {
            return Blocks.AIR.getDefaultState();
        } else {
            return super.getStateForNeighborUpdate(state, dirToNeighbour, neighborState, world, pos, neighborPos);
        }
    }

    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient) {
            // server break

            if (state.get(HALF) == DoubleBlockHalf.UPPER) {
                // when upper half is broken
                BlockPos anchorPos = pos.offset(state.get(FACING).getOpposite());
                BlockState anchorState = world.getBlockState(anchorPos);

                if (anchorState.isOf(this) && anchorState.get(HALF) == DoubleBlockHalf.LOWER) {

                    world.setBlockState(
                            anchorPos,
                            Blocks.AIR.getDefaultState(),
                            Block.SKIP_DROPS | Block.NOTIFY_ALL
                    );

                    world.syncWorldEvent(
                            player,
                            WorldEvents.BLOCK_BROKEN,
                            anchorPos,
                            Block.getRawIdFromState(anchorState)
                    );
                }
            }

        }
        super.onBreak(world, pos, state, player);
    }

    @SuppressWarnings("deprecation")
    public long getRenderingSeed(BlockState state, BlockPos pos) {
        return MathHelper.hashCode(
                pos.getX(),
                pos.down(state.get(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(),
                pos.getZ()
        );
    }

    private boolean canPlaceUpper(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos anchorPos = pos.offset(direction.getOpposite());
        BlockState anchorState = world.getBlockState(anchorPos);
        return anchorState.getBlock() instanceof LargeCrystalClusterBlock // allow anchor to not always be strictly 'this' for budding growth
                && anchorState.get(HALF) == DoubleBlockHalf.LOWER;
    }
}
