package com.putoet.day16;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

record PuzzleInput(@NotNull List<String> lines) {
    public List<TicketFieldValidator> ticketFieldValidators() {
        final var validators = new ArrayList<TicketFieldValidator>();

        for (var idx = 0; !lines.get(idx).isEmpty(); idx++) {
            final var line = lines.get(idx);

            validators.add(TicketFieldValidator.of(idx, line));
        }

        return validators;
    }

    public Ticket myTicket() {
        for (var idx = 0; idx < lines.size() - 1; idx++) {
            final var line = lines.get(idx);

            if (line.startsWith("your ticket:")) {
                final var myTicket = lines.get(idx + 1);
                return new Ticket(
                        "myTicket",
                        Arrays.stream(myTicket.split(","))
                                .map(Integer::parseInt)
                                .toList());
            }
        }

        throw new IllegalArgumentException("Puzzle input doesn't contain 'your ticket:'");
    }

    public List<Ticket> nearbyTickets() {
        final var nearbyTickets = new ArrayList<Ticket>();

        var tickets = false;
        for (var idx = 0; idx < lines.size(); idx++) {
            final var line = lines.get(idx);

            if (tickets) {
                final var ticket = lines.get(idx);
                nearbyTickets.add(
                        new Ticket(
                                "ticket-" + idx,
                                Arrays.stream(ticket.split(","))
                                        .map(Integer::parseInt)
                                        .toList()
                        )
                );
            }

            if (line.startsWith("nearby tickets:"))
                tickets = true;
        }

        if (!tickets)
            throw new IllegalArgumentException("Puzzle input doesn't contain 'nearby tickets:'");

        return nearbyTickets;
    }
}
