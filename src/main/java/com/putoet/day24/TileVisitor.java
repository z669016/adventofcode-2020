package com.putoet.day24;

import com.putoet.grid.Point;

import java.util.*;

public class TileVisitor {
    private final Map<Point, Tile> tiles = new HashMap<>();

    public TileVisitor() {
        tiles.put(Point.ORIGIN, new Tile(Point.ORIGIN));
    }

    public void visit(List<String> routes) {
        routes.forEach(this::visit);
    }

    public void visit(String route) {
        final Iterator<HexagonalDirection> iter = HexagonalDirection.iteratorOf(route);
        Point point = Point.ORIGIN;

        Tile tile = null;
        while (iter.hasNext()) {
            final HexagonalDirection direction = iter.next();
            point = point.add(direction.move());

            tile = tiles.get(point);
            if (tile == null) {
                tile = new Tile(point);
                tiles.put(point, tile);
            }
        }

        tile.flip();
    }

    public int size() {
        return tiles.size();
    }

    public Collection<Tile> tiles() {
        return tiles.values();
    }

    public long whiteCount() {
        return tiles.values().stream().filter(tile -> tile.color() == Tile.Color.WHITE).count();
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
