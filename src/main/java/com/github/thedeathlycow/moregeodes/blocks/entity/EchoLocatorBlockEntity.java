package com.github.thedeathlycow.moregeodes.blocks.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.LinkedList;
import java.util.List;

public class EchoLocatorBlockEntity extends BlockEntity {

    private static final Vec3i scanRadius = new Vec3i(30, 30, 30);

    public EchoLocatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ECHO_LOCATOR, pos, state);
    }

    public void activate(World world, BlockPos origin, TagKey<Block> blocksCanLocate) {
        BlockPos from = origin.add(scanRadius.multiply(-1));
        BlockPos to = origin.add(scanRadius);

        List<Pair<BlockState, BlockPos>> found = new LinkedList<>();
        for (BlockPos pos : BlockPos.iterate(from, to)) {
            BlockState state = world.getBlockState(pos);
            if (state.isIn(blocksCanLocate)) {
                found.add(new Pair<>(state, pos));
            }
        }
    }
}
