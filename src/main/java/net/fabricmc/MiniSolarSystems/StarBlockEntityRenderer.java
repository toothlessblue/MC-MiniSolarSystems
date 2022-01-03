package net.fabricmc.MiniSolarSystems;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.TextureUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.realms.util.RealmsTextureManager;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.SpriteAtlasManager;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.data.client.model.Texture;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.awt.*;
import java.util.Random;

public class StarBlockEntityRenderer implements BlockEntityRenderer<StarBlockEntity> {
    private static ItemStack stack = new ItemStack(Items.DIRT, 1);

    private static final float ORBIT_SECONDS_PER_DISTANCE = 10.0f;
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
        //matrices.translate(ORBIT_DISTANCE * Math.cos(time) + 0.5f, 0.5f, ORBIT_DISTANCE * Math.sin(time) + 0.5f);

        // Rotate the planet
        //matrices.multiply(new Vec3f(0.2f, 1, 0).getDegreesQuaternion((blockEntity.getWorld().getTime() + tickDelta) * PLANET_ROTATION_SPEED));

        // Scale the planet
        //matrices.scale(0.25f, 0.25f, 0.25f);

        // Render the "planet"

        //new PlanetRenderer().render(matrices);

        new PlanetRenderer().render(matrices, vertexConsumers);

        // Mandatory call after GL calls
        matrices.pop();
    }
}