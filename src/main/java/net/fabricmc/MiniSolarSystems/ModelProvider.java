package net.fabricmc.MiniSolarSystems;

import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelProviderException;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class ModelProvider implements ModelResourceProvider {
    public static final StarBlockModel blockStarModel = new StarBlockModel();
    public static final Identifier blockStarModelId = new Identifier("toothlessblue_minisolarsystems:block/star");

    @Override
    public @Nullable UnbakedModel loadModelResource(Identifier resourceId, ModelProviderContext context) throws ModelProviderException {
        if(resourceId.equals(blockStarModelId)) {
            return blockStarModel;
        }

        return null;
    }
}
