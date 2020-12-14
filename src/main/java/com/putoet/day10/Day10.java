package com.putoet.day10;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day10 {
    public static void main(String[] args) {
        final List<Adapter> adapters = AdapterMatcher.of(ResourceLines.list("/day10.txt"));
        part1(adapters);
    }

    private static void part1(List<Adapter> adapters) {
        final int diffOne = AdapterMatcher.difference(adapters, 1);
        final int diffThree = AdapterMatcher.difference(adapters, 3);
        System.out.println("the number of 1-jolt differences multiplied by the number of 3-jolt differences is " +
                (diffOne * diffThree));
    }
}
