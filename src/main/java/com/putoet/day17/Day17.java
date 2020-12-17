package com.putoet.day17;

import com.putoet.resources.ResourceLines;

public class Day17 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        Grid3D grid = Grid3D.of(ResourceLines.list("/day17.txt"));
        for (int count = 0; count < 6; count++)
            grid = grid.evolve();

        System.out.println("The number of cubes left in the active state after the sixth cycle on the 3D grid  is " + grid.count(Grid3D.ACTIVE));
    }

    private static void part2() {
        Grid4D grid = Grid4D.of(ResourceLines.list("/day17.txt"));
        for (int count = 0; count < 6; count++)
            grid = grid.evolve();

        System.out.println("The number of cubes left in the active state after the sixth cycle on the 4D grid is " + grid.count(Grid4D.ACTIVE));
    }
}
