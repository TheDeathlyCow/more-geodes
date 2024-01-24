package com.github.thedeathlycow.moregeodes.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.DisplayEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.decoration.DisplayEntity;

public class EchoDisplayEntityRenderer extends DisplayEntityRenderer.BlockDisplayEntityRenderer {
    public EchoDisplayEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(
            DisplayEntity.BlockDisplayEntity blockDisplayEntity,
            DisplayEntity.BlockDisplayEntity.Data data,
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            int brightness, float lerpProgress
    ) {
        if (!blockDisplayEntity.isInvisible()) {
            super.render(blockDisplayEntity, data, matrixStack, vertexConsumerProvider, brightness, lerpProgress);
        }
    }
}
