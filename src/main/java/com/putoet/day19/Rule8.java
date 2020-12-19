package com.putoet.day19;

import java.util.List;
import java.util.Set;

public class Rule8 extends ChoiceRule {
    private final Rules rules;

    public Rule8(int id, Rules rules, List<Integer> idListOne, List<Integer> idListTwo) {
        super(id, rules, idListOne, idListTwo);
        this.rules = rules;
    }

    @Override
    public boolean isValid(String toValidate) {
        if (toValidate.length() == 0)
            return false;

        if (toValidate.length() % 5 != 0)
            return false;

        // Rule 8: 42 | 42 8
        final Rule rule42 = rules.get(42);

        // use the fact that rule42 only returns values of length 5
        while (toValidate.length() > 0 && rule42.isValid(toValidate.substring(0, 5))) {
            toValidate = toValidate.substring(5);
        }

        return toValidate.length() == 0;
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
