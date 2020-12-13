package com.putoet.day11;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeatsTest {

    @Test
    void nextAdjacent() {
        Seats seats = new Seats(new Grid(GridUtils.of(ResourceLines.list("/day11.txt"))));
        final long initialSeats = GridUtils.count(seats.grid().grid(), Seats.EMPTY_SEAT);

        seats = seats.nextAdjacent();
        long taken = seats.occupied();
        assertEquals(initialSeats, taken);

        seats = seats.nextAdjacent();
        taken = seats.occupied();
        assertEquals(20, taken);
    }

    @Test
    void nextInSight() {
        Seats seats = new Seats(new Grid(GridUtils.of(ResourceLines.list("/day11.txt"))));
        final long initialSeats = GridUtils.count(seats.grid().grid(), Seats.EMPTY_SEAT);

        seats = seats.nextInSight();
        long taken = seats.occupied();
        assertEquals(initialSeats, taken);

        seats = seats.nextInSight();
        taken = seats.occupied();
        System.out.println(seats.grid());
        assertEquals(7, taken);
    }
}