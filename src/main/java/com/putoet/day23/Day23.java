package com.putoet.day23;

public class Day23 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        final CupCircle circle = new CupCircle("284573961");

        for (int i = 0; i < 100; i++) {
            circle.nextCircle();
        }

        System.out.println("the labels on the cups after cup 1 are: " + circle.afterOne());
    }

    private static void part2() {
        final CupCircle circle = new CupCircle("284573961", 1_000_000);

        for (int idx = 0; idx < 10_000_000; idx++)
            circle.nextCircle();

        System.out.println("if you multiply their labels AFTER 1 together, you get " + circle.afterOneTwoValues());
    }
}
