package com.putoet.day16;

import com.putoet.resources.ResourceLines;

import java.util.List;
import java.util.stream.IntStream;

public class Day16 {
    public static void main(String[] args) {
        final PuzzleInput puzzleInput = new PuzzleInput(ResourceLines.list("/day16.txt"));

        part1(puzzleInput);
        part2(puzzleInput);
    }

    private static void part1(PuzzleInput puzzleInput) {
        final List<Ticket> tickets = puzzleInput.nearbyTickets();
        final int errorRate = errorRate(puzzleInput, tickets);

        System.out.println("ticket scanning error rate is " + errorRate);
    }

    protected static int errorRate(PuzzleInput puzzleInput, List<Ticket> tickets) {
        final List<TicketFieldValidator> validators = puzzleInput.ticketFieldValidators();

        return tickets.stream()
                .mapToInt(ticket -> ticket.fields().stream()
                        .filter(field -> validators.stream().noneMatch(validator -> validator.isValid(field)))
                        .mapToInt(field -> field)
                        .sum())
                .sum();
    }

    private static void part2(PuzzleInput puzzleInput) {
        final List<Ticket> tickets = puzzleInput.nearbyTickets();
        final TicketValidator validator = new TicketValidator(puzzleInput.ticketFieldValidators());
        final List<Ticket> validTickets = validator.validTickets(tickets);
        final List<String> fieldNames = validator.fieldNames(validTickets);

        final Ticket myTicket = puzzleInput.myTicket();
        final long departure = IntStream.range(0, fieldNames.size())
                .filter(idx -> fieldNames.get(idx).startsWith("departure"))
                .mapToLong(myTicket::field)
                .reduce(1, (a, b) -> a * b);

        System.out.println("if you multiply those six departure values together, you get " + departure);
    }
}
