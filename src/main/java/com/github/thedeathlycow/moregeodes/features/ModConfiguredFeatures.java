package com.github.thedeathlycow.moregeodes.features;

import appeng.core.definitions.AEBlocks;
import com.github.thedeathlycow.moregeodes.MoreGeodes;
import com.github.thedeathlycow.moregeodes.blocks.CrystalClusterBlock;
import com.github.thedeathlycow.moregeodes.blocks.LargeCrystalClusterBlock;
import com.github.thedeathlycow.moregeodes.blocks.ModBlocks;
import com.github.thedeathlycow.moregeodes.tag.ModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModConfiguredFeatures {

    public static final RegistryEntry<ConfiguredFeature<?, ?>> EMERALD_GEODE;
    public static final RegistryEntry<ConfiguredFeature<?, ?>> QUARTZ_GEODE;
    public static final RegistryEntry<ConfiguredFeature<?, ?>> DIAMOND_GEODE;
    public static final RegistryEntry<ConfiguredFeature<?, ?>> ECHO_GEODE;
    public static final RegistryEntry<ConfiguredFeature<?, ?>> LAPIS_GEODE;

    public static final RegistryEntry<ConfiguredFeature<?, ?>> GYPSUM_CRYSTALS;
    public static final RegistryEntry<ConfiguredFeature<?, ?>> GYPSUM_PATCH;

    @Nullable
    public static final RegistryEntry<ConfiguredFeature<?, ?>> CERTUS_GEODE;

    private static RegistryEntry<ConfiguredFeature<?, ?>> register(String id, ConfiguredFeature<?, ?> configuredFeature) {
        return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MoreGeodes.MODID, id), configuredFeature);
    }

    static {
        EMERALD_GEODE = register("emerald_geode", new ConfiguredFeature<>(Feature.GEODE, new GeodeFeatureConfig
                (
                        new GeodeLayerConfig
                                (
                                        SimpleBlockStateProvider.of(Blocks.AIR.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.EMERALD_GEODE.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.BUDDING_EMERALD.getDefaultState()),
                                        SimpleBlockStateProvider.of(Blocks.CALCITE.getDefaultState()),
                                        SimpleBlockStateProvider.of(Blocks.SMOOTH_BASALT.getDefaultState()),
                                        ModBlocks.BUDDING_EMERALD.getClusterStates(),
                                        BlockTags.FEATURES_CANNOT_REPLACE,
                                        BlockTags.GEODE_INVALID_BLOCKS
                                ),
                        new GeodeLayerThicknessConfig(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackConfig(0.95D, 2.0D, 2),
                        0.35D, 0.083D, true,
                        UniformIntProvider.create(4, 6),
                        UniformIntProvider.create(3, 4),
                        UniformIntProvider.create(1, 2),
                        -16, 16, 0.05D, 1
                )
        ));

        QUARTZ_GEODE = register("quartz_geode", new ConfiguredFeature<>(Feature.GEODE, new GeodeFeatureConfig
                (
                        new GeodeLayerConfig
                                (
                                        SimpleBlockStateProvider.of(Blocks.AIR.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.QUARTZ_GEODE.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.BUDDING_QUARTZ.getDefaultState()),
                                        SimpleBlockStateProvider.of(Blocks.TUFF.getDefaultState()),
                                        SimpleBlockStateProvider.of(Blocks.SMOOTH_BASALT.getDefaultState()),
                                        ModBlocks.BUDDING_QUARTZ.getClusterStates(),
                                        BlockTags.FEATURES_CANNOT_REPLACE,
                                        BlockTags.GEODE_INVALID_BLOCKS
                                ),
                        new GeodeLayerThicknessConfig(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackConfig(0.95D, 2.0D, 2),
                        0.35D, 0.083D, true,
                        UniformIntProvider.create(4, 6),
                        UniformIntProvider.create(3, 4),
                        UniformIntProvider.create(1, 2),
                        -16, 16, 0.05D, 1
                )
        ));

        DIAMOND_GEODE = register("diamond_geode", new ConfiguredFeature<>(Feature.GEODE, new GeodeFeatureConfig
                (
                        new GeodeLayerConfig
                                (
                                        SimpleBlockStateProvider.of(Blocks.WATER.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.DIAMOND_GEODE.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.DIAMOND_GEODE.getDefaultState()),
                                        new WeightedBlockStateProvider(
                                                DataPool.<BlockState>builder()
                                                        .add(Blocks.DEEPSLATE_COAL_ORE.getDefaultState(), 1)
                                                        .add(Blocks.SMOOTH_BASALT.getDefaultState(), 4)
                                                        .build()
                                        ),
                                        SimpleBlockStateProvider.of(Blocks.SMOOTH_BASALT.getDefaultState()),
                                        List.of(ModBlocks.DIAMOND_CLUSTER.getDefaultState(), ModBlocks.DIAMOND_CLUSTER.getDefaultState()),
                                        BlockTags.FEATURES_CANNOT_REPLACE,
                                        BlockTags.GEODE_INVALID_BLOCKS
                                ),
                        new GeodeLayerThicknessConfig(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackConfig(0.0D, 1.0D, 2),
                        0.35D, 0.083D, true,
                        UniformIntProvider.create(4, 6),
                        UniformIntProvider.create(3, 4),
                        UniformIntProvider.create(1, 2),
                        -16, 16, 0.05D, 1
                )
        ));

        ECHO_GEODE = register("echo_geode", new ConfiguredFeature<>(Feature.GEODE, new GeodeFeatureConfig
                (
                        new GeodeLayerConfig
                                (
                                        SimpleBlockStateProvider.of(Blocks.AIR.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.ECHO_BLOCK.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.BUDDING_ECHO_BLOCK.getDefaultState()),
                                        SimpleBlockStateProvider.of(Blocks.SCULK.getDefaultState()),
                                        SimpleBlockStateProvider.of(Blocks.BLACKSTONE.getDefaultState()),
                                        ModBlocks.BUDDING_ECHO_BLOCK.getClusterStates(),
                                        BlockTags.FEATURES_CANNOT_REPLACE,
                                        BlockTags.GEODE_INVALID_BLOCKS
                                ),
                        new GeodeLayerThicknessConfig(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackConfig(0.95D, 2.0D, 2),
                        0.35D, 0.083D, true,
                        UniformIntProvider.create(4, 6),
                        UniformIntProvider.create(3, 4),
                        UniformIntProvider.create(1, 2),
                        -16, 16, 0.05D, 1
                )
        ));

        LAPIS_GEODE = register("lapis_geode", new ConfiguredFeature<>(Feature.GEODE, new GeodeFeatureConfig
                (
                        new GeodeLayerConfig
                                (
                                        SimpleBlockStateProvider.of(Blocks.AIR.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.LAPIS_CRYSTAL_BLOCK.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.BUDDING_LAPIS.getDefaultState()),
                                        SimpleBlockStateProvider.of(ModBlocks.PYRITE.getDefaultState()),
                                        SimpleBlockStateProvider.of(Blocks.SMOOTH_BASALT.getDefaultState()),
                                        ModBlocks.BUDDING_LAPIS.getClusterStates(),
                                        BlockTags.FEATURES_CANNOT_REPLACE,
                                        BlockTags.GEODE_INVALID_BLOCKS
                                ),
                        new GeodeLayerThicknessConfig(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackConfig(0.95D, 2.0D, 2),
                        0.35D, 0.083D, true,
                        UniformIntProvider.create(4, 6),
                        UniformIntProvider.create(3, 4),
                        UniformIntProvider.create(1, 2),
                        -16, 16, 0.05D, 1
                )
        ));

        GYPSUM_CRYSTALS = register("gypsum_crystals", new ConfiguredFeature<>(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(
                        new WeightedBlockStateProvider(
                                new DataPool.Builder<BlockState>()
                                        .add(
                                                ModBlocks.GYPSUM_ROSE.getDefaultState()
                                                        .with(LargeCrystalClusterBlock.HALF, DoubleBlockHalf.LOWER)
                                                        .with(CrystalClusterBlock.FACING, Direction.UP),
                                                1
                                        )
                                        .add(
                                                ModBlocks.LARGE_GYPSUM_BUD.getDefaultState()
                                                        .with(LargeCrystalClusterBlock.HALF, DoubleBlockHalf.LOWER)
                                                        .with(CrystalClusterBlock.FACING, Direction.UP),
                                                2
                                        )
                                        .add(
                                                ModBlocks.MEDIUM_GYPSUM_BUD.getDefaultState()
                                                        .with(CrystalClusterBlock.FACING, Direction.UP),
                                                3
                                        )
                                        .add(
                                                ModBlocks.SMALL_GYPSUM_BUD.getDefaultState()
                                                        .with(CrystalClusterBlock.FACING, Direction.UP),
                                                4
                                        )
                                        .build()
                        )
                )
        ));

        GYPSUM_PATCH = register("gypsum_patch", new ConfiguredFeature<>(
                Feature.VEGETATION_PATCH,
                new VegetationPatchFeatureConfig(
                        ModBlockTags.GYPSUM_PATCH_REPLACEABLE,
                        new WeightedBlockStateProvider(
                                new DataPool.Builder<BlockState>()
                                        .add(ModBlocks.GYPSUM_CRYSTAL_BLOCK.getDefaultState(), 9)
                                        .add(ModBlocks.BUDDING_GYPSUM.getDefaultState(), 1)
                                        .build()
                        ),
                        PlacedFeatures.createEntry(GYPSUM_CRYSTALS),
                        VerticalSurfaceType.FLOOR,
                        UniformIntProvider.create(2, 3),
                        0,
                        5,
                        0.4f,
                        UniformIntProvider.create(1, 3),
                        0.3f
                )
        ));

        if (MoreGeodes.isAE2Loaded()) {
            CERTUS_GEODE = register("certus_geode", new ConfiguredFeature<>(Feature.GEODE, new GeodeFeatureConfig(
                    new GeodeLayerConfig(
                            SimpleBlockStateProvider.of(Blocks.WATER.getDefaultState()),
                            SimpleBlockStateProvider.of(AEBlocks.DAMAGED_BUDDING_QUARTZ.block()),
                            SimpleBlockStateProvider.of(AEBlocks.QUARTZ_BLOCK.block()),
                            SimpleBlockStateProvider.of(Blocks.OBSIDIAN),
                            SimpleBlockStateProvider.of(AEBlocks.SKY_STONE_BLOCK.block()),
                            List.of(
                                    AEBlocks.SMALL_QUARTZ_BUD.block().getDefaultState(),
                                    AEBlocks.MEDIUM_QUARTZ_BUD.block().getDefaultState()
                            ),
                            BlockTags.FEATURES_CANNOT_REPLACE,
                            BlockTags.GEODE_INVALID_BLOCKS
                    ),
                    new GeodeLayerThicknessConfig(1d, 1d, 1.5d, 2d),
                    new GeodeCrackConfig(0.2d, 0.5d, 2),
                    0.25d,
                    0.85d,
                    true,
                    UniformIntProvider.create(3, 5),
                    UniformIntProvider.create(3, 4),
                    UniformIntProvider.create(1, 2),
                    -16,
                    16,
                    0.05d,
                    1
            )));
        } else {
            CERTUS_GEODE = null;
        }
    }

}
