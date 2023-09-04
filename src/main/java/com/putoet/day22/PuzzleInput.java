package com.putoet.day22;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

record PuzzleInput(List<Integer> player1Cards, List<Integer> player2Cards) {

    public static PuzzleInput of(@NotNull List<String> lines) {
        List<Integer> player1Cards = null;

        var list = new ArrayList<Integer>();

        for (var line : lines) {
            if ("Player 1:".equals(line)) {
                continue;
            }

            if ("Player 2:".equals(line)) {
                player1Cards = list;
                list = new ArrayList<>();
                continue;
            }

            if (!line.isEmpty())
                list.add(Integer.parseInt(line));
        }

        return new PuzzleInput(player1Cards, list);
    }
}
