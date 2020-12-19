package com.putoet.day19;

import org.javatuples.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rules {
    private final Map<Integer,Rule> rules = new HashMap<>();

    public static Rules of(List<String> lines) {
        final Rules rules = new Rules();

        for (String line : lines) {
            final Pair<Integer,Rule> rule = Rule.of(rules, line);
            rules.rules.put(rule.getValue0(), rule.getValue1());
        }

        return rules;
    }

    private Rules() {}

    public Rule get(int id) {
        if (!rules.containsKey(id))
            throw new IllegalArgumentException("Rule " + id + " has not been defined.");

        return rules.get(id);
    }

    public void put(int id, Rule rule) {
        rules.put(id, rule);
    }

    public int size() {
        return rules.size();
    }
}
