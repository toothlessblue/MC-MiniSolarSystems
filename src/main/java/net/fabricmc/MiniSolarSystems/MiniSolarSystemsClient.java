package net.fabricmc.MiniSolarSystems;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.MinecraftClient;

public class MiniSolarSystemsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(
                MiniSolarSystems.starBlockEntity,
                ctx -> new StarBlockEntityRenderer()
        );
    }
}
