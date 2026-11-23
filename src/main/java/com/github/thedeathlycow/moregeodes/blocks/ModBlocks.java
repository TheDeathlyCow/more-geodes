package com.github.thedeathlycow.moregeodes.blocks;

import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.sounds.CrystalBlockSoundGroup;
import com.github.thedeathlycow.moregeodes.sounds.ModBlockSoundGroups;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import java.util.List;


public class ModBlocks {

    public static final Block EMERALD_CRYSTAL_BLOCK = new CrystalBlock(
            CrystalBlockSoundGroup.EMERALD_GEODE,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "emerald_crystal_block")))
                    .mapColor(MapColor.EMERALD)
                    .strength(1.5F)
                    .sound(ModBlockSoundGroups.EMERALD_GEODE)
                    .requiresCorrectToolForDrops()
    );

    public static final Block EMERALD_CLUSTER = new CrystalClusterBlock(
            CrystalBlockSoundGroup.EMERALD_GEODE,
            7, 3,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "emerald_cluster")))
                    .mapColor(MapColor.EMERALD)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.EMERALD_CLUSTER)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 5)
                    .pushReaction(PushReaction.POPPED)
    );
    public static final Block LARGE_EMERALD_BUD = new CrystalClusterBlock(
            CrystalBlockSoundGroup.EMERALD_GEODE,
            5, 3,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "large_emerald_bud")))
                    .mapColor(MapColor.EMERALD)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.LARGE_EMERALD_BUD)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 4)
                    .pushReaction(PushReaction.POPPED)
    );
    public static final Block MEDIUM_EMERALD_BUD = new CrystalClusterBlock(
            CrystalBlockSoundGroup.EMERALD_GEODE,
            4, 3,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "medium_emerald_bud")))
                    .mapColor(MapColor.EMERALD)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.MEDIUM_EMERALD_BUD)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 2)
                    .pushReaction(PushReaction.POPPED)
    );
    public static final Block SMALL_EMERALD_BUD = new CrystalClusterBlock(
            CrystalBlockSoundGroup.EMERALD_GEODE,
            3, 4,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "small_emerald_bud")))
                    .mapColor(MapColor.EMERALD)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.SMALL_EMERALD_BUD)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 1)
                    .pushReaction(PushReaction.POPPED)
    );

    public static final Block QUARTZ_CRYSTAL_BLOCK = new CrystalBlock(
            CrystalBlockSoundGroup.QUARTZ_GEODE,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "quartz_crystal_block")))
                    .mapColor(MapColor.QUARTZ)
                    .strength(1.5F)
                    .sound(ModBlockSoundGroups.QUARTZ_GEODE)
                    .requiresCorrectToolForDrops()
    );

    public static final Block QUARTZ_CLUSTER = new QuartzClusterBlock(
            7, 3,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "quartz_cluster")))
                    .mapColor(MapColor.QUARTZ)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.QUARTZ_CLUSTER)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 5)
                    .pushReaction(PushReaction.POPPED),
            15, 1
    );
    public static final Block LARGE_QUARTZ_BUD = new QuartzClusterBlock(
            5, 3,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "large_quartz_bud")))
                    .mapColor(MapColor.QUARTZ)
                    .noOcclusion().requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.LARGE_QUARTZ_BUD)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 4)
                    .pushReaction(PushReaction.POPPED),
            15, 5
    );
    public static final Block MEDIUM_QUARTZ_BUD = new QuartzClusterBlock(
            4, 3,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "medium_quartz_bud")))
                    .mapColor(MapColor.QUARTZ)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.MEDIUM_QUARTZ_BUD)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 2)
                    .pushReaction(PushReaction.POPPED),
            15, 10
    );
    public static final Block SMALL_QUARTZ_BUD = new QuartzClusterBlock(
            3, 4,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "small_quartz_bud")))
                    .mapColor(MapColor.QUARTZ)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.SMALL_QUARTZ_BUD)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 1)
                    .pushReaction(PushReaction.POPPED),
            15, 20
    );

    public static final Block DIAMOND_CRYSTAL_BLOCK = new CrystalBlock(
            CrystalBlockSoundGroup.DIAMOND_GEODE,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "diamond_crystal_block")))
                    .mapColor(MapColor.DIAMOND)
                    .strength(1.5F)
                    .sound(ModBlockSoundGroups.DIAMOND_GEODE)
                    .requiresCorrectToolForDrops()
    );

    public static final Block DIAMOND_CLUSTER = new CrystalClusterBlock(
            CrystalBlockSoundGroup.DIAMOND_GEODE,
            7, 3,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "diamond_cluster")))
                    .mapColor(MapColor.DIAMOND)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.DIAMOND_CLUSTER)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 7)
                    .pushReaction(PushReaction.POPPED)
    );

    public static final Block ECHO_BLOCK = new CrystalBlock(
            CrystalBlockSoundGroup.ECHO_BLOCK,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "echo_block")))
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(1.5f)
                    .sound(ModBlockSoundGroups.ECHO_BLOCK)
                    .requiresCorrectToolForDrops()
    );

    public static final Block ECHO_CLUSTER = new CrystalClusterBlock(
            CrystalBlockSoundGroup.ECHO_BLOCK,
            7, 3,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "echo_cluster")))
                    .mapColor(MapColor.COLOR_BLACK)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.ECHO_CLUSTER)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 5)
                    .pushReaction(PushReaction.POPPED)
    );
    public static final Block LARGE_ECHO_BUD = new CrystalClusterBlock(
            CrystalBlockSoundGroup.ECHO_BLOCK,
            5, 3,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "large_echo_bud")))
                    .mapColor(MapColor.COLOR_BLACK)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.LARGE_ECHO_BUD)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 4)
                    .pushReaction(PushReaction.POPPED)
    );
    public static final Block MEDIUM_ECHO_BUD = new CrystalClusterBlock(
            CrystalBlockSoundGroup.ECHO_BLOCK,
            4, 3,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "medium_echo_bud")))
                    .mapColor(MapColor.COLOR_BLACK)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.MEDIUM_ECHO_BUD)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 2)
                    .pushReaction(PushReaction.POPPED)
    );
    public static final Block SMALL_ECHO_BUD = new CrystalClusterBlock(
            CrystalBlockSoundGroup.ECHO_BLOCK,
            3, 4,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "small_echo_bud")))
                    .mapColor(MapColor.COLOR_BLACK)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.SMALL_ECHO_BUD)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 1)
                    .pushReaction(PushReaction.POPPED)
    );

    public static final Block LAPIS_CRYSTAL_BLOCK = new CrystalBlock(
            CrystalBlockSoundGroup.LAPIS,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "lapis_crystal_block")))
                    .mapColor(MapColor.LAPIS)
                    .strength(1.5f)
                    .sound(ModBlockSoundGroups.LAPIS_CRYSTAL_BLOCK)
                    .requiresCorrectToolForDrops()
    );
    public static final Block LAPIS_CLUSTER = new CrystalClusterBlock(
            CrystalBlockSoundGroup.LAPIS,
            7, 3,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "lapis_cluster")))
                    .mapColor(MapColor.LAPIS)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.LAPIS_CLUSTER)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 5)
                    .pushReaction(PushReaction.POPPED)
    );
    public static final Block LARGE_LAPIS_BUD = new CrystalClusterBlock(
            CrystalBlockSoundGroup.LAPIS,
            5, 3,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "large_lapis_bud")))
                    .mapColor(MapColor.LAPIS)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.LARGE_LAPIS_BUD)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 4)
                    .pushReaction(PushReaction.POPPED)
    );
    public static final Block MEDIUM_LAPIS_BUD = new CrystalClusterBlock(
            CrystalBlockSoundGroup.LAPIS,
            4, 3,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "medium_lapis_bud")))
                    .mapColor(MapColor.LAPIS)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.MEDIUM_LAPIS_BUD)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 2)
                    .pushReaction(PushReaction.POPPED)
    );
    public static final Block SMALL_LAPIS_BUD = new CrystalClusterBlock(
            CrystalBlockSoundGroup.LAPIS,
            3, 4,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "small_lapis_bud")))
                    .mapColor(MapColor.LAPIS)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.SMALL_LAPIS_BUD)
                    .strength(1.5f)
                    .lightLevel((blockState) -> 1)
                    .pushReaction(PushReaction.POPPED)
    );


    public static final Block GYPSUM_CRYSTAL_BLOCK = new CrystalBlock(
            CrystalBlockSoundGroup.GYPSUM,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "gypsum_crystal_block")))
                    .mapColor(MapColor.WOOL)
                    .strength(1.3f)
                    .sound(ModBlockSoundGroups.GYPSUM_CRYSTAL_BLOCK)
                    .requiresCorrectToolForDrops()
    );
    public static final Block GYPSUM_ROSE = new LargeCrystalClusterBlock(
            CrystalBlockSoundGroup.GYPSUM,
            13, 3,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "gypsum_rose")))
                    .mapColor(MapColor.WOOL)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.GYPSUM_CRYSTAL_BLOCK)
                    .strength(1.3f)
                    .pushReaction(PushReaction.POPPED)
    );
    public static final Block LARGE_GYPSUM_BUD = new LargeCrystalClusterBlock(
            CrystalBlockSoundGroup.GYPSUM,
            5, 3,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "large_gypsum_bud")))
                    .mapColor(MapColor.WOOL)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.GYPSUM_CRYSTAL_BLOCK)
                    .strength(1.3f)
                    .pushReaction(PushReaction.POPPED)
    );
    public static final Block MEDIUM_GYPSUM_BUD = new CrystalClusterBlock(
            CrystalBlockSoundGroup.LAPIS,
            16, 3,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "medium_gypsum_bud")))
                    .mapColor(MapColor.LAPIS)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.GYPSUM_CRYSTAL_BLOCK)
                    .strength(1.3f)
                    .pushReaction(PushReaction.POPPED)
    );
    public static final Block SMALL_GYPSUM_BUD = new CrystalClusterBlock(
            CrystalBlockSoundGroup.LAPIS,
            11, 4,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "small_gypsum_bud")))
                    .mapColor(MapColor.LAPIS)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(ModBlockSoundGroups.GYPSUM_CRYSTAL_BLOCK)
                    .strength(1.3f)
                    .pushReaction(PushReaction.POPPED)
    );

    public static final Block GABBRO = new Block(
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "gabbro")))
                    .mapColor(MapColor.COLOR_BLACK)
                    .requiresCorrectToolForDrops()
                    .strength(1.25F, 4.2F)
                    .sound(SoundType.BASALT)
    );

    public static final GeodeBuddingBlock BUDDING_EMERALD = new GeodeBuddingBlock(
            CrystalBlockSoundGroup.EMERALD_GEODE,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "budding_emerald")))
                    .mapColor(MapColor.EMERALD)
                    .randomTicks()
                    .strength(1.5F)
                    .sound(ModBlockSoundGroups.EMERALD_GEODE)
                    .requiresCorrectToolForDrops(),
            List.of(SMALL_EMERALD_BUD, MEDIUM_EMERALD_BUD, LARGE_EMERALD_BUD, EMERALD_CLUSTER)
    );

    public static final GeodeBuddingBlock BUDDING_QUARTZ = new GeodeBuddingBlock(
            CrystalBlockSoundGroup.QUARTZ_GEODE,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "budding_quartz")))
                    .mapColor(MapColor.QUARTZ)
                    .randomTicks()
                    .strength(1.5f)
                    .sound(ModBlockSoundGroups.QUARTZ_GEODE)
                    .requiresCorrectToolForDrops(),
            List.of(SMALL_QUARTZ_BUD, MEDIUM_QUARTZ_BUD, LARGE_QUARTZ_BUD, QUARTZ_CLUSTER)
    );

    public static final GeodeBuddingBlock BUDDING_ECHO_BLOCK = new GeodeBuddingBlock(
            CrystalBlockSoundGroup.ECHO_BLOCK,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "budding_echo_block")))
                    .mapColor(MapColor.COLOR_BLACK)
                    .randomTicks()
                    .strength(1.5f)
                    .sound(ModBlockSoundGroups.ECHO_BLOCK)
                    .requiresCorrectToolForDrops(),
            List.of(SMALL_ECHO_BUD, MEDIUM_ECHO_BUD, LARGE_ECHO_BUD, ECHO_CLUSTER)
    );

    public static final GeodeBuddingBlock BUDDING_LAPIS = new GeodeBuddingBlock(
            CrystalBlockSoundGroup.LAPIS,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "budding_lapis")))
                    .mapColor(MapColor.COLOR_BLACK)
                    .randomTicks()
                    .strength(1.5f)
                    .sound(ModBlockSoundGroups.LAPIS_CRYSTAL_BLOCK)
                    .requiresCorrectToolForDrops(),
            List.of(SMALL_LAPIS_BUD, MEDIUM_LAPIS_BUD, LARGE_LAPIS_BUD, LAPIS_CLUSTER)
    );

    public static final Block BUDDING_GYPSUM = new DirectedBuddingBlock(
            Direction.UP,
            CrystalBlockSoundGroup.GYPSUM,
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "budding_gypsum")))
                    .mapColor(MapColor.WOOL)
                    .randomTicks()
                    .strength(1.3f)
                    .sound(ModBlockSoundGroups.GYPSUM_CRYSTAL_BLOCK)
                    .requiresCorrectToolForDrops(),
            List.of(SMALL_GYPSUM_BUD, MEDIUM_GYPSUM_BUD, LARGE_GYPSUM_BUD, GYPSUM_ROSE)
    );


    public static final Block PYRITE = new Block(
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "pyrite")))
                    .mapColor(MapColor.GOLD)
                    .sound(SoundType.CALCITE)
                    .requiresCorrectToolForDrops()
                    .strength(0.75F)
    );

    public static final Block PYRITE_STAIRS = new StairBlock(
            PYRITE.defaultBlockState(),
            BlockBehaviour.Properties.ofFullCopy(PYRITE).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "pyrite_stairs")))
    );

    public static final Block PYRITE_SLAB = new SlabBlock(
            BlockBehaviour.Properties.ofFullCopy(PYRITE).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "pyrite_slab")))
    );

    public static final Block PYRITE_WALL = new WallBlock(
            BlockBehaviour.Properties.ofFullCopy(PYRITE).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "pyrite_wall")))
    );

    public static final Block CALCITE_STAIRS = new StairBlock(
            Blocks.CALCITE.defaultBlockState(),
            BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "calcite_stairs")))
    );

    public static final Block CALCITE_SLAB = new SlabBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "calcite_slab")))
    );

    public static final Block CALCITE_WALL = new WallBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "calcite_wall")))
    );

    public static final Block CHISELED_GYPSUM = new Block(
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "chiseled_gypsum")))
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(0.8f)
    );

    public static final Block GYPSUM_PILLAR = new RotatedPillarBlock(
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "gypsum_pillar")))
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(0.8f)
    );

    public static final Block POLISHED_GYPSUM_BLOCK = new Block(
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "polished_gypsum_block")))
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(0.8f)
    );

    public static final Block POLISHED_GYPSUM_STAIRS = new StairBlock(
            POLISHED_GYPSUM_BLOCK.defaultBlockState(),
            BlockBehaviour.Properties.ofFullCopy(POLISHED_GYPSUM_BLOCK).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "polished_gypsum_stairs")))
    );

    public static final Block POLISHED_GYPSUM_SLAB = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_GYPSUM_BLOCK).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "polished_gypsum_slab"))));

    public static final Block POLISHED_GYPSUM_WALL = new WallBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_GYPSUM_BLOCK).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "polished_gypsum_wall"))));

    public static final Block SMOOTH_GYPSUM_BLOCK = new Block(
            BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "smooth_gypsum_block")))
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(0.8f)
    );

    public static final Block SMOOTH_GYPSUM_STAIRS = new StairBlock(
            SMOOTH_GYPSUM_BLOCK.defaultBlockState(),
            BlockBehaviour.Properties.ofFullCopy(SMOOTH_GYPSUM_BLOCK).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "smooth_gypsum_stairs")))
    );

    public static final Block SMOOTH_GYPSUM_SLAB = new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SMOOTH_GYPSUM_BLOCK).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "smooth_gypsum_slab"))));

    public static final Block SMOOTH_GYPSUM_WALL = new WallBlock(BlockBehaviour.Properties.ofFullCopy(SMOOTH_GYPSUM_BLOCK).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, "smooth_gypsum_wall"))));


    public static void registerBlocks() {
        register("emerald_crystal_block", EMERALD_CRYSTAL_BLOCK);
        register("budding_emerald", BUDDING_EMERALD);
        register("emerald_cluster", EMERALD_CLUSTER);
        register("large_emerald_bud", LARGE_EMERALD_BUD);
        register("medium_emerald_bud", MEDIUM_EMERALD_BUD);
        register("small_emerald_bud", SMALL_EMERALD_BUD);
        register("quartz_crystal_block", QUARTZ_CRYSTAL_BLOCK);
        register("budding_quartz", BUDDING_QUARTZ);
        register("quartz_cluster", QUARTZ_CLUSTER);
        register("large_quartz_bud", LARGE_QUARTZ_BUD);
        register("medium_quartz_bud", MEDIUM_QUARTZ_BUD);
        register("small_quartz_bud", SMALL_QUARTZ_BUD);
        register("diamond_crystal_block", DIAMOND_CRYSTAL_BLOCK);
        register("diamond_cluster", DIAMOND_CLUSTER);
        register("echo_block", ECHO_BLOCK);
        register("budding_echo_block", BUDDING_ECHO_BLOCK);
        register("echo_cluster", ECHO_CLUSTER);
        register("large_echo_bud", LARGE_ECHO_BUD);
        register("medium_echo_bud", MEDIUM_ECHO_BUD);
        register("small_echo_bud", SMALL_ECHO_BUD);
        register("lapis_crystal_block", LAPIS_CRYSTAL_BLOCK);
        register("lapis_cluster", LAPIS_CLUSTER);
        register("large_lapis_bud", LARGE_LAPIS_BUD);
        register("medium_lapis_bud", MEDIUM_LAPIS_BUD);
        register("small_lapis_bud", SMALL_LAPIS_BUD);
        register("budding_lapis", BUDDING_LAPIS);
        register("pyrite", PYRITE);
        register("pyrite_stairs", PYRITE_STAIRS);
        register("pyrite_slab", PYRITE_SLAB);
        register("pyrite_wall", PYRITE_WALL);
        register("calcite_stairs", CALCITE_STAIRS);
        register("calcite_slab", CALCITE_SLAB);
        register("calcite_wall", CALCITE_WALL);
        register("gypsum_crystal_block", GYPSUM_CRYSTAL_BLOCK);
        register("gypsum_rose", GYPSUM_ROSE);
        register("large_gypsum_bud", LARGE_GYPSUM_BUD);
        register("medium_gypsum_bud", MEDIUM_GYPSUM_BUD);
        register("small_gypsum_bud", SMALL_GYPSUM_BUD);
        register("budding_gypsum", BUDDING_GYPSUM);
        register("gabbro", GABBRO);
        register("chiseled_gypsum", CHISELED_GYPSUM);
        register("gypsum_pillar", GYPSUM_PILLAR);
        register("polished_gypsum_block", POLISHED_GYPSUM_BLOCK);
        register("polished_gypsum_stairs", POLISHED_GYPSUM_STAIRS);
        register("polished_gypsum_slab", POLISHED_GYPSUM_SLAB);
        register("polished_gypsum_wall", POLISHED_GYPSUM_WALL);
        register("smooth_gypsum_block", SMOOTH_GYPSUM_BLOCK);
        register("smooth_gypsum_stairs", SMOOTH_GYPSUM_STAIRS);
        register("smooth_gypsum_slab", SMOOTH_GYPSUM_SLAB);
        register("smooth_gypsum_wall", SMOOTH_GYPSUM_WALL);
    }

    private static void register(String name, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(MoreGeodes.MODID, name), block);
    }

}
