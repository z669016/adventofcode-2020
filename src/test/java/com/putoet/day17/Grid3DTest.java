package com.putoet.day17;

import com.putoet.grid.Point3D;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Grid3DTest {
    private Grid3D grid;

    @BeforeEach
    void setup() {
        grid = Grid3D.of(ResourceLines.list("/day17.txt"));
    }

    @Test
    void of() {
        assertEquals(9, grid.size());
        assertEquals(5, grid.count(Grid3D.ACTIVE));
        assertEquals(4, grid.count(Grid3D.INACTIVE));
    }

    @Test
    void neighbours() {
        assertEquals(1, grid.countNeighbours(Point3D.ORIGIN, Grid3D.ACTIVE));
        assertEquals(5, grid.countNeighbours(Point3D.of(1, 1, 0), Grid3D.ACTIVE));
    }

    @Test
    void evolve() {
        var newGrid = grid.evolve();
        assertEquals(3L * (5 * 5), newGrid.size());
        assertEquals(11, newGrid.count(Grid3D.ACTIVE));

        newGrid = newGrid.evolve();
        assertEquals(5L * (7 * 7), newGrid.size());
        assertEquals(21, newGrid.count(Grid3D.ACTIVE));

        newGrid = newGrid.evolve();
        assertEquals(38, newGrid.count(Grid3D.ACTIVE));

        newGrid = newGrid.evolve();
        newGrid = newGrid.evolve();
        newGrid = newGrid.evolve();
        assertEquals(112, newGrid.count(Grid3D.ACTIVE));
    }
}