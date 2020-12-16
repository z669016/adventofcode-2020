package com.putoet.day16;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketValidatorTest {

    @Test
    void validTickets() {
        final PuzzleInput puzzleInput = new PuzzleInput(ResourceLines.list("/day16.txt"));
        final TicketValidator validator = new TicketValidator(puzzleInput.ticketFieldValidators());
        final List<Ticket> tickets = validator.validTickets(puzzleInput.nearbyTickets());

        assertEquals(1, tickets.size());
        assertEquals(List.of(7, 3, 47), tickets.get(0).fields());
    }

    @Test
    void fieldNames() {
        final PuzzleInput puzzleInput = new PuzzleInput(ResourceLines.list("/day16-2.txt"));
        final TicketValidator validator = new TicketValidator(puzzleInput.ticketFieldValidators());
        assertEquals(List.of("row", "class", "seat"), validator.fieldNames(validator.validTickets(puzzleInput.nearbyTickets())));
    }
}