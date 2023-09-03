package com.putoet.day2;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        final List<String> policies = ResourceLines.list("/day2.txt");

        Timer.run(() -> part1(policies));
        Timer.run(() -> part2(policies));
    }

    private static void part1(List<String> policies) {
        System.out.println("passwords valid according to SledRentalPlacePasswordPolicy: " + policies.stream()
                .filter(policy -> PasswordPolicy.of(policy, SledRentalPlacePasswordPolicy.class).isValid(Password.password(policy)))
                .count()
        );
    }

    private static void part2(List<String> policies) {
        System.out.println("passwords valid according to TobogganPasswordPolicy: " + policies.stream()
                .filter(policy -> PasswordPolicy.of(policy, TobogganPasswordPolicy.class).isValid(Password.password(policy)))
                .count()
        );
    }
}
