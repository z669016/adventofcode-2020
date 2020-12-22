package com.putoet.day22;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void lost() {
        final Player player = new Player(0, List.of());
        assertTrue(player.lost());
    }

    @Test
    void next() {
        final Player player = new Player(0, List.of(1, 2, 3));
        assertTrue(player.hasNext());
        assertEquals(1, player.next());
        assertTrue(player.hasNext());
        assertEquals(2, player.next());
        assertTrue(player.hasNext());
        assertEquals(3, player.next());
        assertFalse(player.hasNext());
    }

    @Test
    void add() {
        final Player player = new Player(0, List.of());
        player.add(2);
        player.add(1);
        assertEquals(2, player.next());
        assertEquals(1, player.next());
    }

    @Test
    void score() {
        final Player player = new Player(0, List.of(9, 2, 6, 3, 1));
        assertEquals(78, player.score());
    }
}