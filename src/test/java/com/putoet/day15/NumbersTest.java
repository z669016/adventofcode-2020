package com.putoet.day15;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class NumbersTest {

    @Test
    void get() {
        final var numbers = new Numbers(List.of(0, 3, 6));
        final var list = IntStream.range(4, 11).mapToLong(turn -> numbers.get()).boxed().toList();
        assertEquals(List.of(0L, 3L, 3L, 1L, 0L, 4L, 0L), list);
    }

    @Test
    void sample() {
        test(new Numbers(List.of(0, 3, 6)), 436);
        test(new Numbers(List.of(1, 3, 2)), 1);
        test(new Numbers(List.of(2, 1, 3)), 10);
        test(new Numbers(List.of(1, 2, 3)), 27);
        test(new Numbers(List.of(2, 3, 1)), 78);
        test(new Numbers(List.of(3, 2, 1)), 438);
        test(new Numbers(List.of(3, 1, 2)), 1836);
    }

    private void test(Numbers numbers, long expected) {
        assertEquals(expected, Day15.getNumber(numbers, 2020));
    }
}