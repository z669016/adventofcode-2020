package com.putoet.day19;

import com.putoet.utilities.Validator;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

abstract class Rule implements Validator<String> {
    private static final Pattern VALUE_RULE = Pattern.compile("^(\\d+): \"([a-z])\"$");
    private static final Pattern LIST_RULE = Pattern.compile("^(\\d+): ((\\d+)( \\d+)*)$");
    private static final Pattern CHOICE_RULE = Pattern.compile("^(\\d+): ((\\d+)( \\d+)*) \\| ((\\d+)( \\d+)*)$");
    public static Rule of(@NotNull Rules rules, @NotNull String rule) {
        var matcher = LIST_RULE.matcher(rule);
        if (matcher.matches()) {
            final var idList = asIntList(matcher.group(2));
            return new ListRule(Integer.parseInt(matcher.group(1)), rules, idList);
        }

        matcher = CHOICE_RULE.matcher(rule);
        if (matcher.matches()) {
            final var one = asIntList(matcher.group(2));
            final var two = asIntList(matcher.group(5));
            return new ChoiceRule(Integer.parseInt(matcher.group(1)), rules, one, two);
        }

        matcher = VALUE_RULE.matcher(rule);
        if (matcher.matches())
            return new ValueRule(Integer.parseInt(matcher.group(1)), matcher.group(2));

        throw new IllegalArgumentException("Invalid rule '" + rule + "'");
    }

    private static List<Integer> asIntList(String text) {
        return Arrays.stream(text.split(" "))
                .map(Integer::parseInt)
                .toList();
    }

    private final int id;
    protected Set<String> values;

    protected Rule(int id) {
        this.id = id;
    }

    protected Set<String> values() {
        if (values == null)
            values = createValues();

        return values;
    }

    public int id() {
        return id;
    }

    public boolean initialized() {
        return values != null;
    }

    protected abstract Set<String> createValues();

    protected Set<String> join(Set<String> otherValues) {
        return otherValues.isEmpty() ? new HashSet<>(values()) : otherValues.stream()
                .map(otherValue -> values().stream().map(myValue -> otherValue + myValue).collect(Collectors.toSet()))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(@NotNull String toValidate) {
        return values().contains(toValidate);
    }
}
