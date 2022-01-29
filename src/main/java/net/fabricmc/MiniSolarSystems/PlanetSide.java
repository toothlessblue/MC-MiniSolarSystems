package net.fabricmc.MiniSolarSystems;

public class PlanetSide {
    private final int side;
    private final Planet planet;
    private final PlanetTile[][] tiles;
    private final PlanetTileRegistry tileRegistry;

    public PlanetSide(Planet planet, PlanetTileRegistry tileRegistry, int side) {
        this.planet = planet;
        this.tileRegistry = tileRegistry;
        this.side = side;
        this.tiles = new PlanetTile[16][16];
    }

    // Refer to documentation/planet-side-wrapping.png for help in understanding these magic values
    // I tried to figure it out mathematically and decided working out the map would be a better idea
    public PlanetTile getTile(int x, int y) {
        if (x >= 0 && x < 16 && y >= 0 && y < 16) return this.tiles[x][y];

        if (this.side == 0) {
            if (x < 0) {
                return this.planet.getSide(1).getTile(y, -x - 1);
            } else if (x > 15) {
                return this.planet.getSide(3).getTile(y, x - 16);
            } else if (y < 0) {
                return this.planet.getSide(2).getTile(15 - x, -y - 1);
            } else {
                return this.planet.getSide(4).getTile(x, y - 16);
            }

        } else if (this.side == 1) {
            if (x < 0) {
                return this.planet.getSide(2).getTile(16 + x, y);
            } else if (x > 15) {
                return this.planet.getSide(4).getTile(x - 16, y);
            } else if (y < 0) {
                return this.planet.getSide(0).getTile(-y - 1, x);
            } else {
                return this.planet.getSide(5).getTile(16 + y, x);
            }

        } else if (this.side == 2) {
            if (x < 0) {
                return this.planet.getSide(3).getTile(16 + x, y);
            } else if (x > 15) {
                return this.planet.getSide(1).getTile(x - 16, y);
            } else if (y < 0) {
                return this.planet.getSide(0).getTile(15 - x, -y - 1);
            } else {
                return this.planet.getSide(5).getTile(x, y - 16);
            }

        } else if (this.side == 3) {
            if (x < 0) {
                return this.planet.getSide(4).getTile(16 + x, y);
            } else if (x > 15) {
                return this.planet.getSide(2).getTile(x - 16, y);
            } else if (y < 0) {
                return this.planet.getSide(0).getTile(16 - y, 15 - x);
            } else {
                return this.planet.getSide(5).getTile(y - 16, 15 - x);
            }

        } else if (this.side == 4) {
            if (x < 0) {
                return this.planet.getSide(1).getTile(16 + x, y);
            } else if (x > 15) {
                return this.planet.getSide(3).getTile(x - 16, y);
            } else if (y < 0) {
                return this.planet.getSide(0).getTile(x, 16 + y);
            } else {
                return this.planet.getSide(5).getTile(15 - x, 31 - y);
            }

        } else if (this.side == 5) {
            if (x < 0) {
                return this.planet.getSide(3).getTile(15 - y, 16 + x);
            } else if (x > 15) {
                return this.planet.getSide(1).getTile(y, 31 - x);
            } else if (y < 0) {
                return this.planet.getSide(2).getTile(x, 16 - y);
            } else {
                return this.planet.getSide(4).getTile(15 - x, 31 - y);
            }

        } else {
            throw new IndexOutOfBoundsException("PlanetSide.side is not valid! " + this.side);

        }
    }

    public PlanetTile[] getNeighbours(int x, int y) {
        return new PlanetTile[] {
                this.getTile(x - 1, y),
                this.getTile(x + 1, y),
                this.getTile(x, y - 1),
                this.getTile(x, y + 1),
        };
    }

    public void generateNewTiles() {
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                PlanetTile tile = new PlanetTile(this.planet, this.tileRegistry, new TileLocation(this.side, x, y), this);
                this.tiles[x][y] = tile;
                this.tileRegistry.forceTileLocationUpdate(tile.location);
            }
        }
    }

    public void registerNewTiles() {
        MiniSolarSystems.LOGGER.info("Registering tiles");

        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                PlanetTile tile = this.getTile(x, y);

                if (tile.landHeight < this.planet.waterHeight) {
                    tile.setType(TileType.Ocean);
                }
            }
        }
    }
}
