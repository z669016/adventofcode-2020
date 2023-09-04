package com.putoet.day22;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveCombatTest {

    @Test
    void playInfiniteGame() {
        final var combat = new RecursiveCombat(List.of(43,19), List.of(2, 29, 14));
        combat.play();

        assertEquals(1, combat.winner().id());
    }

    @Test
    void play() {
        final var combat = new RecursiveCombat(List.of(9, 2, 6, 3, 1), List.of(5, 8, 4, 7, 10));
        combat.play();
        assertEquals(2, combat.winner().id());
        assertEquals(291, combat.winner().score());
        assertEquals(17, combat.rounds());
    }
}