package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.sounds.CrystalBlockSoundGroup;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

import java.util.List;

public class DirectedBuddingBlock extends GeodeBuddingBlock {

    private final Direction direction;

    public DirectedBuddingBlock(Direction direction, CrystalBlockSoundGroup hitSoundGroup, Settings settings, List<Block> clusters) {
        super(hitSoundGroup, settings, clusters);
        this.direction = direction;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (this.shouldGrow(random)) { // modulate the speed of the budding growth

            // always grow in the target direction
            this.growCrystalOnce(world, pos, this.direction);
        }
    }
}
