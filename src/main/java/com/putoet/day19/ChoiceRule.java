package com.putoet.day19;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ChoiceRule extends Rule {
    protected final ListRule one;
    protected final ListRule two;

    public ChoiceRule(int id, @NotNull Rules rules, @NotNull List<Integer> idListOne, @NotNull List<Integer> idListTwo) {
        super(id);
        this.one = new ListRule(id, rules, idListOne);
        this.two = new ListRule(id, rules, idListTwo);
    }

    @Override
    protected Set<String> createValues() {
        final var set = new HashSet<>(one.values());
        set.addAll(two.values());

        return set;
    }
}
