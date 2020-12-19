package com.putoet.day19;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Rule8Test {
    private Rule rule;

    @BeforeEach
    void setup() {
        final PuzzleInput puzzleInput = new PuzzleInput(ResourceLines.list("/day19-2.txt"));
        final Rules rules = puzzleInput.rules();
        rule = new Rule8(8, rules, List.of(42), List.of(42, 8));
    }

    @Test
    void isValid() {
        assertTrue(rule.isValid("aaaba"));
        assertTrue(rule.isValid("aaabaaaaaabaaaa"));
    }
}