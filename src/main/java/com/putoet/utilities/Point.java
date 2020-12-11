package com.putoet.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Point {
    public static final Point ORIGIN = Point.of(0, 0);

    public final int y, x;

    public static Point of(int x, int y) {
        return new Point(x, y);
    }

    private static List<Point> strictDirections;
    private static List<Point> allDirections;
    public static List<Point> directions(boolean strict) {
        if (strict) {
            if (strictDirections == null)
                strictDirections = List.of(
                        Point.of(1, 0),
                        Point.of(0,1),
                        Point.of(-1,0),
                        Point.of(0, -1)
                );
            return strictDirections;
        }

        if (allDirections == null) {
            allDirections = new ArrayList<>(directions(true));
            allDirections.add(Point.of(1, 1));
            allDirections.add(Point.of(-1, 1));
            allDirections.add(Point.of(1, -1));
            allDirections.add(Point.of(-1, -1));
            allDirections = Collections.unmodifiableList(allDirections);
        }
        return allDirections;
    }

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point add(Point other) {
        assert other != null;

        return new Point(x + other.x, y + other.y);
    }

    public List<Point> adjacend() {
        return List.of(
                Point.of(x + 1, y),
                Point.of(x + 1, y + 1),
                Point.of(x, y + 1),
                Point.of(x - 1, y + 1),
                Point.of(x - 1, y),
                Point.of(x - 1, y - 1),
                Point.of(x, y - 1),
                Point.of(x + 1, y - 1)
        );
    }

    public int manhattanDistance(Point other) {
        return Math.abs(x - other.x) + (Math.abs(y - other.y));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return y == point.y &&
                x == point.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
