package com.putoet.day24;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.*;

class TileVisitor {
    private final Map<Point, Tile> tiles = new HashMap<>();

    public TileVisitor() {
        tiles.put(Point.ORIGIN, new Tile(Point.ORIGIN));
    }

    public void visit(@NotNull List<String> routes) {
        routes.forEach(this::visit);
    }

    public void visit(@NotNull String route) {
        final var iter = HexagonalDirection.iteratorOf(route);
        var point = Point.ORIGIN;

        Tile tile = null;
        while (iter.hasNext()) {
            final var direction = iter.next();
            point = point.add(direction.move());

            tile = tiles.get(point);
            if (tile == null) {
                tile = new Tile(point);
                tiles.put(point, tile);
            }
        }

        Objects.requireNonNull(tile).flip();
    }

    public int size() {
        return tiles.size();
    }

    public Collection<Tile> tiles() {
        return tiles.values();
    }

    public long blackCount() {
        return tiles.values().stream().filter(tile -> tile.color() == Tile.Color.BLACK).count();
    }

    public long flipCount(int flipped) {
        return tiles.values().stream().filter(tile -> tile.flipped() == flipped).count();
    }

    @Override
    public String toString() {
        return tiles.values().toString();
    }
}
