package com.putoet.day11;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;

import java.util.function.BiFunction;

record Seats(Grid grid) {
    public static final char EMPTY_SEAT = 'L';
    public static final char TAKEN_SEAT = '#';

    public Seats nextAdjacent() {
        return next(this, new Seats(grid.copy()), Seats::occupiedAround, 4);
    }

    public Seats nextInSight() {
        return next(this, new Seats(grid.copy()), Seats::occupiedInSight, 5);
    }

    public Grid grid() {
        return grid;
    }

    public long occupied() {
        return GridUtils.count(grid.grid(), TAKEN_SEAT);
    }

    private static Seats next(Seats seats, Seats next, BiFunction<Seats, Point,Long> strategy, int maxOccupied) {
        for (var y = seats.grid.minY(); y < seats.grid.maxY(); y++) {
            for (var x = seats.grid.minX(); x < seats.grid.maxX(); x++) {
                final var occupied = strategy.apply(seats, Point.of(x, y));
                if (seats.grid.get(x, y) == EMPTY_SEAT && occupied == 0)
                    next.grid.set(x, y, TAKEN_SEAT);
                else if (seats.grid.get(x, y) == TAKEN_SEAT && occupied >= maxOccupied)
                    next.grid.set(x, y, EMPTY_SEAT);
            }
        }

        return next;
    }

    private static long occupiedAround(Seats seats, Point point) {
        return Point.directions(false).stream()
                .map(point::add)
                .filter(p -> seats.grid.contains(p.x(), p.y()))
                .filter(p -> seats.grid.get(p.x(), p.y()) == TAKEN_SEAT)
                .count();
    }

    private static long occupiedInSight(Seats seats, Point point) {
        final var directions = Point.directions(false);
        return directions.stream()
                .mapToLong(direction -> occupiedInSight(seats, point, direction))
                .sum();
    }

    private static long occupiedInSight(Seats seats, Point point, Point direction) {
        point = point.add(direction);
        while (seats.grid.contains(point.x(), point.y())) {
            if (seats.grid.get(point.x(), point.y()) == TAKEN_SEAT)
                return 1;
            if (seats.grid.get(point.x(), point.y()) == EMPTY_SEAT)
                return 0;
            point = point.add(direction);
        }

        return 0;
    }
}
