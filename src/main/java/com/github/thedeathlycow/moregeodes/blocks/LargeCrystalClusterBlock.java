package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.sounds.CrystalBlockSoundGroup;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.util.RandomSource;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class LargeCrystalClusterBlock extends CrystalClusterBlock {

    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    protected final VoxelShape lowerNorthShape;
    protected final VoxelShape lowerSouthShape;
    protected final VoxelShape lowerEastShape;
    protected final VoxelShape lowerWestShape;
    protected final VoxelShape lowerUpShape;
    protected final VoxelShape lowerDownShape;

    public LargeCrystalClusterBlock(CrystalBlockSoundGroup hitSoundGroup, int height, int xzOffset, Properties settings) {
        super(hitSoundGroup, height, xzOffset, settings);
        this.registerDefaultState(this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER));

        // create lower half size with a height of 16
        height = 16;
        this.lowerUpShape = Block.box(xzOffset, 0.0, xzOffset, (16 - xzOffset), height, (16 - xzOffset));
        this.lowerDownShape = Block.box(xzOffset, (16 - height), xzOffset, (16 - xzOffset), 16.0, (16 - xzOffset));
        this.lowerNorthShape = Block.box(xzOffset, xzOffset, (16 - height), (16 - xzOffset), (16 - xzOffset), 16.0);
        this.lowerSouthShape = Block.box(xzOffset, xzOffset, 0.0, (16 - xzOffset), (16 - xzOffset), height);
        this.lowerEastShape = Block.box(0.0, xzOffset, xzOffset, height, (16 - xzOffset), (16 - xzOffset));
        this.lowerWestShape = Block.box((16 - height), xzOffset, xzOffset, 16.0, (16 - xzOffset), (16 - xzOffset));
    }

    public static void placeAt(LevelAccessor world, BlockState state, BlockPos pos, int flags) {
        BlockPos headPos = pos.relative(state.getValue(FACING));
        world.setBlock(
                pos,
                DoublePlantBlock.copyWaterloggedFrom(world, pos, state.setValue(HALF, DoubleBlockHalf.LOWER)),
                flags
        );
        world.setBlock(
                headPos,
                DoublePlantBlock.copyWaterloggedFrom(world, headPos, state.setValue(HALF, DoubleBlockHalf.UPPER)),
                flags
        );
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {

        @Nullable BlockState baseState = super.getStateForPlacement(ctx);
        if (baseState == null) {
            return null;
        }

        BlockPos blockPos = ctx.getClickedPos();
        Level world = ctx.getLevel();

        boolean canPlace = blockPos.getY() < world.getMaxY() - 1
                && world.getBlockState(blockPos.relative(baseState.getValue(FACING))).canBeReplaced(ctx);

        return canPlace ? baseState : null;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HALF);
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            return super.getShape(state, world, pos, context);
        } else {
            return switch (state.getValue(FACING)) {
                case NORTH -> this.lowerNorthShape;
                case SOUTH -> this.lowerSouthShape;
                case EAST -> this.lowerEastShape;
                case WEST -> this.lowerWestShape;
                case DOWN -> this.lowerDownShape;
                case UP -> this.lowerUpShape;
            };
        }
    }

    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        BlockPos headPos = pos.relative(state.getValue(CrystalClusterBlock.FACING));
        world.setBlock(
                headPos,
                DoublePlantBlock.copyWaterloggedFrom(world, headPos, state.setValue(HALF, DoubleBlockHalf.UPPER)),
                Block.UPDATE_ALL
        );
    }

    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return switch (state.getValue(HALF)) {
            case LOWER -> super.canSurvive(state, world, pos);
            case UPPER -> this.canPlaceUpper(state, world, pos);
        };
    }

    public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess ticks, BlockPos pos, Direction dirToNeighbour, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        DoubleBlockHalf half = state.getValue(HALF);

        boolean shouldBreakBlock = half == DoubleBlockHalf.LOWER
                && dirToNeighbour == state.getValue(FACING)
                && (!neighborState.is(this) || neighborState.getValue(HALF) == half);

        if (shouldBreakBlock) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(state, world, ticks, pos, dirToNeighbour, neighborPos, neighborState, random);
        }
    }

    public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        if (!world.isClientSide()) {

            if (!player.isCreative()) {
                Block.dropResources(state, world, pos, null, player, player.getMainHandItem());
            }

            if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
                // when upper half is broken
                BlockPos anchorPos = pos.relative(state.getValue(FACING).getOpposite());
                BlockState anchorState = world.getBlockState(anchorPos);

                if (anchorState.is(this) && anchorState.getValue(HALF) == DoubleBlockHalf.LOWER) {

                    world.setBlock(
                            anchorPos,
                            Blocks.AIR.defaultBlockState(),
                            Block.UPDATE_SUPPRESS_DROPS | Block.UPDATE_ALL
                    );

                    world.levelEvent(
                            player,
                            LevelEvent.PARTICLES_DESTROY_BLOCK,
                            anchorPos,
                            Block.getId(anchorState)
                    );
                }
            }
        }
        return super.playerWillDestroy(world, pos, state, player);
    }

    @SuppressWarnings("deprecation")
    public long getSeed(BlockState state, BlockPos pos) {
        return Mth.getSeed(
                pos.getX(),
                pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(),
                pos.getZ()
        );
    }

    private boolean canPlaceUpper(BlockState state, LevelReader world, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos anchorPos = pos.relative(direction.getOpposite());
        BlockState anchorState = world.getBlockState(anchorPos);
        return anchorState.getBlock() instanceof LargeCrystalClusterBlock // allow anchor to not always be strictly 'this' for budding growth
                && anchorState.getValue(HALF) == DoubleBlockHalf.LOWER;
    }
}
