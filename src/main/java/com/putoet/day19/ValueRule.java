package com.putoet.day19;

import java.util.Set;

public class ValueRule extends Rule {
    private final String value;

    public ValueRule(int id, String value) {
        super(id);
        this.value = value;
    }

    @Override
    protected Set<String> createValues() {
        return Set.of(value);
    }

    @Override
    protected boolean references(int id) {
        return false;
    }

    @Override
    protected Set<Integer> references(Set<Integer> set) {
        return Set.of();
    }
}
