package com.putoet.day19;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListRuleTest {
    private Rules rules;
    private ListRule rule;

    @BeforeEach
    void setup() {
        rules = mock(Rules.class);
        when(rules.get(0)).thenReturn(new ValueRule("a"));
        when(rules.get(1)).thenReturn(new ValueRule("b"));
        when(rules.get(2)).thenReturn(new ValueRule("c"));

        rule = new ListRule(rules, List.of(0, 1, 2));
    }

    @Test
    void createValues() {
        assertEquals(Set.of("abc"), rule.values());
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