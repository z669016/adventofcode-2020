package com.putoet.day15;

import java.util.List;

public class Day15 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        final Numbers numbers = new Numbers(List.of(0,12,6,13,20,1,17));
        System.out.println("the 2020th number spoken will be " + getNumber(numbers, 2020L));
    }

    private static void part2() {
        final Numbers numbers = new Numbers(List.of(0,12,6,13,20,1,17));
        System.out.println("the 30000000th number spoken will be " + getNumber(numbers, 30_000_000L));
    }

    public static long getNumber(Numbers numbers, long lastTurn) {
        long number = -1L;
        for (long turn = numbers.turns() + 1; turn <= lastTurn; turn++) {
            number = numbers.get();
        }

        return number;
    }
}
