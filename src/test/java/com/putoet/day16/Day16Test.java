package com.putoet.day16;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day16Test {

    @Test
    void errorRate() {
        final PuzzleInput puzzleInput = new PuzzleInput(ResourceLines.list("/day16.txt"));
        final List<Ticket> tickets = puzzleInput.nearbyTickets();

        assertEquals(71, Day16.errorRate(puzzleInput, tickets));
    }
}