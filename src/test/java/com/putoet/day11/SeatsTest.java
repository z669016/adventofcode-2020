package com.putoet.day11;

import com.putoet.resources.ResourceLines;
import com.putoet.utilities.Grid;
import com.putoet.utilities.GridUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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