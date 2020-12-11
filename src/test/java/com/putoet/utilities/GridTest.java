package com.putoet.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void contains() {
        final Grid grid = new Grid(-7, 9, -13, 17,
                GridUtils.of(-7, 9, -13, 17, '.'));

        assertTrue(grid.contains(-7, -13));
        assertFalse(grid.contains(-7, -14));
        assertFalse(grid.contains(-8, -14));
        assertFalse(grid.contains(-8, -13));

        assertTrue(grid.contains(8, -13));
        assertFalse(grid.contains(8, -14));
        assertFalse(grid.contains(9, -14));
        assertFalse(grid.contains(9, -13));

        assertTrue(grid.contains(8, 16));
        assertFalse(grid.contains(9, 16));
        assertFalse(grid.contains(9, 17));
        assertFalse(grid.contains(8, 17));

        assertTrue(grid.contains(-7, 16));
        assertFalse(grid.contains(-7, 17));
        assertFalse(grid.contains(-8, 17));
        assertFalse(grid.contains(-8, 16));
//
//        final char used = '#';
//        grid.set(-7, -13, used);
//        grid.set(8, -13, used);
//        grid.set(8, 16, used);
//        grid.set(-7, 16, used);
//        System.out.println(grid);
    }
}