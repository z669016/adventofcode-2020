package com.putoet.day22;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombatTest {

    @Test
    void play() {
        final Combat combat = new Combat(List.of(9, 2, 6, 3, 1), List.of(5, 8, 4, 7, 10));
        combat.play();
        assertEquals(2, combat.winner().id());
        assertEquals(306, combat.winner().score());
        assertEquals(29, combat.rounds());
    }
}