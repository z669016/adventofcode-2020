package com.putoet.day17;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

record Grid4D(Map<Point4D,Character> grid) {
    public static final char INACTIVE = '.';
    public static final char ACTIVE = '#';

    public Grid4D() {
        this(new HashMap<>());
    }

    public Grid4D{
        grid = new HashMap<>(grid);
    }

    public static Grid4D of(@NotNull List<String> lines) {
        final var grid = new Grid4D();
        final int z = 0, w = 0;
        for (var y = 0; y < lines.size(); y++) {
            final var line = lines.get(y);
            for (var x = 0; x < line.length(); x++) {
                grid.set(Point4D.of(x, y, z, w), line.charAt(x));
            }
        }

        return grid;
    }

    public char get(@NotNull Point4D point) {
        if (!grid.containsKey(point))
            throw new AssertionError(point + " is not on the grid");

        return grid.get(point);
    }

    public void set(@NotNull Point4D point, char c) {
        grid.put(point, c);
    }

    public long count(char c) {
        return grid.values().stream().filter(value -> value == c).count();
    }

    public long size() {
        return grid.size();
    }

    public long countNeighbours(@NotNull Point4D point, char c) {
        return point.adjacent().stream()
                .filter(grid::containsKey)
                .filter(p -> grid.get(p) == c)
                .count();
    }

    public Grid4D evolve() {
        final Grid4D newGrid = extend();

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

    private Grid4D extend() {
        final Grid4D newGrid = new Grid4D(grid);

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

        final var maxW = grid.keySet().stream().mapToInt(Point4D::w).max().getAsInt();
        final var minW = grid.keySet().stream().mapToInt(Point4D::w).min().getAsInt();
        final var maxZ = grid.keySet().stream().mapToInt(Point4D::z).max().getAsInt();
        final var minZ = grid.keySet().stream().mapToInt(Point4D::z).min().getAsInt();
        final var maxY = grid.keySet().stream().mapToInt(Point4D::y).max().getAsInt();
        final var minY = grid.keySet().stream().mapToInt(Point4D::y).min().getAsInt();
        final var maxX = grid.keySet().stream().mapToInt(Point4D::x).max().getAsInt();
        final var minX = grid.keySet().stream().mapToInt(Point4D::x).min().getAsInt();

        for (var w = minW; w <= maxW; w++){
            for (var z = minZ; z <= maxZ; z++) {
                System.out.println("z=" + z);
                for (var y = minY; y <= maxY; y++) {
                    for (var x = minX; x <= maxX; x++) {
                        System.out.print(grid.get(Point4D.of(x, y, z, w)));
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
    }
}
