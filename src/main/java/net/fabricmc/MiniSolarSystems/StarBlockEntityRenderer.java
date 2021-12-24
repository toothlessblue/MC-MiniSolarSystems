package net.fabricmc.MiniSolarSystems;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class StarBlockEntityRenderer implements BlockEntityRenderer<StarBlockEntity> {
    private static ItemStack stack = new ItemStack(Items.DIRT, 1);

    @Override
    public void render(StarBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

    }
}