package com.putoet.day17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Point4D {
    public static final Point4D ORIGIN = Point4D.of(0, 0, 0, 0);

    public final int y, x, z, w;

    private Point4D(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public static Point4D of(int x, int y, int z, int w) {
        return new Point4D(x, y, z, w);
    }

    private final static List<Point4D> allDirections = new ArrayList<>();;
    static {
        for (int w = -1; w < 2; w++) {
            for (int z = -1; z < 2; z++) {
                for (int y = -1; y < 2; y++) {
                    for (int x = -1; x < 2; x++) {
                        allDirections.add(Point4D.of(x, y, z, w));
                    }
                }
            }
        }
        allDirections.remove(Point4D.ORIGIN);
    }

    public static List<Point4D> directions() {
        return Collections.unmodifiableList(allDirections);
    }

    public Point4D add(Point4D other) {
        assert other != null;

        return new Point4D(x + other.x, y + other.y, z + other.z, w + other.w);
    }

    public List<Point4D> adjacend() {
        return directions().stream().map(p -> p.add(this)).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point4D)) return false;
        final Point4D point = (Point4D) o;
        return w == point.w &&
                z == point.z &&
                y == point.y &&
                x == point.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x, z, w);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }
}
