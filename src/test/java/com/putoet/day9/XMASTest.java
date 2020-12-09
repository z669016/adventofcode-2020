package com.putoet.day9;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class XMASTest {
    final XMAS xmas = new XMAS(5);

    @Test
    void firstInvalid() {
        final List<Long> numbers = ResourceLines.longList("/day9.txt");
        test(numbers);
    }

    @Test
    void firstInvalidFirstAfterPreamble() {
        final List<Long> numbers = List.of(35L, 20L, 15L, 25L, 47L, 127L,
                40L, 62L, 55L, 65L, 95L, 102L, 117L, 150L, 182L, 219L, 299L, 277L, 309L, 576L);

        test(numbers);
    }

     @Test
    void firstInvalidLastAfterPreamble() {
        final List<Long> numbers = List.of(35L, 20L, 15L, 25L, 47L,
                40L, 62L, 55L, 65L, 95L, 102L, 117L, 150L, 182L, 219L, 299L, 267L, 518L, 127L);
        test(numbers);
    }

    private void test(List<Long> numbers) {
        final int offset = xmas.firstInvalidOffset(numbers);
        assertNotEquals(-1, offset);
        assertEquals(127, numbers.get(offset));
    }

    @Test
    void weakness() {
        final List<Long> numbers = ResourceLines.longList("/day9.txt");
        assertEquals(62, xmas.weakness(numbers, 127));
    }
}