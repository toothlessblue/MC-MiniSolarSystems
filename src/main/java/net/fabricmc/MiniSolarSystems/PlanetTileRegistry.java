package net.fabricmc.MiniSolarSystems;

import toothlessblue.RandomUtils;

import java.util.*;

//
//   Structure
//
//   PlanetTileRegistry {
//     [key: TileType]: {
//       [key: LocationID]: Tile
//     }
//   }
//
//   LocationID is int

public class PlanetTileRegistry {
    private final HashMap<TileType, HashMap<Integer, PlanetTile>> tileTypeLocationMap = new HashMap<>();
    private final HashMap<Integer, TileType> locationToType = new HashMap<>();
    private final HashMap<Integer, TileLocation> oceanAdjacent = new HashMap<>();
    private final HashMap<TileType, Integer> tileTypeCounters = new HashMap<>();

    private final HashSet<TileLocation> inChanges = new HashSet<>();
    private final ArrayList<TileLocation> changedLocations = new ArrayList<>();

    private final Planet planet;

    public PlanetTileRegistry(Planet planet) {
        this.planet = planet;

        for (TileType type : TileType.values()) {
            this.tileTypeLocationMap.put(type, new HashMap<>());
            this.tileTypeCounters.put(type, 0);
        }
    }

    public void forceTileLocationUpdate(TileLocation location) {
        if (this.inChanges.contains(location)) return;

        this.inChanges.add(location);
        this.changedLocations.add(location);
    }

    private void addNewOceanTile(PlanetTile tile) {
        PlanetTile[] neighbours = tile.getNeighbours();
        for (PlanetTile neighbour : neighbours) {
            if (!neighbour.compareType(TileType.Ocean)) {
                this.oceanAdjacent.put(neighbour.location.getId(), neighbour.location);
            }
        }

        this.oceanAdjacent.remove(tile.location.getId());
    }

    private void removeOldOceanTile(PlanetTile tile) {
        PlanetTile[] neighbours = tile.getNeighbours();
        for (PlanetTile neighbour : neighbours) { // For each neighbour next to the removed ocean tile
            PlanetTile[] neighbourNeighbours = neighbour.getNeighbours();
            boolean oceanAdjacent = false;

            for (PlanetTile neighbourNeighbour : neighbourNeighbours) { // Check each adjacent tile to see if neighbour is still ocean adjacent
                if (neighbourNeighbour != tile && neighbourNeighbour.compareType(TileType.Ocean)) { // If it is, break and keep entry
                    oceanAdjacent = true;
                    break;
                }
            }

            if (!oceanAdjacent) { // If not, remove entry
                this.oceanAdjacent.remove(neighbour.location.getId());
            }
        }
    }

    public void register(PlanetTile tile) {
        if (tile.compareType(TileType.Ocean)) this.addNewOceanTile(tile);

        this.tileTypeLocationMap.get(tile.getType()).put(tile.location.getId(), tile);
        this.locationToType.put(tile.location.getId(), tile.getType());
        this.tileTypeCounters.put(tile.getType(), this.tileTypeCounters.get(tile.getType()) + 1);

        if (!this.inChanges.contains(tile.location)) {
            this.inChanges.add(tile.location);
            this.changedLocations.add(tile.location);
        }
    }

    public void unregister(PlanetTile tile) {
        if (tile.compareType(TileType.Ocean)) this.removeOldOceanTile(tile);

        if (!this.locationToType.containsKey(tile.location.getId())) return;

        this.tileTypeLocationMap.get(tile.getType()).remove(tile.location.getId());
        this.locationToType.remove(tile.location.getId());
        this.tileTypeCounters.put(tile.getType(), this.tileTypeCounters.get(tile.getType()) - 1);
    }

    public PlanetTile getTileByLocation(TileLocation location) {
        return this.planet.getSide(location.side).getTile(location.x, location.y);
    }

    public PlanetTile getRandomTileOfType(TileType type) {
        Collection<PlanetTile> tiles = this.tileTypeLocationMap.get(type).values();
        int total = tiles.size();

        return (PlanetTile) RandomUtils.getRandomOfArray(tiles.toArray());
    }

    public int getTileCount(TileType type) {
        return this.tileTypeCounters.get(type);
    }

    public Collection<TileLocation> getOceanAdjacent() {
        return this.oceanAdjacent.values();
    }

    /**
     * @return every tile which type changed since the last time this function was called.
     */
    public ArrayList<TileLocation> getChanges() { // TODO, possible optimisation, add clear changes function instead of cloning and clearing, see how performs
        ArrayList<TileLocation> clone = new ArrayList<>(this.changedLocations);

        this.changedLocations.clear();
        this.inChanges.clear();

        return clone;
    }

    // TODO this function is a patch, ideally oceanAdjacent should always be accurately up to date when a tile changes to or from ocean
    //      but with the current algo this is not the case
    public void fixOceanAdjacentTile(PlanetTile tile) {
        this.oceanAdjacent.remove(tile.location.getId());
    }
}














