package com.putoet.day19;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RulesTest {

    @Test
    void isValid() {
        final PuzzleInput puzzleInput = new PuzzleInput(ResourceLines.list("/day19.txt"));
        final Rules rules = puzzleInput.rules();
        final List<String> messages = puzzleInput.messages();

        final Rule ruleZero = rules.get(0);
        final long match = messages.stream().filter(ruleZero::isValid).count();

        assertEquals(2L, match);
    }

    @Test
    void isValidWithLoops() {
        // Unchanged puzzle
        PuzzleInput puzzleInput = new PuzzleInput(ResourceLines.list("/day19-2.txt"));
        Rules rules = puzzleInput.rules();
        List<String> messages = puzzleInput.messages();

        Rule ruleZero = rules.get(0);
        long match = messages.stream().filter(ruleZero::isValid).count();

        assertEquals(3L, match);

        // Unchanged puzzle
        puzzleInput = new PuzzleInput(ResourceLines.list("/day19-2.txt"));
        rules = puzzleInput.rules();
        messages = puzzleInput.messages();

//        rules.put(8, Rule.of(rules, "8: 42 | 42 8").getValue1());
//        rules.put(11, Rule.of(rules, "11: 42 31 | 42 11 31").getValue1());

//        ruleZero = rules.get(0);
//        match = messages.stream().filter(ruleZero::isValid).count();
//
//        assertEquals(3L, match);

        System.out.println("rules referencing 8: " + rules.references(8));
        System.out.println("rules referencing 11: " + rules.references(11));

        System.out.println("Values for 42 are " + rules.get(42).values());
        System.out.println("Values for 31 are " + rules.get(31).values());
    }
}