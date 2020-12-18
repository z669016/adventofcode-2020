package com.putoet.day18;

import com.putoet.resources.ResourceLines;

import java.util.List;
import java.util.stream.Collectors;

public class Day18 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day18.txt");
        part1(lines);
        part2(lines);
    }

    private static void part1(List<String> lines) {
        final List<Expression> expressions = lines.stream()
                .map(ExpressionBuilder::of)
                .collect(Collectors.toList());
        System.out.println("the sum of the resulting values is " + expressions.stream().mapToLong(Expression::get).sum());
    }

    private static void part2(List<String> lines) {
        final List<Expression> expressions = lines.stream()
                .map(ExpressionBuilderPlusPrecedence::of)
                .collect(Collectors.toList());
        System.out.println("the sum of the resulting values (+ precedence) is " + expressions.stream().mapToLong(Expression::get).sum());
    }
}
