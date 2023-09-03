package com.putoet.day4;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnhancedPassportValidatorTest {

    @Test
    void isValid() {
        final var validator = new EnhancedPassportValidator();
        final var invalidBatch = Batch.of(ResourceLines.list("/day4-2.txt"));
        assertTrue(invalidBatch.stream().noneMatch(validator::isValid));

        final var validBatch = Batch.of(ResourceLines.list("/day4-3.txt"));
        assertTrue(validBatch.stream().allMatch(validator::isValid));
    }

    @Test
    void byrValidator() {
        assertTrue(EnhancedPassportValidator.byrValidator("2002"));
        assertFalse(EnhancedPassportValidator.byrValidator("2003"));
    }

    @Test
    void hgtValidator() {
        assertTrue(EnhancedPassportValidator.hgtValidator("60in"));
        assertTrue(EnhancedPassportValidator.hgtValidator("190cm"));
        assertFalse(EnhancedPassportValidator.hgtValidator("190in"));
        assertFalse(EnhancedPassportValidator.hgtValidator("190"));
    }

    @Test
    void hclValidator() {
        assertTrue(EnhancedPassportValidator.hclValidator("#123abc"));
        assertFalse(EnhancedPassportValidator.hclValidator("#123abz"));
        assertFalse(EnhancedPassportValidator.hclValidator("123abc"));
    }

    @Test
    void eclValidator() {
        assertTrue(EnhancedPassportValidator.eclValidator("brn"));
        assertFalse(EnhancedPassportValidator.eclValidator("wat"));
    }

    @Test
    void pidValidator() {
        assertTrue(EnhancedPassportValidator.pidValidator("000000001"));
        assertFalse(EnhancedPassportValidator.pidValidator("0123456789"));
    }
}