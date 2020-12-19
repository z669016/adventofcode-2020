package com.putoet.day19;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ListRule extends Rule {
    private final Rules rules;
    private final List<Integer> idList;

    public ListRule(Rules rules, List<Integer> idList) {
        this.rules = rules;
        this.idList = idList;
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
}
