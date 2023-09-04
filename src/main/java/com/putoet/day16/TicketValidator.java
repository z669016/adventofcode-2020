package com.putoet.day16;

import com.putoet.utilities.Validator;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

class TicketValidator implements Validator<Ticket> {
    private final List<TicketFieldValidator> validators;

    public TicketValidator(@NotNull List<TicketFieldValidator> validators) {
        this.validators = validators;
    }

    @Override
    public boolean isValid(@NotNull Ticket toValidate) {
        return toValidate.fields().stream()
                .allMatch(field -> validators.stream().anyMatch(validator -> validator.isValid(field)));
    }

    public List<Ticket> validTickets(@NotNull List<Ticket> tickets) {
        return tickets.stream()
                .filter(this::isValid)
                .toList();
    }

    public List<String> fieldNames(@NotNull List<Ticket> tickets) {
        final var fields = validators.stream()
                .map(TicketFieldValidator::fieldName)
                .collect(Collectors.toSet());
        final var map = validators.stream()
                .collect(Collectors.toMap(TicketFieldValidator::fieldName, validator -> validator));

        @SuppressWarnings("unchecked") final Set<String>[] possibleFieldNames = (Set<String>[]) Array.newInstance(HashSet.class, fields.size());
        for (var fieldName : fields) {
            final var validator = map.get(fieldName);
            for (var idx = 0; idx < fields.size(); idx++) {
                final var finalIdx = idx;
                if (tickets.stream().map(ticket -> ticket.field(finalIdx)).allMatch(validator::isValid)) {
                    if (possibleFieldNames[idx] == null) possibleFieldNames[idx] = new HashSet<>();
                    possibleFieldNames[idx].add(fieldName);
                }
            }
        }

        final var single = new HashSet<String>();
        while (single.size() != fields.size()) {
            for (Set<String> possibleFieldName : possibleFieldNames) {
                if (possibleFieldName.size() == 1)
                    single.addAll(possibleFieldName);
                else
                    possibleFieldName.removeIf(single::contains);
            }
        }

        return Arrays.stream(possibleFieldNames).flatMap(Collection::stream).toList();
    }
}
