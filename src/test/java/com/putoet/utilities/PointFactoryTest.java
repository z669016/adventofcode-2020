package com.putoet.utilities;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PointFactoryTest {

    @Test
    void ofString() {
        assertThrows(AssertionError.class, () -> PointFactory.of(""));

        assertEquals(Point.of(3,5), PointFactory.of("3, 5"));
    }

    @Test
    void ofList() {
        assertEquals(List.of(), PointFactory.of(List.of()));
        assertEquals(List.of(Point.of(1, 3), Point.of(5, 7)), PointFactory.of(List.of("1, 3", "5, 7")));
    }
}