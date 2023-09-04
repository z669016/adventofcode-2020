package com.putoet.day19;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChoiceRuleTest {
    private ChoiceRule rule;

    @BeforeEach
    void setup() {
        var rules = mock(Rules.class);
        when(rules.get(0)).thenReturn(new ValueRule(0, "a"));
        when(rules.get(1)).thenReturn(new ValueRule(1, "b"));
        when(rules.get(2)).thenReturn(new ValueRule(2, "c"));
        when(rules.get(3)).thenReturn(new ValueRule(3, "d"));
        when(rules.get(4)).thenReturn(new ValueRule(4, "e"));
        when(rules.get(5)).thenReturn(new ValueRule(5, "f"));

        rule = new ChoiceRule(6, rules, List.of(0, 1, 2), List.of(3, 4, 5));
    }

    @Test
    void createValues() {
        assertEquals(Set.of("abc", "def"), rule.values());
    }

    @Test
    void isValid() {
        assertTrue(rule.isValid("abc"));
        assertTrue(rule.isValid("def"));
        assertFalse(rule.isValid("ab"));
    }

    @Test
    void join() {
        assertEquals(Set.of("0abc", "0def"), rule.join(Set.of("0")));
        assertEquals(Set.of("0abc", "0def", "1abc", "1def"), rule.join(Set.of("0", "1")));
    }
}