package net.fabricmc.MiniSolarSystems;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;

public class StarBlockEntity extends BlockEntity {
    private ArrayList<Planet> planets = new ArrayList<Planet>();

    public int tickNum = 0;

    public StarBlockEntity(BlockPos pos, BlockState state) {
        super(MiniSolarSystems.starBlockEntity, pos, state);

        this.planets.add(new Planet(this));
    }

    // Executed once per tick c:
    public static void tick(World world, BlockPos pos, BlockState state, StarBlockEntity be) {
        be.tickNum++;
        if (be.tickNum < 20) return;
        be.tickNum = 0;

        for (Planet planet : be.getPlanets()) {
            planet.tick();
        }
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

    public Collection<Planet> getPlanets() {
        return this.planets;
    }
}
