package com.putoet.day3;

import com.putoet.resources.ResourceLines;
import com.putoet.utilities.Point;

import java.util.List;

public class Day3 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day3.txt");
        final TreeArea treeArea = TreeArea.of(lines);

        part1(treeArea);
        part2(treeArea);
    }

    private static void part1(TreeArea treeArea) {
        System.out.println("You would encounter " + treesForSlope(treeArea, Point.of(3, 1)) + " trees.");
    }

    private static void part2(TreeArea treeArea) {
        final List<Point> slopes = List.of(
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

    public static int treesForSlope(TreeArea treeArea, Point slope) {
        final Walker walker = new Walker();
        return walker.walkAndCountTrees(slope, treeArea);
    }
}
