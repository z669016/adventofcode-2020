package com.putoet.day22;

import java.util.ArrayList;
import java.util.List;

public class PuzzleInput {
    private List<Integer> player1Cards;
    private List<Integer> player2Cards;

    public PuzzleInput(List<String> lines) {
        assert lines != null;

        int player;
        List<Integer> list = new ArrayList<>();
        for (String line : lines) {
            if ("Player 1:".equals(line)) {
                player = 1;
                continue;
            }

            if ("Player 2:".equals(line)) {
                player1Cards = list;
                list = new ArrayList<>();
                player = 2;
                continue;
            }

            if (line.length() > 0)
                list.add(Integer.parseInt(line));
        }
        player2Cards = list;
    }

    public List<Integer> player1Cards() { return player1Cards; }
    public List<Integer> player2Cards() { return player2Cards; }
}
