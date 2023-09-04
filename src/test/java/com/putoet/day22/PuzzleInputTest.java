package com.putoet.day22;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleInputTest {

    @Test
    void playerXCards() {
        final PuzzleInput puzzleInput = PuzzleInput.of(ResourceLines.list("/day22.txt"));
        assertEquals(List.of(9, 2, 6, 3, 1), puzzleInput.player1Cards());
        assertEquals(List.of(5, 8, 4, 7, 10), puzzleInput.player2Cards());
    }
}