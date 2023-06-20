package com.github.thedeathlycow.moregeodes.blocks;

import carpet.CarpetSettings;
import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.item.ModItems;
import com.github.thedeathlycow.moregeodes.sounds.CrystalBlockSoundGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * An extension of {@link CrystalBlock} that provides for more cluster states, rather
 * than hardcoded amethyst growth states. Otherwise, this is an essence of a copy of
 * {@link BuddingAmethystBlock}.
 */
@SuppressWarnings("deprecation")
public class GeodeBuddingBlock extends CrystalBlock {

    private static final Direction[] DIRECTIONS = Direction.values();

    private final List<Block> clusters;

    public GeodeBuddingBlock(CrystalBlockSoundGroup hitSoundGroup, AbstractBlock.Settings settings, List<Block> clusters) {
        super(hitSoundGroup, settings.pistonBehavior(getPistonBehavior()));
        this.clusters = clusters;
    }

    private static PistonBehavior getPistonBehavior() {
        if (MoreGeodes.isCarpetLoaded() && CarpetSettings.movableAmethyst) {
            return PistonBehavior.NORMAL;
        }

        return PistonBehavior.DESTROY;
    }


    @Override
    public void afterBreak(
            World world,
            PlayerEntity player,
            BlockPos pos,
            BlockState state,
            @Nullable BlockEntity blockEntity,
            ItemStack stack
    ) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);
        if (MoreGeodes.isCarpetLoaded()) {
            if (CarpetSettings.movableAmethyst
                    && stack.getItem() instanceof PickaxeItem
                    && EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) > 0
            ) {
                dropStack(world, pos, this.asItem().getDefaultStack());
            }
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (this.shouldGrow(random)) { // modulate the speed of the budding growth
            // the direction we want to try growing in
            Direction directionToGrow = DIRECTIONS[random.nextInt(DIRECTIONS.length)];

            this.growCrystalOnce(world, pos, directionToGrow);
        }
    }

    public List<Block> getClusters() {
        return Collections.unmodifiableList(clusters);
    }

    public List<BlockState> getClusterStates() {
        return this.getClusters().stream()
                .map((Block::getDefaultState))
                .collect(Collectors.toList());
    }

    protected boolean shouldGrow(Random random) {
        return random.nextInt(5) == 0;
    }

    /**
     * Grows a crystal once in the given direction
     *
     * @param world           The level to grow in
     * @param pos             The position of the budding block
     * @param directionToGrow The direction of the crystal to be grown
     */
    protected void growCrystalOnce(ServerWorld world, BlockPos pos, Direction directionToGrow) {

        // the position of the block to try growing in
        BlockPos positionToGrow = pos.offset(directionToGrow);

        // the current state of the block to try growing in
        BlockState currentStateInGrow = world.getBlockState(positionToGrow);

        // get an optional of the next bud to grow
        Optional<Block> nextBud = getNextBlockForGrowth(currentStateInGrow, directionToGrow);

        // set the next bud state if present
        nextBud.ifPresent((nextGrowth) -> {
            BlockState nextBudState = nextGrowth.getDefaultState()
                    .with(CrystalClusterBlock.FACING, directionToGrow)
                    .with(CrystalClusterBlock.WATERLOGGED, currentStateInGrow.getFluidState().getFluid() == Fluids.WATER);

            if (nextGrowth instanceof LargeCrystalClusterBlock) {

                Direction facing = nextBudState.get(CrystalClusterBlock.FACING);
                BlockPos headPos = positionToGrow.offset(facing);
                BlockState headState = world.getBlockState(headPos);

                boolean canGrow = (this.stateCanGrowNewBud(headState) || this.stateCanGrowBud(headState, facing))
                        && nextBudState.canPlaceAt(world, positionToGrow);

                if (canGrow) {
                    LargeCrystalClusterBlock.placeAt(
                            world,
                            nextBudState,
                            positionToGrow,
                            Block.NOTIFY_ALL
                    );
                }

            } else {
                world.setBlockState(positionToGrow, nextBudState, Block.NOTIFY_ALL);
            }
        });
    }

    protected Optional<Block> getNextBlockForGrowth(BlockState currentState, Direction offsetFromSource) {
        // the current block where are trying to grow
        Block currentBlock = currentState.getBlock();
        // iterator of buds
        Iterator<Block> buds = this.clusters.iterator();

        if (this.stateCanGrowNewBud(currentState) && buds.hasNext()) {
            // grow a new cluster
            return Optional.of(buds.next());
        } else if (this.stateCanGrowBud(currentState, offsetFromSource)) {

            // iterate over buds, and look for the current block
            // then, return the next block if it exists
            for (Block bud = buds.next(); buds.hasNext(); bud = buds.next()) {
                if (bud == currentBlock) {
                    return Optional.of(buds.next());
                }
            }
        }
        return Optional.empty();
    }


    private boolean stateCanGrowBud(BlockState state, Direction direction) {
        return state.contains(CrystalClusterBlock.FACING)
                && state.get(CrystalClusterBlock.FACING) == direction
                && this.clusters.contains(state.getBlock());
    }

    private boolean stateCanGrowNewBud(BlockState state) {
        return state.isAir() || state.isOf(Blocks.WATER) && state.getFluidState().getLevel() == 8;
    }
}
