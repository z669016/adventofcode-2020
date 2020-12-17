package com.putoet.day17;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid4D {
    public static final char INACTIVE = '.';
    public static final char ACTIVE = '#';

    private final Map<Point4D, Character> grid = new HashMap<>();

    public Grid4D() {}

    public Grid4D(Map<Point4D,Character> grid) {
        assert grid != null;

        this.grid.putAll(grid);
    }

    public static Grid4D of(List<String> lines) {
        final Grid4D grid = new Grid4D();
        final int z = 0, w = 0;
        for (int y = 0; y < lines.size(); y++) {
            final String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                grid.set(Point4D.of(x, y, z, w), line.charAt(x));
            }
        }

        return grid;
    }

    public char get(Point4D point) {
        if (!grid.containsKey(point))
            throw new AssertionError(point.toString() + " is not on the grid");

        return grid.get(point);
    }

    public void set(Point4D point, char c) {
        grid.put(point, c);
    }

    public long count(char c) {
        return grid.values().stream().filter(value -> value == c).count();
    }

    public long size() {
        return grid.size();
    }

    public long countNeighbours(Point4D point, char c) {
        return point.adjacend().stream()
                .filter(grid::containsKey)
                .filter(p -> grid.get(p) == c)
                .count();
    }

    public Grid4D evolve() {
        final Grid4D newGrid = extend();

        for (Point4D point : newGrid.grid.keySet()) {
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

    private Grid4D extend() {
        final Grid4D newGrid = new Grid4D(grid);

        for (Point4D point : grid.keySet()) {
            final List<Point4D> neighbours = point.adjacend();
            neighbours.stream()
                    .filter(newPoint -> !grid.containsKey(newPoint))
                    .forEach(newPoint -> newGrid.set(newPoint, INACTIVE));
        }

        return newGrid;
    }

    public void print() {
        if (grid.size() == 0)
            return;

        final int maxW = grid.keySet().stream().mapToInt(point -> point.w).max().getAsInt();
        final int minW = grid.keySet().stream().mapToInt(point -> point.w).min().getAsInt();
        final int maxZ = grid.keySet().stream().mapToInt(point -> point.z).max().getAsInt();
        final int minZ = grid.keySet().stream().mapToInt(point -> point.z).min().getAsInt();
        final int maxY = grid.keySet().stream().mapToInt(point -> point.y).max().getAsInt();
        final int minY = grid.keySet().stream().mapToInt(point -> point.y).min().getAsInt();
        final int maxX = grid.keySet().stream().mapToInt(point -> point.x).max().getAsInt();
        final int minX = grid.keySet().stream().mapToInt(point -> point.x).min().getAsInt();

        for (int w = minW; w <= maxW; w++){
            for (int z = minZ; z <= maxZ; z++) {
                System.out.println("z=" + z);
                for (int y = minY; y <= maxY; y++) {
                    for (int x = minX; x <= maxX; x++) {
                        System.out.print(grid.get(Point4D.of(x, y, z, w)));
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
    }
}
