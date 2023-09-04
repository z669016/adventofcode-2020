package com.putoet.day11;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day11 {
    public static void main(String[] args) {
        final var seats = new Seats(new Grid(GridUtils.of(ResourceLines.list("/day11.txt"))));
        Timer.run(() -> part1(seats));
        Timer.run(() -> part2(seats));
    }

    private static void part1(Seats prev) {
        var next = prev.nextAdjacent();
        while(!GridUtils.gridEquals(prev.grid().grid(), next.grid().grid())) {
            prev = next;
            next = next.nextAdjacent();
        }

        System.out.println("Number of occupied seats is " + next.occupied());
    }

    private static void part2(Seats prev) {
        var next = prev.nextInSight();
        while(!GridUtils.gridEquals(prev.grid().grid(), next.grid().grid())) {
            prev = next;
            next = next.nextInSight();
        }

        System.out.println("Number of occupied seats is " + next.occupied());
    }
}
