package net.fabricmc.MiniSolarSystems;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class RegistryHelper {
    private static String namespace = "";

    private RegistryHelper() { }

    public static void initialise(String namespace) {
        RegistryHelper.namespace = namespace;
    }

    public static <T extends Item> T register(String itemId, T item)  {
        Registry.register(Registry.ITEM, new Identifier(RegistryHelper.namespace, itemId), item);
        return item;
    }

    public static <T extends Block> T register(String blockId, T block) {
        Registry.register(Registry.BLOCK, new Identifier(RegistryHelper.namespace, blockId), block);
        return block;
    }

    public static <T extends BlockEntity> BlockEntityType<T> register(String blockEntityId, FabricBlockEntityTypeBuilder<T> blockEntityBuilder) {
        return Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                RegistryHelper.namespace + ":" + blockEntityId,
                blockEntityBuilder.build(null)
        );
    }
}
