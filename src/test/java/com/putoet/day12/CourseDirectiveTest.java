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
        assertEquals("NORTH-3", N3.toString());
        assertEquals("SOUTH-5", S5.toString());
        assertEquals("EAST-7", E7.toString());
        assertEquals("WEST-1", W1.toString());
        assertEquals("LEFT-90", L90.toString());
        assertEquals("RIGHT-180", R180.toString());
        assertEquals("FORWARD-23", F23.toString());

        assertThrows(AssertionError.class, () -> CourseDirective.of(null));
        assertThrows(IllegalArgumentException.class, () -> CourseDirective.of("N"));
        assertThrows(IllegalArgumentException.class, () -> CourseDirective.of("3"));
    }
}