package net.fabricmc.MiniSolarSystems;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

public class MiniSolarSystemsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm -> new ModelProvider());

        BlockEntityRendererRegistry.register(
                MiniSolarSystems.starBlockEntity,
                new BlockEntityRendererFactory<StarBlockEntity>() {
                    @Override
                    public BlockEntityRenderer<StarBlockEntity> create(Context ctx) {
                        return new StarBlockEntityRenderer();
                    }
                }
        );
    }
}
