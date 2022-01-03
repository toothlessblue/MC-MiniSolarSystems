package net.fabricmc.MiniSolarSystems;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.TextureUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.*;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.IntBuffer;

public class PlanetRenderer {
    int[] textureIds = new int[6];

    public PlanetRenderer() {
        GL11.glGenTextures(this.textureIds);

        for (int i = 0; i < 6; i++) {
            RenderSystem.bindTextureForSetup(this.textureIds[i]);
            IntBuffer buffer = BufferUtils.createIntBuffer(16 * 16 * 4);
            TextureUtil.initTexture(buffer, 16, 16);
        }
    }

    private int getTexture(PlanetFace face) {
        return textureIds[face.getValue()];
    }

    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
        // TODO update textures

        // render textures



        GL11.glBindTexture(GL11.GL_TEXTURE, this.textureIds[0]);
        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getSolid());
        consumer.vertex(0, 0, 1).color(1,0,1, 1).texture(0, 0).light(0, 1).normal(0, 0, 1).next();
        consumer.vertex(0, 1, 1).color(1,0,1, 1).texture(1, 0).light(0, 1).normal(0, 0, 1).next();
        consumer.vertex(1, 1, 1).color(1,0,1, 1).texture(1, 1).light(0, 1).normal(0, 0, 1).next();
        consumer.vertex(1, 0, 1).color(1,0,1, 1).texture(0, 1).light(0, 1).normal(0, 0, 1).next();


    }
}
