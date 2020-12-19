package com.putoet.day19;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day19 {
    public static void main(String[] args) {
        final PuzzleInput puzzleInput = new PuzzleInput(ResourceLines.list("/day19.txt"));
        part1(puzzleInput);
    }

    private static void part1(PuzzleInput puzzleInput) {
        final Rules rules = puzzleInput.rules();
        final List<String> messages = puzzleInput.messages();

        final Rule ruleZero = rules.get(0);
        final long match = messages.stream().filter(ruleZero::isValid).count();
        System.out.println("The number of messages completely match rule 0 is " + match);
    }
}
