package com.putoet.day17;

import com.putoet.grid.Point3D;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid3D {
    public static final char INACTIVE = '.';
    public static final char ACTIVE = '#';

    private final Map<Point3D, Character> grid = new HashMap<>();

    public Grid3D() {}

    public Grid3D(Map<Point3D,Character> grid) {
        assert grid != null;

        this.grid.putAll(grid);
    }

    public static Grid3D of(List<String> lines) {
        final Grid3D grid = new Grid3D();
        final int z = 0;
        for (int y = 0; y < lines.size(); y++) {
            final String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                grid.set(Point3D.of(x, y, z), line.charAt(x));
            }
        }

        return grid;
    }

    public char get(Point3D point) {
        if (!grid.containsKey(point))
            throw new AssertionError(point.toString() + " is not on the grid");

        return grid.get(point);
    }

    public void set(Point3D point, char c) {
        grid.put(point, c);
    }

    public long count(char c) {
        return grid.values().stream().filter(value -> value == c).count();
    }

    public long size() {
        return grid.size();
    }

    public long countNeighbours(Point3D point, char c) {
        return point.adjacent().stream()
                .filter(grid::containsKey)
                .filter(p -> grid.get(p) == c)
                .count();
    }

    public Grid3D evolve() {
        final Grid3D newGrid = extend();

        for (Point3D point : newGrid.grid.keySet()) {
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
        final Grid3D newGrid = new Grid3D(grid);

        for (Point3D point : grid.keySet()) {
            final List<Point3D> neighbours = point.adjacent();
            neighbours.stream()
                    .filter(newPoint -> !grid.containsKey(newPoint))
                    .forEach(newPoint -> newGrid.set(newPoint, INACTIVE));
        }

        return newGrid;
    }

    public void print() {
        if (grid.size() == 0)
            return;

        final int maxZ = grid.keySet().stream().mapToInt(Point3D::z).max().getAsInt();
        final int minZ = grid.keySet().stream().mapToInt(Point3D::z).min().getAsInt();
        final int maxY = grid.keySet().stream().mapToInt(Point3D::y).max().getAsInt();
        final int minY = grid.keySet().stream().mapToInt(Point3D::y).min().getAsInt();
        final int maxX = grid.keySet().stream().mapToInt(Point3D::x).max().getAsInt();
        final int minX = grid.keySet().stream().mapToInt(Point3D::x).min().getAsInt();

        for (int z = minZ; z <= maxZ; z++) {
            System.out.println("z=" + z);
            for (int y = minY; y <= maxY; y++) {
                for (int x = minX; x <= maxX; x++) {
                    System.out.print(grid.get(Point3D.of(x, y, z)));
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
