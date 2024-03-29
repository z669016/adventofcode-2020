package com.putoet.day19;

import java.util.List;
import java.util.Set;

public class Rule0 extends ListRule {
    private final Rules rules;
    private Rule rule8;
    private Rule rule11;
    private Rule rule42;
    private int rule42Length;

    public Rule0(int id, Rules rules, List<Integer> idList) {
        super(id, rules, idList);
        this.rules = rules;
    }

    @Override
    public boolean isValid(String toValidate) {
        init();

        if (toValidate.length() % rule42Length != 0)
            return false;

        boolean match = false;
        int length = rule42Length;
        while (!match && length < toValidate.length()) {
            final String left = toValidate.substring(0, length);
            final String right = toValidate.substring(length);
            match = rule8.isValid(left) && rule11.isValid(right);
            length += rule42Length;
        }

        return match;
    }

    private void init() {
        // Rule 0: 8 11
        if (rule8 == null || rule11 == null || rule42 == null) {
            rule8 = rules.get(8);
            rule11 = rules.get(11);
            rule42 = rules.get(42);

            rule42Length = rule42.values().stream().findFirst().get().length();
        }
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
