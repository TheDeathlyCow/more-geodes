package com.github.thedeathlycow.moregeodes.client;

import net.minecraft.client.render.entity.DisplayEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

/**
 * Extends block display entity renderer with a public constructor
 */
public class EchoDisplayEntityRenderer extends DisplayEntityRenderer.BlockDisplayEntityRenderer {
    public EchoDisplayEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }
}
