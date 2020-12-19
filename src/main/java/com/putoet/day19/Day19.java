package com.putoet.day19;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day19 {
    public static void main(String[] args) {
        final PuzzleInput puzzleInput = new PuzzleInput(ResourceLines.list("/day19.txt"));
        part1(puzzleInput);
        part2(puzzleInput);
    }

    private static void part1(PuzzleInput puzzleInput) {
        final Rules rules = puzzleInput.rules();
        final List<String> messages = puzzleInput.messages();

        final Rule ruleZero = rules.get(0);
        final long match = messages.stream().filter(ruleZero::isValid).count();
        System.out.println("The number of messages completely match rule 0 is " + match);
    }

    private static void part2(PuzzleInput puzzleInput) {
        final Rules rules = puzzleInput.rules();

        rules.put(0, new Rule0(0, rules, List.of(8, 11)));
        rules.put(8, new Rule8(8, rules, List.of(42), List.of(42, 8)));
        rules.put(11, new Rule11(11, rules, List.of(42, 31), List.of(42, 11, 31)));

        final List<String> messages = puzzleInput.messages();

        final Rule ruleZero = rules.get(0);
        final long match = messages.stream().filter(ruleZero::isValid).count();
        System.out.println("The number of messages completely match (looping) rule 0 is " + match);

        // 74 is not right
        // 0 is not right
    }
}
