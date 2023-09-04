package com.putoet.day16;

import com.putoet.utilities.Validator;
import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Pattern;

record TicketFieldValidator(@NotNull String fieldName, @NotNull List<Pair<Integer,Integer>> ranges)
        implements Validator<Integer> {

    public static Pattern TICKET_FIELD_VALIDATOR = Pattern.compile("([a-z ]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)");
    public static TicketFieldValidator of(int lineNumber, @NotNull String line) {
        final var matcher = TICKET_FIELD_VALIDATOR.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid ticked line validator '" + line + "' at line " + lineNumber);

        return new TicketFieldValidator(matcher.group(1),
                List.of(
                        Pair.with(Integer.parseInt(matcher.group(2)), Integer.valueOf(matcher.group(3))),
                        Pair.with(Integer.parseInt(matcher.group(4)), Integer.valueOf(matcher.group(5)))
                )
        );
    }

    public TicketFieldValidator {
        for (var range : ranges)
            assert range.getValue0() <= range.getValue1();
    }

    @Override
    public boolean isValid(@NotNull Integer toValidate) {
        return ranges.stream().anyMatch(range -> toValidate >= range.getValue0() && toValidate <= range.getValue1());
    }
}
