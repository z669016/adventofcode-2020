package com.putoet.day17;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Grid4DTest {
    private Grid4D grid;

    @BeforeEach
    void setup() {
        grid = Grid4D.of(ResourceLines.list("/day17.txt"));
    }

    @Test
    void of() {
        assertEquals(9, grid.size());
        assertEquals(5, grid.count(Grid4D.ACTIVE));
        assertEquals(4, grid.count(Grid4D.INACTIVE));

        assertEquals(80, Point4D.directions().size());
    }

    @Test
    void evolve() {
        var newGrid = grid.evolve();
        assertEquals(29, newGrid.count(Grid4D.ACTIVE));

        newGrid = newGrid.evolve();
        assertEquals(60, newGrid.count(Grid4D.ACTIVE));

        newGrid = newGrid.evolve();
        newGrid = newGrid.evolve();
        newGrid = newGrid.evolve();
        newGrid = newGrid.evolve();
        assertEquals(848, newGrid.count(Grid4D.ACTIVE));
    }
}