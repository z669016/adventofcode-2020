package com.putoet.day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PuzzleInput {
    private final List<String> lines;

    public PuzzleInput(List<String> lines) {
        assert lines != null;

        this.lines = lines;
    }

    public List<TicketFieldValidator> ticketFieldValidators() {
        final List<TicketFieldValidator> validators= new ArrayList<>();

        for (int idx = 0; lines.get(idx).length() > 0; idx++) {
            final String line = lines.get(idx);

            validators.add(TicketFieldValidator.of(idx, line));
        }

        return validators;
    }

    public Ticket myTicket() {
        for (int idx = 0; idx < lines.size() - 1; idx++) {
            final String line = lines.get(idx);

            if (line.startsWith("your ticket:")) {
                final String myTicket = lines.get(idx + 1);
                return new Ticket("myTicket", Arrays.stream(myTicket.split(","))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList()));
            }
        }

        throw new IllegalArgumentException("Puzzle input doesn't contain 'your ticket:'");
    }

    public List<Ticket> nearbyTickets() {
        final List<Ticket> nearbyTickets = new ArrayList<>();

        boolean tickets = false;
        for (int idx = 0; idx < lines.size(); idx++) {
            final String line = lines.get(idx);

            if (tickets) {
                final String ticket = lines.get(idx);
                nearbyTickets.add(new Ticket("ticket-" + idx, Arrays.stream(ticket.split(","))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList())));
            }

            if (line.startsWith("nearby tickets:"))
                tickets = true;
        }

        if (!tickets)
            throw new IllegalArgumentException("Puzzle input doesn't contain 'nearby tickets:'");

        return nearbyTickets;
    }
}
