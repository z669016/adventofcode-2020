package com.putoet.day10;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day10 {
    public static void main(String[] args) {
        final List<Adapter> adapters = AdapterMatcher.of(ResourceLines.list("/day10.txt"));
        final Adapter device = AdapterMatcher.stack(adapters);
        part1(device);
    }

    private static void part1(Adapter device) {
        final int oneDifference = AdapterMatcher.differenceCount(device, 1);
        final int threeDifference = AdapterMatcher.differenceCount(device, 3);

        System.out.println("the number of 1-jolt differences multiplied by the number of 3-jolt differences is " +
                (oneDifference * threeDifference));
    }
}
