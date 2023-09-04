package com.putoet.day16;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day16Test {

    @Test
    void errorRate() {
        final var puzzleInput = new PuzzleInput(ResourceLines.list("/day16.txt"));
        final var tickets = puzzleInput.nearbyTickets();

        assertEquals(71, Day16.errorRate(puzzleInput, tickets));
    }
}