package com.putoet.day4;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassportValidatorTest {

    @Test
    void isValid() {
        final var passports = Batch.of(ResourceLines.list("/day4.txt"));
        final var validator = new PassportValidator();
        assertEquals(2L, passports.stream().filter(validator::isValid).count());
    }
}