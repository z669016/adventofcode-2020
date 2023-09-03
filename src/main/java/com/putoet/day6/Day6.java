package com.putoet.day6;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day6 {
    public static void main(String[] args) {
        final var allAnswers = GroupAnswers.of(ResourceLines.list("/day6.txt"));
        Timer.run(() -> part1(allAnswers));
        Timer.run(() -> part2(allAnswers));
    }

    private static void part1(List<GroupAnswers> allAnswers) {
        final var yesCount = allAnswers.stream()
                .mapToLong(GroupAnswers::yesCount)
                .sum();

        System.out.println("The sum of the counts is " +yesCount);
    }

    private static void part2(List<GroupAnswers> allAnswers) {
        final var yesCount = allAnswers.stream()
                .mapToLong(GroupAnswers::allYesses)
                .sum();

        System.out.println("The sum of all yes questions is " +yesCount);
    }
}
