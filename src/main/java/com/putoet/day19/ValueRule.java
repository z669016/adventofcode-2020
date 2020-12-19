package com.putoet.day19;

import java.util.Set;

public class ValueRule extends Rule {
    private final String value;

    public ValueRule(String value) {
        this.value = value;
    }

    @Override
    protected Set<String> createValues() {
        return Set.of(value);
    }
}
