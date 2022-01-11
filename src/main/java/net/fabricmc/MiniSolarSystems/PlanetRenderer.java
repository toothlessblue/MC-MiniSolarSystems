package net.fabricmc.MiniSolarSystems;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix4f;
import toothlessblue.TextureHelper;

public class PlanetRenderer {
    NativeImageBackedTexture texture;
    Identifier dynamicTextureId;
    int colour = 0;

    public PlanetRenderer() {
        this.texture = new NativeImageBackedTexture(96, 16, false);
        TextureHelper.blackenNativeImage(this.texture.getImage());
        this.texture.getImage().setColor(1,1, TextureHelper.RGBAtoINT(255, 0, 255));
        this.dynamicTextureId = MinecraftClient.getInstance().getTextureManager().registerDynamicTexture("toothlessblue_minisolarsystems", texture);

    }

    public void updateTexture() {
        this.colour++;

        if (this.colour > 255) {
            this.colour = 0;
        }

        this.texture.getImage().setColor(0, 0, TextureHelper.RGBAtoINT(this.colour, this.colour, this.colour));
        this.texture.upload();
    }

    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int overlay, int light) {
        this.updateTexture(); // TODO not to be called every frame, to be called every few seconds or so

        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(this.dynamicTextureId));
        Matrix4f position = matrices.peek().getPositionMatrix();

        consumer.vertex(position, 0, 0, 0).color(1f, 1f, 1f, 1f).texture(0f, 1f).overlay(overlay).light(light).normal(0, 0, 1).next();
        consumer.vertex(position, 0, 1, 0).color(1f, 1f, 1f, 1f).texture(0f, 0f).overlay(overlay).light(light).normal(0, 0, 1).next();
        consumer.vertex(position, 1, 1, 0).color(1f, 1f, 1f, 1f).texture(1f/6f, 0f).overlay(overlay).light(light).normal(0, 0, 1).next();
        consumer.vertex(position, 1, 0, 0).color(1f, 1f, 1f, 1f).texture(1f/6f, 1f).overlay(overlay).light(light).normal(0, 0, 1).next();
    }
}
