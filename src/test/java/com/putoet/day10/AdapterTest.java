package com.putoet.day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdapterTest {

    @Test
    void stack() {
        final Adapter one = new Adapter(3);
        final Adapter two = new Adapter(5);
        final Adapter three = new Adapter(10);

        assertThrows(IllegalArgumentException.class, () -> one.stack(two));
        assertThrows(IllegalArgumentException.class, () -> three.stack(two));
    }

    @Test
    void difference() {
        final Adapter one = new Adapter(3);
        final Adapter two = new Adapter(5);

        assertEquals(3, one.difference());
        two.stack(one);
        assertEquals(2, two.difference());
    }

    @Test
    void prev() {
        final Adapter one = new Adapter(1);
        final Adapter two = new Adapter(2);
        final Adapter three = new Adapter(3);
        final Adapter four = new Adapter(4);
        final Adapter five = new Adapter(5);

        five.stack(four).stack(three).stack(two).stack(one);

        assertEquals(four, Adapter.prev(five, 1).get());
        assertEquals(three, Adapter.prev(five, 2).get());
        assertEquals(two, Adapter.prev(five, 3).get());
        assertEquals(one, Adapter.prev(five, 4).get());
    }
}