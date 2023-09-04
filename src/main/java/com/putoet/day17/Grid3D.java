package com.putoet.day17;

import com.putoet.grid.Point3D;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

record Grid3D(Map<Point3D,Character> grid) {
    public static final char INACTIVE = '.';
    public static final char ACTIVE = '#';

    public Grid3D() {
        this(new HashMap<>());
    }

    public Grid3D {
        grid = new HashMap<>(grid);
    }

    public static Grid3D of(@NotNull List<String> lines) {
        final var grid = new Grid3D();
        final var z = 0;
        for (var y = 0; y < lines.size(); y++) {
            final var line = lines.get(y);
            for (var x = 0; x < line.length(); x++) {
                grid.set(Point3D.of(x, y, z), line.charAt(x));
            }
        }

        return grid;
    }

    public char get(@NotNull Point3D point) {
        if (!grid.containsKey(point))
            throw new AssertionError(point + " is not on the grid");

        return grid.get(point);
    }

    public void set(@NotNull Point3D point, char c) {
        grid.put(point, c);
    }

    public long count(char c) {
        return grid.values().stream().filter(value -> value == c).count();
    }

    public long size() {
        return grid.size();
    }

    public long countNeighbours(@NotNull Point3D point, char c) {
        return point.adjacent().stream()
                .filter(grid::containsKey)
                .filter(p -> grid.get(p) == c)
                .count();
    }

    public Grid3D evolve() {
        final var newGrid = extend();

        for (var point : newGrid.grid.keySet()) {
            final long active = this.countNeighbours(point, ACTIVE);
            if (newGrid.get(point) == ACTIVE) {
                if (active < 2 || active > 3)
                    newGrid.set(point, INACTIVE);
            } else {
                if (active == 3)
                    newGrid.set(point, ACTIVE);
            }
        }

        return newGrid;
    }

    private Grid3D extend() {
        final var newGrid = new Grid3D(grid);

        for (var point : grid.keySet()) {
            final var neighbours = point.adjacent();
            neighbours.stream()
                    .filter(newPoint -> !grid.containsKey(newPoint))
                    .forEach(newPoint -> newGrid.set(newPoint, INACTIVE));
        }

        return newGrid;
    }

    public void print() {
        if (grid.isEmpty())
            return;

        final var maxZ = grid.keySet().stream().mapToInt(Point3D::z).max().getAsInt();
        final var minZ = grid.keySet().stream().mapToInt(Point3D::z).min().getAsInt();
        final var maxY = grid.keySet().stream().mapToInt(Point3D::y).max().getAsInt();
        final var minY = grid.keySet().stream().mapToInt(Point3D::y).min().getAsInt();
        final var maxX = grid.keySet().stream().mapToInt(Point3D::x).max().getAsInt();
        final var minX = grid.keySet().stream().mapToInt(Point3D::x).min().getAsInt();

        for (var z = minZ; z <= maxZ; z++) {
            System.out.println("z=" + z);
            for (var y = minY; y <= maxY; y++) {
                for (var x = minX; x <= maxX; x++) {
                    System.out.print(grid.get(Point3D.of(x, y, z)));
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
