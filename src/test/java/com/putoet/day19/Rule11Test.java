package com.putoet.day19;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Rule11Test {
    private Rule rule;

    @BeforeEach
    void setup() {
        final PuzzleInput puzzleInput = new PuzzleInput(ResourceLines.list("/day19-2.txt"));
        final Rules rules = puzzleInput.rules();
        rule = new Rule11(11, rules, List.of(42, 31), List.of(42, 11, 31));
    }

    @Test
    void isValid() {
        assertTrue(rule.isValid("abbbbabaab"));
        assertTrue(rule.isValid("bbbababbbbabaabbaaab"));
    }
}