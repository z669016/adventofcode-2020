package com.putoet.day22;

import com.putoet.resources.ResourceLines;

public class Day22 {
    public static void main(String[] args) {
        final PuzzleInput puzzleInput = new PuzzleInput(ResourceLines.list("/day22.txt"));
        part1(puzzleInput);
        part2(puzzleInput);
    }

    private static void part1(PuzzleInput puzzleInput) {
        final Combat combat = new Combat(puzzleInput.player1Cards(), puzzleInput.player2Cards());
        combat.play();

        System.out.println("the winning player's score on the game of Combat is " + combat.winner().score());
    }

    private static void part2(PuzzleInput puzzleInput) {
        final RecursiveCombat combat = new RecursiveCombat(puzzleInput.player1Cards(), puzzleInput.player2Cards());
        combat.play();

        System.out.println("the winning player's (" + combat.winner().id() + ") score on the game of RecursiveCombat is " + combat.winner().score());
        if (combat.wasInfiniteGame())
            System.out.println("Game ended as infinite game!");

        // 34151 is too high
    }
}
