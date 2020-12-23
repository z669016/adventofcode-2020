package com.putoet.day23;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CupCircleTest {
    private CupCircle cupCircle;

    @BeforeEach
    void setup() {
        cupCircle = new CupCircle("389125467");
    }


    @Test
    void nextCircle() {
        assertEquals("3 8 9 1 2 5 4 6 7", cupCircle.toString());
        cupCircle.nextCircle();
        assertEquals("2 8 9 1 5 4 6 7 3", cupCircle.toString());
        cupCircle.nextCircle();
        assertEquals("5 4 6 7 8 9 1 3 2", cupCircle.toString());
        cupCircle.nextCircle();
        assertEquals("8 9 1 3 4 6 7 2 5", cupCircle.toString());
        cupCircle.nextCircle();
        assertEquals("4 6 7 9 1 3 2 5 8", cupCircle.toString());
        cupCircle.nextCircle();
        assertEquals("1 3 6 7 9 2 5 8 4", cupCircle.toString());
        cupCircle.nextCircle();
        assertEquals("9 3 6 7 2 5 8 4 1", cupCircle.toString());
        cupCircle.nextCircle();
        assertEquals("2 5 8 3 6 7 4 1 9", cupCircle.toString());
        cupCircle.nextCircle();
        assertEquals("6 7 4 1 5 8 3 9 2", cupCircle.toString());
        cupCircle.nextCircle();
        assertEquals("5 7 4 1 8 3 9 2 6", cupCircle.toString());
        cupCircle.nextCircle();
        assertEquals("8 3 7 4 1 9 2 6 5", cupCircle.toString());

        assertEquals("92658374", cupCircle.afterOne());

        for (int idx = 10; idx < 100; idx++)
            cupCircle.nextCircle();

        assertEquals("67384529", cupCircle.afterOne());
    }

    @Test
    void bigCircle() {
        final CupCircle circle = new CupCircle("389125467", 1_000_000);

        for (int idx = 0; idx < 10_000_000; idx++)
            circle.nextCircle();

        assertEquals(149245887792L, circle.afterOneTwoValues());
    }
}