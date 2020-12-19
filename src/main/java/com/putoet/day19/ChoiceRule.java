package com.putoet.day19;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ChoiceRule extends Rule {
    private final Rules rules;
    protected final ListRule one;
    protected final ListRule two;

    public ChoiceRule(int id, Rules rules, List<Integer> idListOne, List<Integer> idListTwo) {
        super(id);
        this.rules = rules;
        this.one = new ListRule(id, rules, idListOne);
        this.two = new ListRule(id, rules, idListTwo);
    }

    @Override
    protected Set<String> createValues() {
        final Set<String> set = new HashSet<>(one.values());
        set.addAll(two.values());

        return set;
    }

    @Override
    protected boolean references(int id) {
        return one.references(id) || two.references(id);
    }

    @Override
    protected Set<Integer> references(Set<Integer> set) {
        return List.of(one.idList(), two.idList()).stream().flatMap(Collection::stream).collect(Collectors.toSet());
    }
}
