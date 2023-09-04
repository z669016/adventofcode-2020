package com.putoet.day24;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

class TileArt {
    private final Map<Point, Tile> tiles;

    public TileArt(@NotNull Collection<Tile> tiles) {
        assert !tiles.isEmpty();

        this.tiles = tiles.stream().collect(Collectors.toMap(Tile::point, Tile::new));
        for (var tile : tiles) {
            if (tile.color() == Tile.Color.BLACK) {
                for (var direction : HexagonalDirection.directions()) {
                    final var point = tile.point().add(direction.move());
                    if (!this.tiles.containsKey(point)) {
                        this.tiles.put(point, new Tile(point));
                    }
                }
            }
        }
    }

    public TileArt next() {
        final var nextTileArt = new TileArt(tiles.values());
        nextTileArt.evolve(tiles);

        return nextTileArt;
    }

    public int size() {
        return tiles.size();
    }

    private void evolve(Map<Point, Tile> tilesToEvolve) {
        for (var tile : tilesToEvolve.values()) {
            final var blackNeighbours = blackNeighbourCount(tile, tilesToEvolve);

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
        var blackNeighbourCount = 0;

        for (var direction : HexagonalDirection.directions()) {
            final var point = tile.point().add(direction.move());
            var neighbour = tiles.get(point);
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
