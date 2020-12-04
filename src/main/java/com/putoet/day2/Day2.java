package com.putoet.day2;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        final List<String> policies = ResourceLines.list("/day2.txt");
        System.out.println("passwords valid according to SledRentalPlacePasswordPolicy: " + policies.stream()
                .filter(policy -> SledRentalPlacePasswordPolicy.of(policy).isValid(Password.password(policy)))
                .count()
        );
    }

    private static void part2() {
        final List<String> policies = ResourceLines.list("/day2.txt");
        System.out.println("passwords valid according to TobogganPasswordPolicy: " + policies.stream()
                .filter(policy -> TobogganPasswordPolicy.of(policy).isValid(Password.password(policy)))
                .count()
        );
    }
}
