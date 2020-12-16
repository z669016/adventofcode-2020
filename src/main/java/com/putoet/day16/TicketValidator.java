package com.putoet.day16;

import com.putoet.utilities.Validator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TicketValidator implements Validator<Ticket> {
    private final List<TicketFieldValidator> validators;

    public TicketValidator(List<TicketFieldValidator> validators) {
        this.validators = validators;
    }

    @Override
    public boolean isValid(Ticket toValidate) {
        return toValidate.fields().stream()
                    .allMatch(field -> validators.stream().anyMatch(validator -> validator.isValid(field)));
    }

    public List<Ticket> validTickets(List<Ticket> tickets) {
        return tickets.stream()
                .filter(this::isValid)
                .collect(Collectors.toList());
    }

    public List<String> fieldNames(List<Ticket> tickets) {
        final Set<String> fields = validators.stream().map(TicketFieldValidator::fieldName).collect(Collectors.toSet());
        final Map<String, TicketFieldValidator> map = validators.stream()
                .collect(Collectors.toMap(TicketFieldValidator::fieldName, validator -> validator));

        final Set<String>[] possibleFieldNames = new HashSet[fields.size()];
        for (String fieldName : fields) {
            final TicketFieldValidator validator = map.get(fieldName);

            for (int idx = 0; idx < fields.size(); idx++) {
                int finalIdx = idx;
                if (tickets.stream().map(ticket -> ticket.field(finalIdx)).allMatch(validator::isValid)) {
                    if (possibleFieldNames[idx] == null)
                        possibleFieldNames[idx] = new HashSet<>();
                    possibleFieldNames[idx].add(fieldName);
                }
            }
        }

        final Set<String> single = new HashSet<>();
        while (single.size() != fields.size()) {
            for (int idx = 0; idx < possibleFieldNames.length; idx++) {
                if (possibleFieldNames[idx].size() == 1)
                    single.addAll(possibleFieldNames[idx]);
                else
                    possibleFieldNames[idx].removeIf(single::contains);
            }
        }

        return Arrays.stream(possibleFieldNames).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
