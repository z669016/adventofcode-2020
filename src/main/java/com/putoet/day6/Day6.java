package com.putoet.day6;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day6 {
    public static void main(String[] args) {
        final List<GroupAnswers> allAnswers = GroupAnswers.of(ResourceLines.list("/day6.txt"));
        part1(allAnswers);
        part2(allAnswers);
    }

    private static void part1(List<GroupAnswers> allAnswers) {
        final long yesCount = allAnswers.stream()
                .mapToLong(GroupAnswers::yesCount)
                .sum();

        System.out.println("The sum of the counts is " +yesCount);
    }

    private static void part2(List<GroupAnswers> allAnswers) {
        final long yesCount = allAnswers.stream()
                .mapToLong(GroupAnswers::allYesses)
                .sum();

        System.out.println("The sum of all yes questions is " +yesCount);

        // 491 is too low
    }
}
