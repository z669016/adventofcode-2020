package com.putoet.day19;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ChoiceRule extends Rule {
    protected final ListRule one;
    protected final ListRule two;

    public ChoiceRule(int id, Rules rules, List<Integer> idListOne, List<Integer> idListTwo) {
        super(id);
        this.one = new ListRule(id, rules, idListOne);
        this.two = new ListRule(id, rules, idListTwo);
    }

    @Override
    protected Set<String> createValues() {
        final Set<String> set = new HashSet<>(one.values());
        set.addAll(two.values());

        return set;
    }
}
