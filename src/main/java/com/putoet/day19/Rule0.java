package com.putoet.day19;

import java.util.List;
import java.util.Set;

public class Rule0 extends ListRule {
    private final Rules rules;
    private Rule rule8;
    private Rule rule11;

    public Rule0(int id, Rules rules, List<Integer> idList) {
        super(id, rules, idList);
        this.rules = rules;
    }

    @Override
    public boolean isValid(String toValidate) {
        if (toValidate.length() % 5 != 0)
            return false;

        // Rule 0: 8 11
        if (rule8 == null || rule11 == null) {
            rule8 = rules.get(8);
            rule11 = rules.get(11);
        }

        boolean match = false;
        int length = 5;
        while (!match && length < toValidate.length()) {
            final String left = toValidate.substring(0, length);
            final String right = toValidate.substring(length);
            match = rule8.isValid(left) && rule11.isValid(right);
            length += 5;
        }

        return match;
    }

    private String matchRule11(String toValidate) {
        int offset = toValidate.length() - 10;
        while (offset > 0 && !rule11.isValid(toValidate.substring(offset)))
            offset -= 10;

        return toValidate.substring(0, Math.max(offset, 0));
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
