package com.putoet.day19;

import org.javatuples.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RuleTest {
    private Rules rules;

    @BeforeEach
    void setup() {
        rules = mock(Rules.class);
        when(rules.get(0)).thenReturn(new ValueRule("a"));
        when(rules.get(1)).thenReturn(new ValueRule("b"));
        when(rules.get(2)).thenReturn(new ValueRule("c"));
        when(rules.get(33)).thenReturn(new ValueRule("dd"));
    }


    @Test
    void ofError() {
        assertThrows(AssertionError.class, () -> Rule.of(rules,null));
        assertThrows(IllegalArgumentException.class, () -> Rule.of(rules,""));
        assertThrows(IllegalArgumentException.class, () -> Rule.of(rules,"a: \"a\""));
    }

    @Test
    void ofValue() {
        final Pair<Integer,Rule> rule = Rule.of(rules,"0: \"a\"");

        assertEquals(0, rule.getValue0());
        assertTrue(rule.getValue1() instanceof ValueRule);
        assertEquals(Set.of("a"), rule.getValue1().values());
    }

    @Test
    void ofList() {
        final Pair<Integer,Rule> rule = Rule.of(rules,"0: 1 2 33");

        assertEquals(0, rule.getValue0());
        assertTrue(rule.getValue1() instanceof ListRule);
        assertEquals(Set.of("bcdd"), rule.getValue1().values());
    }

    @Test
    void ofChoice() {
        final Pair<Integer,Rule> rule = Rule.of(rules,"0: 1 2 | 33");

        assertEquals(0, rule.getValue0());
        assertTrue(rule.getValue1() instanceof ChoiceRule);
        assertEquals(Set.of("bc", "dd"), rule.getValue1().values());
    }
}