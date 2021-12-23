package net.fabricmc.MiniSolarSystems;

import net.minecraft.block.Block;
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
}
