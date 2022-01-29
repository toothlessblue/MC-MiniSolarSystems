package net.fabricmc.MiniSolarSystems;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix4f;
import toothlessblue.TextureHelper;

import java.awt.*;
import java.util.ArrayList;

public class PlanetRenderer {
    public static int rendererCount = 0;

    NativeImageBackedTexture texture;
    Identifier dynamicTextureId;
    int colour = 0;

    public PlanetRenderer() {
        PlanetRenderer.rendererCount++;

        this.texture = new NativeImageBackedTexture(96, 16, false);
        this.dynamicTextureId = MinecraftClient.getInstance().getTextureManager().registerDynamicTexture("toothlessblue_minisolarsystems_planet_renderer" + PlanetRenderer.rendererCount, texture);
    }

    public void updateTexture(PlanetTileRegistry tiles) {
        ArrayList<TileLocation> updates = tiles.getChanges();

        if (updates.size() == 0) return;

        for (TileLocation update : updates) {
            int offset = update.side * 16;
            this.texture.getImage().setColor(update.x + offset, update.y, tiles.getTileByLocation(update).getType().toColour());
        }

        this.texture.upload();
    }

    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int overlay, int light) {
        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(this.dynamicTextureId));
        Matrix4f position = matrices.peek().getPositionMatrix();

        // TOP
        consumer.vertex(position, 1, 1, 1).color(1f, 1f, 1f, 1f).texture(1f/6f, 0f).overlay(overlay).light(light).normal(0, 1, 0).next();
        consumer.vertex(position, 1, 1, 0).color(1f, 1f, 1f, 1f).texture(0f/6f, 0f).overlay(overlay).light(light).normal(0, 1, 0).next();
        consumer.vertex(position, 0, 1, 0).color(1f, 1f, 1f, 1f).texture(0f/6f, 1f).overlay(overlay).light(light).normal(0, 1, 0).next();
        consumer.vertex(position, 0, 1, 1).color(1f, 1f, 1f, 1f).texture(1f/6f, 1f).overlay(overlay).light(light).normal(0, 1, 0).next();

        // LEFT
        consumer.vertex(position, 0, 0, 0).color(1f, 1f, 1f, 1f).texture(2f/6f, 1f).overlay(overlay).light(light).normal(0, 0, -1).next();
        consumer.vertex(position, 0, 1, 0).color(1f, 1f, 1f, 1f).texture(2f/6f, 0f).overlay(overlay).light(light).normal(0, 0, -1).next();
        consumer.vertex(position, 1, 1, 0).color(1f, 1f, 1f, 1f).texture(1f/6f, 0f).overlay(overlay).light(light).normal(0, 0, -1).next();
        consumer.vertex(position, 1, 0, 0).color(1f, 1f, 1f, 1f).texture(1f/6f, 1f).overlay(overlay).light(light).normal(0, 0, -1).next();

        // FRONT
        consumer.vertex(position, 1, 0, 0).color(1f, 1f, 1f, 1f).texture(3f/6f, 1f).overlay(overlay).light(light).normal(1, 0, 1).next();
        consumer.vertex(position, 1, 1, 0).color(1f, 1f, 1f, 1f).texture(3f/6f, 0f).overlay(overlay).light(light).normal(1, 0, 1).next();
        consumer.vertex(position, 1, 1, 1).color(1f, 1f, 1f, 1f).texture(2f/6f, 0f).overlay(overlay).light(light).normal(1, 0, 1).next();
        consumer.vertex(position, 1, 0, 1).color(1f, 1f, 1f, 1f).texture(2f/6f, 1f).overlay(overlay).light(light).normal(1, 0, 1).next();

        // RIGHT
        consumer.vertex(position, 1, 1, 1).color(1f, 1f, 1f, 1f).texture(4f/6f, 0f).overlay(overlay).light(light).normal(0, 0, 1).next();
        consumer.vertex(position, 0, 1, 1).color(1f, 1f, 1f, 1f).texture(3f/6f, 0f).overlay(overlay).light(light).normal(0, 0, 1).next();
        consumer.vertex(position, 0, 0, 1).color(1f, 1f, 1f, 1f).texture(3f/6f, 1f).overlay(overlay).light(light).normal(0, 0, 1).next();
        consumer.vertex(position, 1, 0, 1).color(1f, 1f, 1f, 1f).texture(4f/6f, 1f).overlay(overlay).light(light).normal(0, 0, 1).next();

        // BACK
        consumer.vertex(position, 0, 1, 1).color(1f, 1f, 1f, 1f).texture(5f/6f, 0f).overlay(overlay).light(light).normal(-1, 0, 0).next();
        consumer.vertex(position, 0, 1, 0).color(1f, 1f, 1f, 1f).texture(4f/6f, 0f).overlay(overlay).light(light).normal(-1, 0, 0).next();
        consumer.vertex(position, 0, 0, 0).color(1f, 1f, 1f, 1f).texture(4f/6f, 1f).overlay(overlay).light(light).normal(-1, 0, 0).next();
        consumer.vertex(position, 0, 0, 1).color(1f, 1f, 1f, 1f).texture(5f/6f, 1f).overlay(overlay).light(light).normal(-1, 0, 0).next();

        // BOTTOM
        consumer.vertex(position, 0, 0, 0).color(1f, 1f, 1f, 1f).texture(6f/6f, 1f).overlay(overlay).light(light).normal(0, -1, 0).next();
        consumer.vertex(position, 1, 0, 0).color(1f, 1f, 1f, 1f).texture(6f/6f, 0f).overlay(overlay).light(light).normal(0, -1, 0).next();
        consumer.vertex(position, 1, 0, 1).color(1f, 1f, 1f, 1f).texture(5f/6f, 0f).overlay(overlay).light(light).normal(0, -1, 0).next();
        consumer.vertex(position, 0, 0, 1).color(1f, 1f, 1f, 1f).texture(5f/6f, 1f).overlay(overlay).light(light).normal(0, -1, 0).next();


    }
}
