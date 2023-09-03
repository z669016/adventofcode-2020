package com.putoet.day3;

import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day3 {
    public static void main(String[] args) {
        final var treeArea = TreeArea.of(ResourceLines.list("/day3.txt"));

        Timer.run(() -> part1(treeArea));
        Timer.run(() -> part2(treeArea));
    }

    private static void part1(TreeArea treeArea) {
        System.out.println("You would encounter " + treesForSlope(treeArea, Point.of(3, 1)) + " trees.");
    }

    private static void part2(TreeArea treeArea) {
        final var slopes = List.of(
                Point.of(1, 1),
                Point.of(3, 1),
                Point.of(5, 1),
                Point.of(7, 1),
                Point.of(1, 2)
        );

        long multipliedTrees = slopes.stream()
                .mapToLong(slope -> treesForSlope(treeArea, slope))
                .reduce(1, (a, b) -> a * b);
        System.out.println("Multiplied trees " + multipliedTrees);
    }

    static int treesForSlope(TreeArea treeArea, Point slope) {
        return new Walker().walkAndCountTrees(slope, treeArea);
    }
}
