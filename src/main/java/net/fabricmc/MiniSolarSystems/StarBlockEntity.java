package net.fabricmc.MiniSolarSystems;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StarBlockEntity extends BlockEntity {
    public StarBlockEntity(BlockPos pos, BlockState state) {
        super(MiniSolarSystems.starBlockEntity, pos, state);
    }

    // Executed once per tick c:
    public static void tick(World world, BlockPos pos, BlockState state, StarBlockEntity be) {

    }

    // Serialize the BlockEntity
    @Override
    public void writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
    }

    // Deserialize the BlockEntity
    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
    }
}
