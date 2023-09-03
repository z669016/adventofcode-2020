package com.putoet.day10;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day10 {
    public static void main(String[] args) {
        final var adapters = AdapterMatcher.of(ResourceLines.list("/day10.txt"));
        Timer.run(() -> part1(adapters));
        Timer.run(() -> part2(adapters));
    }

    private static void part1(List<Adapter> adapters) {
        final var diffOne = AdapterMatcher.difference(adapters, 1);
        final var diffThree = AdapterMatcher.difference(adapters, 3);
        System.out.println("the number of 1-jolt differences multiplied by the number of 3-jolt differences is " +
                           (diffOne * diffThree));
    }

    private static void part2(List<Adapter> adapters) {
        final var combinations = AdapterMatcher.combinations(adapters);
        System.out.println("the total number of distinct ways you can arrange the adapters to connect the charging outlet to your device is " + combinations);
    }
}
