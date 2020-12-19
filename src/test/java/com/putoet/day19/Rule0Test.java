package com.putoet.day19;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Rule0Test {
    private PuzzleInput puzzleInput;
    private Rule rule;

    @BeforeEach
    void setup() {
        puzzleInput = new PuzzleInput(ResourceLines.list("/day19-2.txt"));
        final Rules rules = puzzleInput.rules();
        rules.put(8, new Rule8(8, rules, List.of(42), List.of(42, 8)));
        rules.put(11, new Rule11(11, rules, List.of(42, 31), List.of(42, 11, 31)));

        rule = new Rule0(0, rules, List.of(8, 11));
    }

    @Test
    void isValid() {
        assertTrue(rule.isValid("aaabaabbbbabaab"));
        assertTrue(rule.isValid("aaabaaaaaabaaaabbbababbbbabaabbaaab"));
    }

    @Test
    void sample() {
        final long match = puzzleInput.messages().stream()
                .filter(rule::isValid)
                .peek(System.out::println)
                .count();
        assertEquals(12, match);
    }
}