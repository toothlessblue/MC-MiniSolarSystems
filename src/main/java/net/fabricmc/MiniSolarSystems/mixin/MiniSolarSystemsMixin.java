package net.fabricmc.MiniSolarSystems.mixin;

import net.fabricmc.MiniSolarSystems.MiniSolarSystems;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MiniSolarSystemsMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		MiniSolarSystems.LOGGER.info("Loading!");
	}
}
