package net.fabricmc.MiniSolarSystems;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import toothlessblue.RandomUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Planet {
    private static final int MAX_EROSION = 3;

    private StarBlockEntity star;
    private PlanetSide[] sides = new PlanetSide[6];
    private PlanetTileRegistry tiles;

    public int waterHeight = 2;

    public PlanetRenderer renderer;

    public Planet(StarBlockEntity star) {
        this.tiles = new PlanetTileRegistry(this);

        this.star = star;

        for (int i = 0; i < 6; i++) {
            sides[i] = new PlanetSide(this, this.tiles, i);
            sides[i].generateNewTiles();
        }

        for (int i = 0; i < 6; i++) {
            sides[i].registerNewTiles();
        }
    }

    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int overlay, int light) {
        if (this.renderer == null) {
            if (MinecraftClient.getInstance().world != null && MinecraftClient.getInstance().world.isClient) {
                this.renderer = new PlanetRenderer();
            } else {
                return;
            }
        }

        this.renderer.updateTexture(tiles);
        this.renderer.render(matrices, vertexConsumers, overlay, light);
    }

    public PlanetSide getSide(int i) {
        if (i < 0 || i > 5) throw new IndexOutOfBoundsException("The provided index must be between 0 and 5 for Planet.getSide, got " + i);

        return this.sides[i];
    }

    public void tick() {
        this.doErosionDeposition();
        this.generatePlains();
        this.spreadPlains();
    }

    private void doErosionDeposition() {
        int totalRemoved = 0;

        ArrayList<PlanetTile> tilesToSetToOcean = new ArrayList<>();

        for (TileLocation location : this.tiles.getOceanAdjacent()) {
            int toRemove = RandomUtils.getRandomInt(0, Planet.MAX_EROSION);

            totalRemoved += toRemove;

            PlanetTile tile = this.tiles.getTileByLocation(location);

            tile.landHeight -= toRemove;

            if (tile.landHeight < 0) {
                totalRemoved += tile.landHeight;
                tile.landHeight = 0;
            }

            if (tile.landHeight < this.waterHeight) {
                tilesToSetToOcean.add(tile);
            }
        }

        // Avoids concurrent modification exception
        for (PlanetTile tile : tilesToSetToOcean) {
            tile.setType(TileType.Ocean);
        }

        while (totalRemoved > 0) {
            PlanetTile oceanAdjacentTile = this.tiles.getTileByLocation((TileLocation) RandomUtils.getRandomOfArray(this.tiles.getOceanAdjacent().toArray())); // TODO possible optimisation

            int toAdd = RandomUtils.getRandomInt(1, Planet.MAX_EROSION);

            if (oceanAdjacentTile.landHeight > this.waterHeight + 3) {
                PlanetTile[] oceanNeighbours = oceanAdjacentTile.getNeighboursThatAre(TileType.Ocean);
                if (oceanNeighbours.length == 0) {
                    this.tiles.fixOceanAdjacentTile(oceanAdjacentTile);
                    continue;
                }

                PlanetTile oceanNeighbor = RandomUtils.getRandomOfArray(oceanNeighbours);
                oceanNeighbor.landHeight += toAdd;

                if (oceanNeighbor.landHeight > this.waterHeight) {
                    oceanNeighbor.setType(TileType.Desolate);
                }
            } else {
                oceanAdjacentTile.landHeight += toAdd;
            }

            totalRemoved -= toAdd;
        }
    }

    private void generatePlains() {
        int desolateCount = tiles.getTileCount(TileType.Desolate);
        if (this.waterHeight == 0 || desolateCount == 0) return;

        // TODO factor in tile age
        float chanceForGeneration = (desolateCount / 3072f) * (this.waterHeight == 2 ? 1f : 0.75f); // min 0%, max 50%

        if (RandomUtils.rollChance(chanceForGeneration)) {
            this.tiles.getRandomTileOfType(TileType.Desolate).setType(TileType.Plains);
        }
    }

    private void spreadPlains() {
        int plainsCount = tiles.getTileCount(TileType.Plains);
        if (this.waterHeight == 0 || plainsCount == 0) return;

        float spreadChance = (plainsCount / 3072f) * (this.waterHeight == 2 ? 1.25f : 1f); // min 75%, max 50%

        if (RandomUtils.rollChance(spreadChance)) {
            // TODO get random tile with neighbour
        }
    }

    private void generateForest() {
        int plainsCount = tiles.getTileCount(TileType.Plains);
        if (plainsCount == 0) return;

        // TODO factor in tile age
        float chanceForGeneration = (plainsCount / 3072f) * (this.waterHeight == 2 ? 1f : 0.75f); // min 0%, max 50%

        if (RandomUtils.rollChance(chanceForGeneration)) {
            this.tiles.getRandomTileOfType(TileType.Plains).setType(TileType.Forest);
        }
    }
}
