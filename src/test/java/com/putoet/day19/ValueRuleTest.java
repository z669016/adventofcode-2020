package com.putoet.day19;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ValueRuleTest {
    final Rule rule = new ValueRule(0, "a");

    @Test
    void isValid() {
        assertTrue(rule.isValid("a"));
        assertFalse(rule.isValid("b"));
    }

    @Test
    void createValues() {
        assertEquals(Set.of("a"), rule.values());
    }

    @Test
    void join() {
        assertEquals(Set.of("ba"), rule.join(Set.of("b")));
        assertEquals(Set.of("ba", "ca", "da"), rule.join(Set.of("b", "c", "d")));
    }
}