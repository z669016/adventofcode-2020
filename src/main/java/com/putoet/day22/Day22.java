package com.putoet.day22;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day22 {
    public static void main(String[] args) {
        final var puzzleInput = PuzzleInput.of(ResourceLines.list("/day22.txt"));
        Timer.run(() -> part1(puzzleInput));
        Timer.run(() -> part2(puzzleInput));
    }

    private static void part1(PuzzleInput puzzleInput) {
        final var combat = new Combat(puzzleInput.player1Cards(), puzzleInput.player2Cards());
        combat.play();

        System.out.println("the winning player's score on the game of Combat is " + combat.winner().score());
    }

    private static void part2(PuzzleInput puzzleInput) {
        final var combat = new RecursiveCombat(puzzleInput.player1Cards(), puzzleInput.player2Cards());
        combat.play();

        System.out.println("the winning player's (" + combat.winner().id() + ") score on the game of RecursiveCombat is " + combat.winner().score());
        if (combat.wasInfiniteGame())
            System.out.println("Game ended as infinite game!");
    }
}
