package com.putoet.day5;

import org.junit.jupiter.api.Test;
import com.putoet.utilities.Decoder;
import com.putoet.utilities.Point;

import static org.junit.jupiter.api.Assertions.*;

class BoardingPassDecoderTest {

    @Test
    void decode() {
        final Decoder<String, Point> decoder = new BoardingPassDecoder();

        assertEquals(Point.of(5, 44), decoder.decode("FBFBBFFRLR"));
    }
}