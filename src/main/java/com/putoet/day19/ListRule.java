package com.putoet.day19;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ListRule extends Rule {
    private final Rules rules;
    private final List<Integer> idList;

    public ListRule(int id, Rules rules, List<Integer> idList) {
        super(id);
        this.rules = rules;
        this.idList = idList;
    }

    public List<Integer> idList() {
        return idList;
    }

    @Override
    protected Set<String> createValues() {
        final List<Rule> ruleList = idList.stream()
                .map(rules::get)
                .collect(Collectors.toList());

        Set<String> set = Set.of();
        for (Rule rule : ruleList)
            set = rule.join(set);

        return set;
    }

    @Override
    protected boolean references(int id) {
        return idList.contains(id);
    }

    @Override
    protected Set<Integer> references(Set<Integer> set) {
        return Set.copyOf(idList);
    }
}
