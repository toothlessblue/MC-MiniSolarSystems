package net.fabricmc.MiniSolarSystems;

import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.Shader;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.texture.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StarBlockEntityRenderer implements BlockEntityRenderer<StarBlockEntity> {
    private static final float ORBIT_SECONDS_PER_DISTANCE = 30.0f;
    private static final float ORBIT_DISTANCE = 2.5f;
    private static final float PLANET_ROTATION_SPEED = 1.0f;
    private static final float TIME_PER_ORBIT = ORBIT_DISTANCE * ORBIT_SECONDS_PER_DISTANCE;

    // I don't understand this
    // it should be:
    //      2.0f * (float)(Math.PI) / TIME_PER_ORBIT;
    private static final float ORBIT_SPEED = (float)(Math.PI) / (10.0f * TIME_PER_ORBIT);

    @Override
    public void render(StarBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        float time = (blockEntity.getWorld().getTime() + tickDelta) * ORBIT_SPEED;

        // Move the planet
        matrices.translate(ORBIT_DISTANCE * Math.cos(time) + 0.5f, 0.5f, ORBIT_DISTANCE * Math.sin(time) + 0.5f);

        // Rotate the planet
        matrices.multiply(new Vec3f(0.2f, 1, 0).getDegreesQuaternion((blockEntity.getWorld().getTime() + tickDelta) * PLANET_ROTATION_SPEED));

        // Scale the planet
        matrices.scale(0.25f, 0.25f, 0.25f);

        // TODO individual planet motion

        // Render the planets of this star
        for (Planet planet : blockEntity.getPlanets()) {
            planet.render(matrices, vertexConsumers, overlay, light);
        }

        // Mandatory call after GL calls
        matrices.pop();
    }
}