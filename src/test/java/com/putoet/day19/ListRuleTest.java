package com.putoet.day19;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListRuleTest {
    private ListRule rule;

    @BeforeEach
    void setup() {
        var rules = mock(Rules.class);
        when(rules.get(0)).thenReturn(new ValueRule(0, "a"));
        when(rules.get(1)).thenReturn(new ValueRule(1, "b"));
        when(rules.get(2)).thenReturn(new ValueRule(2, "c"));

        rule = new ListRule(3, rules, List.of(0, 1, 2));
    }

    @Test
    void createValues() {
        assertEquals(3, rule.id());
        assertFalse(rule.initialized());
        assertEquals(Set.of("abc"), rule.values());
        assertTrue(rule.initialized());
    }

    @Test
    void isValid() {
        assertTrue(rule.isValid("abc"));
        assertFalse(rule.isValid("ab"));
    }

    @Test
    void join() {
        assertEquals(Set.of("eabc"), rule.join(Set.of("e")));
        assertEquals(Set.of("eabc", "fabc"), rule.join(Set.of("e", "f")));
    }
}