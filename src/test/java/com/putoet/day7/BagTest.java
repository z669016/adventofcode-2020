package com.putoet.day7;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    @Test
    void contains() {
        final var bags = Bags.loadBags(ResourceLines.list("/day7.txt"));
        final var gold = bags.get("shiny gold");

        final var count = bags.values().stream()
                .filter(bag -> bag.contains(gold))
                .count();
        assertEquals(4, count);
    }

    @Test
    void count() {
        final var bags = Bags.loadBags(ResourceLines.list("/day7-2.txt"));
        final var gold = bags.get("shiny gold");
        final var count = gold.count();
        assertEquals(126, count);
    }
}