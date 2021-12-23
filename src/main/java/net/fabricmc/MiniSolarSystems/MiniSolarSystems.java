package net.fabricmc.MiniSolarSystems;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MiniSolarSystems implements ModInitializer {
	private static final ItemGroup itemGroup = FabricItemGroupBuilder.build(new Identifier("toothlessblue_minisolarsystems", "general"),
			() -> new ItemStack(MiniSolarSystems.itemBottledStar)
	);

	public static final BlockStar blockStar = new BlockStar(FabricBlockSettings
			.of(Material.WOOL)
			.strength(0.8f)
			.allowsSpawning((blockState, blockView, blockPos, entityType) -> false)
			.breakByHand(true)
			.dropsNothing()
	);

	public static final ItemBottledStar itemBottledStar = new ItemBottledStar(MiniSolarSystems.blockStar, new FabricItemSettings()
			.group(MiniSolarSystems.itemGroup)
	);

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("MiniSolarSystems");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		RegistryHelper.initialise("toothlessblue_minisolarsystems");
		RegistryHelper.register("bottled_star", itemBottledStar);
		RegistryHelper.register("star", blockStar);
	}
}
