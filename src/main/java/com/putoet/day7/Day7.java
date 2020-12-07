package com.putoet.day7;

import com.putoet.resources.ResourceLines;

import java.util.Map;

public class Day7 {
    public static void main(String[] args) {
        final Map<String,Bag> bags = Bags.loadBags(ResourceLines.list("/day7.txt"));

        part1(bags);
        part2(bags);
    }

    private static void part1(Map<String, Bag> bags) {
        final Bag gold = bags.get("shiny gold");

        final long count = bags.values().stream()
                .filter(bag -> bag.contains(gold))
                .count();

        System.out.println("Number of bags eventually contain at least one shiny gold bag " + count);
    }

    private static void part2(Map<String, Bag> bags) {
        final Bag gold = bags.get("shiny gold");
        final long count = gold.count();

        System.out.println("Number of bags eventually contain at least one shiny gold bag " + count);
    }
}
