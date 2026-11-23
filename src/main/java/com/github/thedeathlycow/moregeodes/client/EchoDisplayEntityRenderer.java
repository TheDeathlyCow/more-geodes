package com.github.thedeathlycow.moregeodes.client;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.BlockDisplayEntityRenderState;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.world.entity.Display;
public class EchoDisplayEntityRenderer extends DisplayRenderer.BlockDisplayRenderer {
    public EchoDisplayEntityRenderer(EntityRendererProvider.Context context) {super(context);}
    @Override public boolean shouldRender(Display.BlockDisplay entity, Frustum frustum,
            double camX, double camY, double camZ, float partialTicks) {
        // Reject pending waves before render-state/model extraction allocates
        // and resolves geometry that would immediately be cleared below.
        return !entity.isInvisible() && super.shouldRender(entity, frustum, camX, camY, camZ, partialTicks);
    }
    @Override public void submitInner(BlockDisplayEntityRenderState state, com.mojang.blaze3d.vertex.PoseStack poses,
        net.minecraft.client.renderer.SubmitNodeCollector collector, int light, float interpolation) {
        state.blockModel.submitOnlyOutline(poses,collector,light,net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY,state.outlineColor);
    }
    @Override public void extractRenderState(Display.BlockDisplay entity,BlockDisplayEntityRenderState state,float delta) {
        super.extractRenderState(entity,state,delta);
        // Invisible until the wave reaches this block. The current outline pipeline
        // submits the visible display's glowing silhouette through terrain.
        if(entity.isInvisible()) state.blockModel.clear();
    }
}
