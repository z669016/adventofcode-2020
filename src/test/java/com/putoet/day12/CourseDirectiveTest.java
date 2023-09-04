package com.putoet.day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CourseDirectiveTest {
    private static final CourseDirective N3 = CourseDirective.of("N3");
    private static final CourseDirective S5 = CourseDirective.of("S5");
    private static final CourseDirective E7 = CourseDirective.of("E7");
    private static final CourseDirective W1 = CourseDirective.of("W1");
    private static final CourseDirective L90 = CourseDirective.of("L90");
    private static final CourseDirective R180 = CourseDirective.of("R180");
    private static final CourseDirective F23 = CourseDirective.of("F23");

    @Test
    void of() {
        assertEquals(Command.NORTH, N3.command());
        assertEquals(3, N3.length());

        assertEquals(Command.SOUTH, S5.command());
        assertEquals(5, S5.length());

        assertEquals(Command.EAST, E7.command());
        assertEquals(7, E7.length());

        assertEquals(Command.WEST, W1.command());
        assertEquals(1, W1.length());

        assertEquals(Command.LEFT, L90.command());
        assertEquals(90, L90.length());

        assertEquals(Command.RIGHT, R180.command());
        assertEquals(180, R180.length());

        assertEquals(Command.FORWARD, F23.command());
        assertEquals(23, F23.length());

        assertThrows(IllegalArgumentException.class, () -> CourseDirective.of("N"));
        assertThrows(IllegalArgumentException.class, () -> CourseDirective.of("3"));
    }
}