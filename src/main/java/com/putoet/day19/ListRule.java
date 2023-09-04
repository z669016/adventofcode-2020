package com.putoet.day19;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

class ListRule extends Rule {
    private final Rules rules;
    private final List<Integer> idList;

    public ListRule(int id, @NotNull Rules rules, @NotNull List<Integer> idList) {
        super(id);
        this.rules = rules;
        this.idList = idList;
    }

    @Override
    protected Set<String> createValues() {
        final var ruleList = idList.stream()
                .map(rules::get)
                .toList();

        Set<String> set = Set.of();
        for (var rule : ruleList)
            set = rule.join(set);

        return set;
    }
}
