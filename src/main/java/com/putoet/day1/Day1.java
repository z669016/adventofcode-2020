package com.putoet.day1;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Day1 {
    public static void main(String[] args) {
        final var values = new HashSet<>(ResourceLines.list("/day1.txt", Integer::parseInt));

        Timer.run(() -> part1(values));
        Timer.run(() -> part2(values));
    }

    private static void part1(Set<Integer> values) {
        for (var first : values) {
            if (values.contains(2020 - first)) {
                final var b = 2020 - first;
                System.out.printf("Values are %d and %d, multiplying to %d%n", first, b, first * b);
                return;
            }
        }
    }

    private static void part2(Set<Integer> values) {
        for (var first : values) {
            for (var second : values) {
                if (Objects.equals(second, first)) continue;
                if ((first == 2020 - first - second) || (second == 2020 - first - second)) continue;

                if (values.contains(2020 - first - second)) {
                    final var c = 2020 - first - second;
                    System.out.printf("Values are %d and %d and %d, multiplying to %d%n", first, second, c, first * second * c);
                    return;
                }
            }
        }
    }
}
