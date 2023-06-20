package com.github.thedeathlycow.moregeodes.client;

import com.github.thedeathlycow.moregeodes.entity.GeodesEntityTypes;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class GeodesEntityRenderers {


    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(
                GeodesEntityTypes.ECHO_DISPLAY,
                EchoDisplayEntityRenderer::new
        );
    }

    private GeodesEntityRenderers() {

    }
}
