package com.putoet.day18;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day18 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day18.txt");
        Timer.run(() -> part1(lines));
        Timer.run(() -> part2(lines));
    }

    private static void part1(List<String> lines) {
        final var expressions = lines.stream()
                .map(ExpressionBuilder::of)
                .toList();
        System.out.println("the sum of the resulting values is " + expressions.stream().mapToLong(Expression::get).sum());
    }

    private static void part2(List<String> lines) {
        final var expressions = lines.stream()
                .map(ExpressionBuilderPlusPrecedence::of)
                .toList();
        System.out.println("the sum of the resulting values (+ precedence) is " + expressions.stream().mapToLong(Expression::get).sum());
    }
}
