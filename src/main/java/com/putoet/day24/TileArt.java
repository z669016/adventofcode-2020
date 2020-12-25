package com.putoet.day24;

import com.putoet.grid.Point;

import java.util.*;
import java.util.stream.Collectors;

public class TileArt {
    private Map<Point, Tile> tiles;

    public TileArt(Collection<Tile> tiles) {
        assert tiles != null && tiles.size() > 0;

        this.tiles = tiles.stream().collect(Collectors.toMap(Tile::point, Tile::new));
        for (Tile tile : tiles) {
            if (tile.color() == Tile.Color.BLACK) {
                for (HexagonalDirection direction : HexagonalDirection.directions()) {
                    final Point point = tile.point().add(direction.move());
                    if (!this.tiles.containsKey(point)) {
                        this.tiles.put(point, new Tile(point));
                    }
                }
            }
        }
    }

    public TileArt next() {
        final TileArt nextTileArt = new TileArt(tiles.values());
        nextTileArt.evolve(tiles);

        return nextTileArt;
    }

    public int size() {
        return tiles.size();
    }

    private void evolve(Map<Point, Tile> tilesToEvolve) {
        for (Tile tile : tilesToEvolve.values()) {
            final int blackNeighbours = blackNeighbourCount(tile, tilesToEvolve);

            if (tile.color() == Tile.Color.BLACK) {
                if (blackNeighbours == 0 || blackNeighbours > 2)
                    this.tiles.get(tile.point()).flip();
            } else {
                if (blackNeighbours == 2)
                    this.tiles.get(tile.point()).flip();
            }
        }
    }

    private int blackNeighbourCount(Tile tile, Map<Point, Tile> tiles) {
        int blackNeighbourCount = 0;

        for (HexagonalDirection direction : HexagonalDirection.directions()) {
            final Point point = tile.point().add(direction.move());
            Tile neighbour = tiles.get(point);
            if (neighbour == null) {
                neighbour = new Tile(point);
                this.tiles.put(point, neighbour);
            }

            if (neighbour.color() == Tile.Color.BLACK)
                blackNeighbourCount++;
        }

        return blackNeighbourCount;
    }

    public long blackCount() {
        return tiles.values().stream().filter(tile -> tile.color() == Tile.Color.BLACK).count();
    }
}
