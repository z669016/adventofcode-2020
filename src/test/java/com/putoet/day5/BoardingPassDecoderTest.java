package com.putoet.day5;

import com.putoet.grid.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardingPassDecoderTest {

    @Test
    void decode() {
        final var decoder = new BoardingPassDecoder();

        assertEquals(Point.of(5, 44), decoder.decode("FBFBBFFRLR"));
    }
}