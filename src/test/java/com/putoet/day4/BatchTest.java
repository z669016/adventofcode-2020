package com.putoet.day4;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BatchTest {

    @Test
    void of() {
        final var passports = Batch.of(ResourceLines.list("/day4.txt"));
        assertEquals(4, passports.size());
        System.out.println(passports);
    }
}