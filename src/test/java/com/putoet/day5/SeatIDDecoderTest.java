package com.putoet.day5;

import com.putoet.grid.Point;
import com.putoet.utilities.Decoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeatIDDecoderTest {

    @Test
    void decode() {
        final Decoder<Point, Integer> decoder = new SeatIDDecoder();
        assertEquals(357, decoder.decode(Point.of(5, 44)));
    }
}