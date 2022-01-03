package net.fabricmc.MiniSolarSystems;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;

public class MiniSolarSystemsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(
                MiniSolarSystems.starBlockEntity,
                ctx -> new StarBlockEntityRenderer()
        );
    }
}
