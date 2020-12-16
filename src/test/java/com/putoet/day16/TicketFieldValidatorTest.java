package com.putoet.day16;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketFieldValidatorTest {
    private List<TicketFieldValidator> validators;

    @BeforeEach
    void setup() {
        final PuzzleInput puzzleInput = new PuzzleInput(ResourceLines.list("/day16.txt"));
        validators = puzzleInput.ticketFieldValidators();
    }

    @Test
    void isValid() {
        final TicketFieldValidator classValidator = validators.get(0);
        assertEquals("class", classValidator.fieldName());
        assertTrue(classValidator.isValid(1));
        assertTrue(classValidator.isValid(3));
        assertTrue(classValidator.isValid(5));
        assertTrue(classValidator.isValid(7));

        assertFalse(classValidator.isValid(4));
    }
}