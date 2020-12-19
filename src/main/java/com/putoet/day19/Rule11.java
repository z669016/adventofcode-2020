package com.putoet.day19;

import java.util.List;
import java.util.Set;

public class Rule11 extends ChoiceRule {
    private final Rules rules;
    private Rule rule11;

    public Rule11(int id, Rules rules, List<Integer> idListOne, List<Integer> idListTwo) {
        super(id, rules, idListOne, idListTwo);
        this.rules = rules;
    }

    @Override
    public boolean isValid(String toValidate) {
        if (toValidate.length() == 0)
            return false;

        if (toValidate.length() % 10 != 0)
            return false;

        // Rule 11: 42 31 | 42 11 31
        if (rule11 == null) {
            rule11 = Rule.of(rules,"11: 42 31");
        }

        String center = center(toValidate);
        while (center.length() > 0 && rule11.values().contains(center)) {
            toValidate = removeCenter(toValidate);
            center = center(toValidate);
        }

        return toValidate.length() == 0;
    }

    private String removeCenter(String toValidate) {
        int offset = toValidate.length() / 2;
        return toValidate.substring(0, offset - 5) + toValidate.substring(offset + 5);
    }

    private String center(String toValidate) {
        final int offset = toValidate.length() / 2;
        if (offset == 0)
            return "";

        final String left = toValidate.substring(offset - 5, offset);
        final String right = toValidate.substring(offset, offset + 5);
        return left + right;
    }

    @Override
    protected Set<String> values() {
        throw new IllegalStateException("createValues should not be called on rule " + id());
    }

    @Override
    protected Set<String> createValues() {
        throw new IllegalStateException("createValues should not be called on rule " + id());
    }
}
