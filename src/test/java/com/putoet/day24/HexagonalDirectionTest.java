package com.putoet.day24;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class HexagonalDirectionTest {

    @Test
    void iteratorOf() {
        final Iterator<HexagonalDirection> iter = HexagonalDirection.iteratorOf("wenwseneswn");

        assertTrue(iter.hasNext());
        assertEquals(HexagonalDirection.WEST, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(HexagonalDirection.EAST, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(HexagonalDirection.NORTH_WEST, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(HexagonalDirection.SOUTH_EAST, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(HexagonalDirection.NORTH_EAST, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(HexagonalDirection.SOUTH_WEST, iter.next());
        assertTrue(iter.hasNext());
        assertThrows(IllegalArgumentException.class, () -> iter.next());
        assertFalse(iter.hasNext());
    }
}