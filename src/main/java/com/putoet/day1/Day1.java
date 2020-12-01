package com.putoet.day1;

import com.putoet.resources.ResourceLines;

import java.util.Set;
import java.util.stream.Collectors;

public class Day1 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        final Set<Integer> values = ResourceLines.list("/day1.txt").stream()
                .map(Integer::valueOf)
                .collect(Collectors.toSet());

        for (Integer first : values) {
            if (values.contains(2020 - first)) {
                final int a = first;
                final int b = 2020 - first;
                System.out.printf("Values are %d and %d, multiplying to %d%n", a, b, a * b);
                return;
            }
        }
    }

    private static void part2() {
        final Set<Integer> values = ResourceLines.list("/day1.txt").stream()
                .map(Integer::valueOf)
                .collect(Collectors.toSet());

        for (Integer first : values) {
            for (Integer second : values) {
                if (second == first) continue;
                if ((first == 2020 - first - second) || (second == 2020 - first - second)) continue;;

                if (values.contains(2020 - first - second)) {
                    final int a = first;
                    final int b = second;
                    final int c = 2020 - first - second;
                    System.out.printf("Values are %d and %d and %d, multiplying to %d%n", a, b, c, a * b * c);
                    return;
                }
            }
        }
    }
}
