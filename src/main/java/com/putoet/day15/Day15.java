package com.putoet.day15;

import com.putoet.utils.Timer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Day15 {
    public static void main(String[] args) {
        final var numbers = new Numbers(List.of(0,12,6,13,20,1,17));

        Timer.run(() -> part1(numbers));
        Timer.run(() -> part2(numbers));
    }

    private static void part1(Numbers numbers) {
        System.out.println("the 2020th number spoken will be " + getNumber(numbers, 2020L));
    }

    private static void part2(Numbers numbers) {
        System.out.println("the 30000000th number spoken will be " + getNumber(numbers, 30_000_000L));
    }

    static long getNumber(@NotNull Numbers numbers, long lastTurn) {
        var number = -1L;
        for (var turn = numbers.turns() + 1; turn <= lastTurn; turn++) {
            number = numbers.get();
        }

        return number;
    }
}
