package com.putoet.day7;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.Map;

public class Day7 {
    public static void main(String[] args) {
        final var bags = Bags.loadBags(ResourceLines.list("/day7.txt"));

        var goldBag = Timer.run(() -> part1(bags));
        Timer.run(() -> part2(goldBag));
    }

    private static Bag part1(Map<String, Bag> bags) {
        final var gold = bags.get("shiny gold");

        final var count = bags.values().stream()
                .filter(bag -> bag.contains(gold))
                .count();

        System.out.println("Number of bags eventually contain at least one shiny gold bag " + count);

        return gold;
    }

    private static void part2(Bag gold) {
        final var count = gold.count();

        System.out.println("Number of bags eventually contain at least one shiny gold bag " + count);
    }
}
