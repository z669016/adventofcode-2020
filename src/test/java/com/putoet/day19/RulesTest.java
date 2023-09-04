package com.putoet.day19;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RulesTest {

    @Test
    void isValid() {
        final var puzzleInput = new PuzzleInput(ResourceLines.list("/day19-1.txt"));
        final var rules = puzzleInput.rules();
        final var messages = puzzleInput.messages();

        final var ruleZero = rules.get(0);
        final var match = messages.stream().filter(ruleZero::isValid).count();

        assertEquals(2L, match);
    }

    @Test
    void isValidWithNoLoops() {
        final var puzzleInput = new PuzzleInput(ResourceLines.list("/day19-2.txt"));
        final var rules = puzzleInput.rules();
        final var messages = puzzleInput.messages();

        final var ruleZero = rules.get(0);
        final var match = messages.stream().filter(ruleZero::isValid).count();

        assertEquals(3L, match);
    }

    @Test
    void isValidWithLoops() {
        final var puzzleInput = new PuzzleInput(ResourceLines.list("/day19-2.txt"));
        final var rules = puzzleInput.rules();
        final var messages = puzzleInput.messages();

        rules.put(0, new Rule0(0, rules, List.of(8, 11)));
        rules.put(8, new Rule8(8, rules, List.of(42), List.of(42, 8)));
        rules.put(11, new Rule11(11, rules, List.of(42, 31), List.of(42, 11, 31)));

        final var ruleZero = rules.get(0);
        final var match = messages.stream().filter(ruleZero::isValid).count();

        assertEquals(12L, match);
    }
}