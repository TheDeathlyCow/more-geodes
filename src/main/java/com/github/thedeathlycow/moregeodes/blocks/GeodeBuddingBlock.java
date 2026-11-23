package com.github.thedeathlycow.moregeodes.blocks;

import carpet.CarpetSettings;
import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.item.ModItems;
import com.github.thedeathlycow.moregeodes.sounds.CrystalBlockSoundGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
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

    private static final Direction[] UPDATE_SHAPE_ORDER = Direction.values();

    private final List<Block> clusters;

    public GeodeBuddingBlock(CrystalBlockSoundGroup hitSoundGroup, BlockBehaviour.Properties settings, List<Block> clusters) {
        super(hitSoundGroup, settings.pushReaction(PushReaction.POPPED));
        this.clusters = clusters;
    }


    @Override
    public void playerDestroy(
            ServerLevel world,
            ServerPlayer player,
            BlockPos pos,
            BlockState state,
            @Nullable BlockEntity blockEntity,
            ItemStack stack
    ) {
        super.playerDestroy(world, player, pos, state, blockEntity, stack);
        if (MoreGeodes.isCarpetLoaded()) {
            if (CarpetSettings.movableAmethyst
                    && stack.is(ItemTags.PICKAXES)
                    && EnchantmentHelper.getItemEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH), stack) > 0
            ) {
                popResource(world, pos, this.asItem().getDefaultInstance());
            }
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (this.shouldGrow(random)) { // modulate the speed of the budding growth
            // the direction we want to try growing in
            Direction directionToGrow = UPDATE_SHAPE_ORDER[random.nextInt(UPDATE_SHAPE_ORDER.length)];

            this.growCrystalOnce(world, pos, directionToGrow);
        }
    }

    public List<Block> getClusters() {
        return Collections.unmodifiableList(clusters);
    }

    public List<BlockState> getClusterStates() {
        return this.getClusters().stream()
                .map((Block::defaultBlockState))
                .collect(Collectors.toList());
    }

    protected boolean shouldGrow(RandomSource random) {
        return random.nextInt(5) == 0;
    }

    /**
     * Grows a crystal once in the given direction
     *
     * @param world           The level to grow in
     * @param pos             The position of the budding block
     * @param directionToGrow The direction of the crystal to be grown
     */
    protected void growCrystalOnce(ServerLevel world, BlockPos pos, Direction directionToGrow) {

        // the position of the block to try growing in
        BlockPos positionToGrow = pos.relative(directionToGrow);

        // the current state of the block to try growing in
        BlockState currentStateInGrow = world.getBlockState(positionToGrow);

        // get an optional of the next bud to grow
        Optional<Block> nextBud = getNextBlockForGrowth(currentStateInGrow, directionToGrow);

        // set the next bud state if present
        nextBud.ifPresent((nextGrowth) -> {
            BlockState nextBudState = nextGrowth.defaultBlockState()
                    .setValue(CrystalClusterBlock.FACING, directionToGrow)
                    .setValue(CrystalClusterBlock.WATERLOGGED, currentStateInGrow.getFluidState().getType() == Fluids.WATER);

            if (nextGrowth instanceof LargeCrystalClusterBlock) {

                Direction facing = nextBudState.getValue(CrystalClusterBlock.FACING);
                BlockPos headPos = positionToGrow.relative(facing);
                BlockState headState = world.getBlockState(headPos);

                boolean canGrow = (this.stateCanGrowNewBud(headState) || this.stateCanGrowBud(headState, facing))
                        && nextBudState.canSurvive(world, positionToGrow);

                if (canGrow) {
                    LargeCrystalClusterBlock.placeAt(
                            world,
                            nextBudState,
                            positionToGrow,
                            Block.UPDATE_ALL
                    );
                }

            } else {
                world.setBlock(positionToGrow, nextBudState, Block.UPDATE_ALL);
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
        return state.hasProperty(CrystalClusterBlock.FACING)
                && state.getValue(CrystalClusterBlock.FACING) == direction
                && this.clusters.contains(state.getBlock());
    }

    private boolean stateCanGrowNewBud(BlockState state) {
        return state.isAir() || state.is(Blocks.WATER) && state.getFluidState().getAmount() == 8;
    }
}
