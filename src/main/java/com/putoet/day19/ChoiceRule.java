package com.putoet.day19;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ChoiceRule extends Rule {
    private final Rules rules;
    private final ListRule one;
    private final ListRule two;

    public ChoiceRule(Rules rules, List<Integer> idListOne, List<Integer> idListTwo) {
        this.rules = rules;
        this.one = new ListRule(rules, idListOne);
        this.two = new ListRule(rules, idListTwo);
    }

    @Override
    protected Set<String> createValues() {
        final Set<String> set = new HashSet<>(one.values());
        set.addAll(two.values());

        return set;
    }
}
