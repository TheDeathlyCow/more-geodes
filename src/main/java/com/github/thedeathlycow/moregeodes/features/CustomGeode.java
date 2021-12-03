package com.github.thedeathlycow.moregeodes.features;

import com.github.thedeathlycow.moregeodes.blocks.CustomBuddingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import org.jetbrains.annotations.NotNull;

/**
 * A class to make the creation of new geodes slightly easier.
 */
public class CustomGeode extends GeodeFeature {

    private CustomBuddingBlock buddingBlock;
    private Block baseBlock;
    private Block outerBlock;
    private Block calciteBlock;

    private final GeodeLayerConfig LAYER_CONFIG;
    private final GeodeFeatureConfig FEATURE_CONFIG;

    /**
     * Creates a custom geode with a budding block and base block.
     *
     * @param buddingBlock The budding block grows the crystals.
     * @param baseBlock    The primary block of the interior of the geode.
     */
    public CustomGeode(@NotNull CustomBuddingBlock buddingBlock, @NotNull Block baseBlock) {
        this(buddingBlock, baseBlock, Blocks.CALCITE, Blocks.SMOOTH_BASALT);
    }

    /**
     * Creates a custom geode with a budding block and base block.
     *
     * @param buddingBlock The budding block grows the crystals.
     * @param baseBlock    The primary block of the interior of the geode.
     * @param outerBlock   The outer shell block of the geode.
     */
    public CustomGeode(@NotNull CustomBuddingBlock buddingBlock, @NotNull Block baseBlock, @NotNull Block calciteBlock, @NotNull Block outerBlock) {
        super(GeodeFeatureConfig.CODEC);
        this.buddingBlock = buddingBlock;
        this.baseBlock = baseBlock;
        this.calciteBlock = calciteBlock;
        this.outerBlock = outerBlock;

        this.LAYER_CONFIG = new GeodeLayerConfig(
                SimpleBlockStateProvider.of(Blocks.AIR.getDefaultState()),
                SimpleBlockStateProvider.of(this.baseBlock.getDefaultState()),
                SimpleBlockStateProvider.of(this.buddingBlock.getDefaultState()),
                SimpleBlockStateProvider.of(this.calciteBlock.getDefaultState()),
                SimpleBlockStateProvider.of(this.outerBlock.getDefaultState()),
                buddingBlock.getClusterStates(),
                BlockTags.FEATURES_CANNOT_REPLACE.getId(),
                BlockTags.GEODE_INVALID_BLOCKS.getId()
        );

        // I don't know what half this stuff does and its not at all documented
        // so I'm just going to leave it as the same as amethysts.
        this.FEATURE_CONFIG = new GeodeFeatureConfig(
                LAYER_CONFIG,
                new GeodeLayerThicknessConfig(1.7D, 2.2D, 3.2D, 4.2D),
                new GeodeCrackConfig(0.95D, 2.0D, 2),
                0.35D, 0.083D, true,
                UniformIntProvider.create(4, 6),
                UniformIntProvider.create(3, 4),
                UniformIntProvider.create(1, 2),
                -16, 16, 0.05D, 1
        );
    }

    public GeodeFeatureConfig getFeatureConfig() {
        return this.FEATURE_CONFIG;
    }

    public GeodeLayerConfig getLayerConfig() {
        return LAYER_CONFIG;
    }
}