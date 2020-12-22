package com.putoet.day22;

import java.util.List;

public class Combat {
    protected final Player player1, player2;
    protected int rounds;

    public Combat(List<Integer> cards1, List<Integer> cards2) {
        assert cards1 != null && cards2 != null;

        player1 = new Player(1, cards1);
        player2 = new Player(2, cards2);
    }

    public void play() {
        while (player1.hasNext() && player2.hasNext()) {
            rounds++;
            int card1 = player1.next();
            int card2 = player2.next();

            if (card1 > card2) {
                player1.add(card1);
                player1.add(card2);
            }
            else {
                player2.add(card2);
                player2.add(card1);
            }
        }
    }

    public Player player1() { return player1; }
    public Player player2() { return player2; }
    public int rounds() { return rounds; }

    public Player winner() {
        if (!player1().lost() && player2().lost())
            return player1;
        if (player1().lost() && !player2().lost())
            return player2;

        throw new IllegalStateException("No winner, both players still have cards, or both have none");
    }
}
