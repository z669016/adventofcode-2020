package com.putoet.day5;

import com.putoet.grid.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeatIDDecoderTest {

    @Test
    void decode() {
        final var decoder = new SeatIDDecoder();
        assertEquals(357, decoder.decode(Point.of(5, 44)));
    }
}