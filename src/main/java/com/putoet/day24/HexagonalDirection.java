package com.putoet.day24;

import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

enum HexagonalDirection {
    EAST(Point.of(2, 0)),
    SOUTH_EAST(Point.of(1, -1)),
    SOUTH_WEST(Point.of(-1, -1)),
    WEST(Point.of(-2, 0)),
    NORTH_WEST(Point.of(-1, 1)),
    NORTH_EAST(Point.of(1, 1));

    private final Point move;

    HexagonalDirection(@NotNull Point move) {
        this.move = move;
    }

    public Point move() { return move; }

    public static Iterator<HexagonalDirection> iteratorOf(@NotNull String route) {
        return new Iterator<>() {
            private int offset = 0;

            @Override
            public boolean hasNext() {
                return offset < route.length();
            }

            @Override
            public HexagonalDirection next() {
                var direction = String.valueOf(route.charAt(offset++));
                if (direction.equals("s") || direction.equals("n")) {
                    if (offset == route.length())
                        throw new IllegalArgumentException("Invalid route '" + route + "'");

                    direction = direction + route.charAt(offset++);
                }
                return switch (direction) {
                    case "e" -> EAST;
                    case "se" -> SOUTH_EAST;
                    case "sw" -> SOUTH_WEST;
                    case "w" -> WEST;
                    case "nw" -> NORTH_WEST;
                    case "ne" -> NORTH_EAST;
                    default ->
                            throw new IllegalArgumentException("Invalid route '" + route + "'");
                };
            }
        };
    }

    public static List<HexagonalDirection> directions() {
        return List.of(EAST, SOUTH_EAST, SOUTH_WEST, WEST, NORTH_WEST, NORTH_EAST);
    }
}
