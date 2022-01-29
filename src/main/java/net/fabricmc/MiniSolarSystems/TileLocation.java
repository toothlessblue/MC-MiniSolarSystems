package net.fabricmc.MiniSolarSystems;

public class TileLocation {
    public int side; // Max 6
    public int x;    // Max 16
    public int y;    // Max 16

    public TileLocation(int side, int x, int y) {
        this.side = side;
        this.x = x;
        this.y = y;
    }

    /**
     * @return An int made out of the bitshifted components of this location
     */
    public int getId() {
        return (this.side) | (this.x << 8) | (this.y << 16);
    }
}
