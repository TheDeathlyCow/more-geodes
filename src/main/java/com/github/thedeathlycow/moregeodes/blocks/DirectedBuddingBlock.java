package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.sounds.CrystalBlockSoundGroup;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class DirectedBuddingBlock extends GeodeBuddingBlock {

    private final Direction direction;

    public DirectedBuddingBlock(Direction direction, CrystalBlockSoundGroup hitSoundGroup, Properties settings, List<Block> clusters) {
        super(hitSoundGroup, settings, clusters);
        this.direction = direction;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (this.shouldGrow(random)) { // modulate the speed of the budding growth

            // always grow in the target direction
            this.growCrystalOnce(world, pos, this.direction);
        }
    }
}
