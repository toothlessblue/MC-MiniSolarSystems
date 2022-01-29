package net.fabricmc.MiniSolarSystems;

// Excavation lowers land level
// Organic tiles burn when temperature is too high
// Harvestable tiles yield resources, turns into plains
// Planet develops life when percentage of it is habitable for long length of time

import toothlessblue.RandomUtils;
import toothlessblue.TextureHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum TileType {
    Ocean(0),    // Any tile lower than the water level turns into ocean
    Plains(1),   // May turn into forest, spreadable
    Forest(2),   // Harvestable, spreadable
    Desolate(3), // May turn into plains, any ocean tile higher than the water level turns into desolate
    Desert(4),   // Excavation returns sand instead of dirt
    Ash(5),      // Very fertile, can grow wheat
    Wheat(6);    // Harvestable, burnable

    private final int value;
    private TileType(int value) {
        this.value = value;
    }

    public int toColour() {
        return switch (this.value) {
            case 0 -> TextureHelper.RGBAtoINT(56, 165, 255);
            case 1 -> TextureHelper.RGBAtoINT(155, 255, 56);
            case 2 -> TextureHelper.RGBAtoINT(24, 120, 7);
            case 3 -> TextureHelper.RGBAtoINT(84, 84, 84);
            case 4 -> TextureHelper.RGBAtoINT(184, 149, 11);
            case 5 -> TextureHelper.RGBAtoINT(179, 181, 174);
            case 6 -> TextureHelper.RGBAtoINT(255, 206, 92);
            default -> 0; // TODO decide on whether or not to raise error, probably should, since value should never be anything else
        };
    }
}

public class PlanetTile {
    private final Planet planet;
    private final PlanetSide onSide;
    private final PlanetTileRegistry registry;

    private TileType type = TileType.Desolate;

    public TileType getType() {
        return this.type;
    }

    public boolean compareType(TileType type) {
        return this.type == type;
    }

    public void setType(TileType type) {
        if (this.type == type) {
            MiniSolarSystems.LOGGER.warn("Setting tile type to the same type.");
            return;
        }

        this.registry.unregister(this);
        this.type = type;
        this.registry.register(this);
    }

    public int temperature; // in kelvin
    public int landHeight; // in meters

    public TileLocation location;

    public PlanetTile(Planet planet, PlanetTileRegistry registry, TileLocation location, PlanetSide onSide) {
        this.registry = registry;
        this.location = location;
        this.onSide = onSide;
        this.landHeight = RandomUtils.getRandomInt(0, 5);
        this.planet = planet;
    }

    public PlanetTile[] getNeighbours() {
        return this.onSide.getNeighbours(this.location.x, this.location.y);
    }

    public PlanetTile[] getNeighboursThatAre(TileType ofType) {
        PlanetTile[] neighbours = this.getNeighbours();
        ArrayList<PlanetTile> filtered = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            if (neighbours[i].compareType(ofType)) {
                filtered.add(neighbours[i]);
            }
        }

        return filtered.toArray(new PlanetTile[0]);
    }
}
