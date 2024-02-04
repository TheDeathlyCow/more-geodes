package com.github.thedeathlycow.moregeodes.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.DisplayEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.IllusionerEntityRenderer;
import net.minecraft.client.render.entity.PillagerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.util.Identifier;

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

            Identifier texture = this.getTexture(blockDisplayEntity);

            var mockProvider = new VertexConsumerProvider() {
                @Override
                public VertexConsumer getBuffer(RenderLayer layer) {
                    return vertexConsumerProvider.getBuffer(RenderLayer.getOutline(texture));
                }
            };

            super.render(
                    blockDisplayEntity,
                    data,
                    matrixStack,
                    mockProvider,
                    brightness,
                    lerpProgress
            );
        }
    }
}
