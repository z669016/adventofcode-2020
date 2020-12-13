package com.putoet.day5;

import com.putoet.grid.Point;
import com.putoet.utilities.Decoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardingPassDecoderTest {

    @Test
    void decode() {
        final Decoder<String, Point> decoder = new BoardingPassDecoder();

        assertEquals(Point.of(5, 44), decoder.decode("FBFBBFFRLR"));
    }
}