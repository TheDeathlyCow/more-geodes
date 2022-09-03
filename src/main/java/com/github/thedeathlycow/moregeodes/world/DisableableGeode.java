package com.github.thedeathlycow.moregeodes.world;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.GeodeFeature;
import net.minecraft.world.gen.feature.GeodeFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.function.Consumer;
import java.util.function.Function;

public class DisableableGeode extends GeodeFeature {

    private final Disabler disabler;

    public DisableableGeode(Disabler disabler, Codec<GeodeFeatureConfig> codec) {
        super(codec);
        this.disabler = disabler;
    }

    public boolean generate(FeatureContext<GeodeFeatureConfig> context) {
        if (this.disabler.isDisabled()) {
            return false;
        } else {
            return super.generate(context);
        }
    }

    @FunctionalInterface
    public interface Disabler {
        boolean isDisabled();
    }
}

