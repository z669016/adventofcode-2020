package com.putoet.day19;

import com.putoet.utilities.Validator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class Rule implements Validator<String> {
    private static final Pattern VALUE_RULE = Pattern.compile("^(\\d+): \"([a-z])\"$");
    private static final Pattern LIST_RULE = Pattern.compile("^(\\d+): ((\\d+)( \\d+)*)$");
    private static final Pattern CHOICE_RULE = Pattern.compile("^(\\d+): ((\\d+)( \\d+)*) \\| ((\\d+)( \\d+)*)$");

    private final int id;
    protected Set<String> values;

    protected Rule(int id) {
        this.id = id;
    }

    public static Rule of(Rules rules, String rule) {
        assert rule != null;

        Matcher matcher = LIST_RULE.matcher(rule);
        if (matcher.matches()) {
            final List<Integer> idList = asIntList(matcher.group(2));
            return new ListRule(Integer.parseInt(matcher.group(1)), rules, idList);
        }

        matcher = CHOICE_RULE.matcher(rule);
        if (matcher.matches()) {
            final List<Integer> one = asIntList(matcher.group(2));
            final List<Integer> two = asIntList(matcher.group(5));
            return new ChoiceRule(Integer.parseInt(matcher.group(1)), rules, one, two);
        }

        matcher = VALUE_RULE.matcher(rule);
        if (matcher.matches())
            return new ValueRule(Integer.parseInt(matcher.group(1)), matcher.group(2));

        throw new IllegalArgumentException("Invalid rule '" + rule + "'");
    }

    private static List<Integer> asIntList(String text) {
        final List<Integer> idList = Arrays.stream(text.split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        return idList;
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

    protected abstract boolean references(int id);

    protected abstract Set<Integer> references(Set<Integer> set);

    protected Set<String> join(Set<String> otherValues) {
        return otherValues.isEmpty() ? new HashSet<>(values()) : otherValues.stream()
                .map(otherValue -> values().stream().map(myValue -> otherValue + myValue).collect(Collectors.toSet()))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String toValidate) {
        return values().contains(toValidate);
    }
}
