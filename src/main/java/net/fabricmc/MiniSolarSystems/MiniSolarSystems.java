package net.fabricmc.MiniSolarSystems;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MiniSolarSystems implements ModInitializer {
	public static BlockEntityType<StarBlockEntity> starBlockEntity;

	public static final ItemGroup itemGroup = FabricItemGroupBuilder.build(new Identifier("toothlessblue_minisolarsystems", "general"),
			() -> new ItemStack(MiniSolarSystems.bottledStarItem)
	);

	public static final StarBlock starBlock = new StarBlock(FabricBlockSettings
			.of(Material.WOOL)
			.strength(0.8f)
			.allowsSpawning((blockState, blockView, blockPos, entityType) -> false)
			.breakByHand(true)
			.dropsNothing()
			.sounds(BlockSoundGroup.WOOL)
			.nonOpaque()
			.solidBlock((blockState, blockView, blockPos) -> false)
			.luminance(10)
	);

	public static final BottledStarItem bottledStarItem = new BottledStarItem(MiniSolarSystems.starBlock, new FabricItemSettings()
			.group(MiniSolarSystems.itemGroup)
	);

	public static final Block debugArrowBlock = new Block(FabricBlockSettings
			.of(Material.WOOL)
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
		RegistryHelper.register("bottled_star", bottledStarItem);
		RegistryHelper.register("star", starBlock);
		RegistryHelper.register("debugarrow", debugArrowBlock);

		starBlockEntity = RegistryHelper.register("star", FabricBlockEntityTypeBuilder.create(StarBlockEntity::new, starBlock));

	}
}
