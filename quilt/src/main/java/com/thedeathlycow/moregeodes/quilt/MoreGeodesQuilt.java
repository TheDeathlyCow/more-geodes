package com.thedeathlycow.moregeodes.quilt;

import com.thedeathlycow.moregeodes.fabriclike.MoreGeodesFabricLike;
import net.fabricmc.api.ModInitializer;

public final class MoreGeodesQuilt implements ModInitializer {
    @Override
    public void onInitialize() {
        // Run the Fabric-like setup.
        // TODO: Add in QKL when updated to 1.21
        MoreGeodesFabricLike.INSTANCE.init();
    }
}
