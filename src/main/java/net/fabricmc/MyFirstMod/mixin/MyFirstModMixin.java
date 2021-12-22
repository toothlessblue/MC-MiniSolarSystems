package net.fabricmc.MyFirstMod.mixin;

import net.fabricmc.MyFirstMod.MyFirstMod;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MyFirstModMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		MyFirstMod.LOGGER.info("This line is printed by an example mod mixin!");
	}
}
