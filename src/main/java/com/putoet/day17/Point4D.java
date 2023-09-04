package com.putoet.day17;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

record Point4D(int x, int y, int z, int w) {
    public static final Point4D ORIGIN = Point4D.of(0, 0, 0, 0);

    public static Point4D of(int x, int y, int z, int w) {
        return new Point4D(x, y, z, w);
    }

    private final static List<Point4D> allDirections = new ArrayList<>();
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

    public Point4D add(@NotNull Point4D other) {
        return new Point4D(x + other.x, y + other.y, z + other.z, w + other.w);
    }

    public List<Point4D> adjacent() {
        return directions().stream().map(p -> p.add(this)).toList();
    }
}
