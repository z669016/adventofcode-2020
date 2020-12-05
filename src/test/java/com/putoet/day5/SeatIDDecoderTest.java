package com.putoet.day5;

import org.junit.jupiter.api.Test;
import com.putoet.utilities.Decoder;
import com.putoet.utilities.Point;

import static org.junit.jupiter.api.Assertions.*;

class SeatIDDecoderTest {

    @Test
    void decode() {
        final Decoder<Point,Integer> decoder = new SeatIDDecoder();
        assertEquals(357, decoder.decode(Point.of(5, 44)));
    }
}