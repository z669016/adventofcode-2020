package com.putoet.day19;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

class Rule8 extends ChoiceRule {
    private final Rules rules;
    private Rule rule42;
    private int rule42Length;

    public Rule8(int id, Rules rules, List<Integer> idListOne, List<Integer> idListTwo) {
        super(id, rules, idListOne, idListTwo);
        this.rules = rules;
    }

    @Override
    public boolean isValid(@NotNull String toValidate) {
        init();

        if (toValidate.length() < rule42Length)
            return false;

        while (toValidate.length() >= rule42Length && rule42.isValid(toValidate.substring(0, rule42Length))) {
            toValidate = toValidate.substring(rule42Length);
        }

        return toValidate.isEmpty();
    }

    private void init() {
        // Rule 8: 42 | 42 8
        rule42 = rules.get(42);
        rule42Length = rule42.values().stream().findFirst().orElseThrow().length();
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
