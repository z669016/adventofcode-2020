package com.putoet.day11;

import com.putoet.resources.ResourceLines;
import com.putoet.utilities.Grid;
import com.putoet.utilities.GridUtils;

public class Day11 {
    public static void main(String[] args) {
        final Seats seats = new Seats(new Grid(GridUtils.of(ResourceLines.list("/day11.txt"))));
        part1(seats);
        part2(seats);
    }

    private static void part1(Seats prev) {
        int count = 0;
        Seats next = prev.nextAdjacent();
        while(!GridUtils.gridEquals(prev.grid().grid(), next.grid().grid())) {
            count++;
            prev = next;
            next = next.nextAdjacent();
        }

        System.out.println("No seats have changed state after " + count + " 'next adjacent' steps.");
        System.out.println("Number of occupied seats is " + next.occupied());
    }

    private static void part2(Seats prev) {
        int count = 0;
        Seats next = prev.nextInSight();
        while(!GridUtils.gridEquals(prev.grid().grid(), next.grid().grid())) {
            count++;
            prev = next;
            next = next.nextInSight();
        }

        System.out.println("No seats have changed state after " + count + " 'next in sight' steps.");
        System.out.println("Number of occupied seats is " + next.occupied());
    }
}
