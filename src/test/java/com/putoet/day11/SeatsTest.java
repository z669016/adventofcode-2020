package com.putoet.day11;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeatsTest {

    @Test
    void nextAdjacent() {
        var seats = new Seats(new Grid(GridUtils.of(ResourceLines.list("/day11.txt"))));
        final long initialSeats = GridUtils.count(seats.grid().grid(), Seats.EMPTY_SEAT);

        seats = seats.nextAdjacent();
        assertEquals(initialSeats, seats.occupied());

        seats = seats.nextAdjacent();
        assertEquals(20, seats.occupied());
    }

    @Test
    void nextInSight() {
        var seats = new Seats(new Grid(GridUtils.of(ResourceLines.list("/day11.txt"))));
        final var initialSeats = GridUtils.count(seats.grid().grid(), Seats.EMPTY_SEAT);

        seats = seats.nextInSight();
        assertEquals(initialSeats, seats.occupied());

        seats = seats.nextInSight();
        assertEquals(7, seats.occupied());
    }
}