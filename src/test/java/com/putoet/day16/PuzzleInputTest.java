package com.putoet.day16;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleInputTest {
    private PuzzleInput puzzleInput;

    @BeforeEach
    void setup() {
        puzzleInput = new PuzzleInput(ResourceLines.list("/day16.txt"));
    }

    @Test
    void ticketFieldValidators() {
        final var ticketFieldValidators = puzzleInput.ticketFieldValidators();
        assertEquals(3, ticketFieldValidators.size());
        assertEquals("class", ticketFieldValidators.get(0).fieldName());
        assertEquals("row", ticketFieldValidators.get(1).fieldName());
        assertEquals("seat", ticketFieldValidators.get(2).fieldName());
    }

    @Test
    void myTicket() {
        final var ticket = puzzleInput.myTicket();
        assertEquals("myTicket", ticket.name());
        assertEquals(List.of(7, 1, 14), ticket.fields());
    }

    @Test
    void nearbyTicket() {
        final var tickets = puzzleInput.nearbyTickets();
        assertEquals(4, tickets.size());
        tickets.forEach(ticket -> assertTrue(ticket.name().startsWith("ticket-")));
    }
}