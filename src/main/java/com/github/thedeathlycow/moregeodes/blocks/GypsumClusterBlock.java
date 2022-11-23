package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.sounds.CrystalBlockSoundGroup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class GypsumClusterBlock extends CrystalClusterBlock {

    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    protected final VoxelShape lowerNorthShape;
    protected final VoxelShape lowerSouthShape;
    protected final VoxelShape lowerEastShape;
    protected final VoxelShape lowerWestShape;
    protected final VoxelShape lowerUpShape;
    protected final VoxelShape lowerDownShape;

    public GypsumClusterBlock(CrystalBlockSoundGroup hitSoundGroup, int height, int xzOffset, Settings settings) {
        super(hitSoundGroup, height, xzOffset, settings);
        this.setDefaultState(this.getDefaultState().with(HALF, DoubleBlockHalf.LOWER));
        this.lowerUpShape = Block.createCuboidShape(xzOffset, 0.0, xzOffset, (16 - xzOffset), height, (16 - xzOffset));
        this.lowerDownShape = Block.createCuboidShape(xzOffset, (16 - height), xzOffset, (16 - xzOffset), 16.0, (16 - xzOffset));
        this.lowerNorthShape = Block.createCuboidShape(xzOffset, xzOffset, (16 - height), (16 - xzOffset), (16 - xzOffset), 16.0);
        this.lowerSouthShape = Block.createCuboidShape(xzOffset, xzOffset, 0.0, (16 - xzOffset), (16 - xzOffset), height);
        this.lowerEastShape = Block.createCuboidShape(0.0, xzOffset, xzOffset, height, (16 - xzOffset), (16 - xzOffset));
        this.lowerWestShape = Block.createCuboidShape((16 - height), xzOffset, xzOffset, 16.0, (16 - xzOffset), (16 - xzOffset));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(HALF) == DoubleBlockHalf.UPPER) {
            return super.getOutlineShape(state, world, pos, context);
        } else {
            Direction direction = state.get(FACING);
            return switch (direction) {
                case NORTH -> this.lowerNorthShape;
                case SOUTH -> this.lowerSouthShape;
                case EAST -> this.lowerEastShape;
                case WEST -> this.lowerWestShape;
                case DOWN -> this.lowerDownShape;
                case UP -> this.lowerUpShape;
            };
        }
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return switch (state.get(HALF)) {
            case LOWER -> super.canPlaceAt(state, world, pos);
            case UPPER -> this.canPlaceUpper(state, world, pos);
        };
    }

    private boolean canPlaceUpper(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        BlockState anchorState = world.getBlockState(blockPos);
        return anchorState.isOf(this) && anchorState.get(HALF) == DoubleBlockHalf.LOWER;
    }
}
