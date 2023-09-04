package com.putoet.day19;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RuleTest {
    private Rules rules;

    @BeforeEach
    void setup() {
        rules = mock(Rules.class);
        when(rules.get(0)).thenReturn(new ValueRule(0, "a"));
        when(rules.get(1)).thenReturn(new ValueRule(1, "b"));
        when(rules.get(2)).thenReturn(new ValueRule(2, "c"));
        when(rules.get(33)).thenReturn(new ValueRule(3, "dd"));
    }


    @Test
    void ofError() {
        assertThrows(IllegalArgumentException.class, () -> Rule.of(rules, ""));
        assertThrows(IllegalArgumentException.class, () -> Rule.of(rules, "a: \"a\""));
    }

    @Test
    void ofValue() {
        final var rule = Rule.of(rules, "0: \"a\"");

        assertEquals(0, rule.id());
        assertTrue(rule instanceof ValueRule);
        assertEquals(Set.of("a"), rule.values());
    }

    @Test
    void ofList() {
        final var rule = Rule.of(rules, "0: 1 2 33");

        assertEquals(0, rule.id());
        assertTrue(rule instanceof ListRule);
        assertEquals(Set.of("bcdd"), rule.values());
    }

    @Test
    void ofChoice() {
        final var rule = Rule.of(rules, "0: 1 2 | 33");

        assertEquals(0, rule.id());
        assertTrue(rule instanceof ChoiceRule);
        assertEquals(Set.of("bc", "dd"), rule.values());
    }
}