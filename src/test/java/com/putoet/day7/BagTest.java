package com.putoet.day7;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    @Test
    void contains() {
        final Map<String,Bag> bags = Bags.loadBags(ResourceLines.list("/day7.txt"));
        final Bag gold = bags.get("shiny gold");

        final long count = bags.values().stream()
                .filter(bag -> bag.contains(gold))
                .count();
        assertEquals(4, count);
    }

    @Test
    void count() {
        final Map<String,Bag> bags = Bags.loadBags(ResourceLines.list("/day7-2.txt"));
        final Bag gold = bags.get("shiny gold");
        final long count = gold.count();
        assertEquals(126, count);
    }
}