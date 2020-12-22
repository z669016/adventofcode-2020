package com.putoet.day22;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecursiveCombat extends Combat {
    private final Set<String> previousRounds = new HashSet<>();
    private Player winner;

    public RecursiveCombat(List<Integer> cards1, List<Integer> cards2) {
        super(cards1, cards2);
    }

    public void play() {
        int subId = 0;
        while (player1.hasNext() && player2.hasNext()) {
            // if this config (the combination of cards for player 1 AND player 2) was seen before, player1 wins.
            // This is to prevent the game continues indefinitely
            if (!(previousRounds.add(player1.cards().toString()+player2.cards().toString()))) {
                winner = player1;
                return;
            }

            rounds++;

            int card1 = player1.next();
            int card2 = player2.next();

            // If the card value for both players match the number of cards in their deck, start a sub game
            if (card1 <= player1.cards().size() && card2 <= player2.cards().size()) {

                // in a sub game, each player starts with the number of cards that equals the drawn cards value
                final RecursiveCombat subGame =
                        new RecursiveCombat(player1.cards().subList(0, card1), player2.cards().subList(0, card2));

                subGame.play();

                // add the cards back for the winner, winner's card first (not the highest card
                if (subGame.winner().id() == 1) {
                    player1.add(card1);
                    player1.add(card2);
                } else {
                    player2.add(card2);
                    player2.add(card1);
                }
            } else {
                // normal game rules when no sub game
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
    }

    public Player winner() {
        if (winner != null)
            return winner;

        return super.winner();
    }

    public boolean wasInfiniteGame() {
        return winner != null;
    }
}
