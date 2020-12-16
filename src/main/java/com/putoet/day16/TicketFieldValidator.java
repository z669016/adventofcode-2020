package com.putoet.day16;

import com.putoet.utilities.Validator;
import org.javatuples.Pair;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketFieldValidator implements Validator<Integer> {
    public static Pattern TICKET_FIELD_VALIDATOR = Pattern.compile("([a-z ]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)");
    private final String fieldName;
    private final List<Pair<Integer, Integer>> ranges;

    public TicketFieldValidator(String fieldName, List<Pair<Integer, Integer>> ranges) {
        assert fieldName != null;
        assert ranges != null;

        for (Pair<Integer, Integer> range : ranges)
            assert range.getValue0() <= range.getValue1();

        this.fieldName = fieldName;
        this.ranges = ranges;
    }

    public static TicketFieldValidator of(int lineNumber, String line) {
        assert line != null;

        final Matcher matcher = TICKET_FIELD_VALIDATOR.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid ticked line validator '" + line + "' at line " + lineNumber);

        return new TicketFieldValidator(matcher.group(1),
                List.of(
                        new Pair<Integer, Integer>(Integer.parseInt(matcher.group(2)), Integer.valueOf(matcher.group(3))),
                        new Pair<Integer, Integer>(Integer.parseInt(matcher.group(4)), Integer.valueOf(matcher.group(5)))
                )
        );
    }

    public String fieldName() {
        return fieldName;
    }

    public List<Pair<Integer, Integer>> ranges() {
        return ranges;
    }

    @Override
    public boolean isValid(Integer toValidate) {
        return ranges.stream().anyMatch(range -> toValidate >= range.getValue0() && toValidate <= range.getValue1());
    }

    @Override
    public String toString() {
        return "{fieldName: " + fieldName + ", ranges: " + ranges + "}";
    }
}
