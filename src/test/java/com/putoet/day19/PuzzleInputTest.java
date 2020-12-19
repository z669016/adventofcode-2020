package com.putoet.day19;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleInputTest {
    private PuzzleInput puzzleInput;

    @BeforeEach
    void setup() {
        puzzleInput = new PuzzleInput(ResourceLines.list("/day19.txt"));
    }

    @Test
    void rules() {
        assertEquals(6, puzzleInput.rules().size());
    }

    @Test
    void messages() {
        assertEquals(5, puzzleInput.messages().size());
    }
}