package com.putoet.day12;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day12 {
    public static void main(String[] args) {
        final var directives = ResourceLines.list("/day12.txt").stream()
                .map(CourseDirective::of)
                .toList();

        Timer.run(() -> part1(directives));
        Timer.run(() -> part2(directives));
    }

    private static void part1(List<CourseDirective> directives) {
        final var ship = new Ship();
        directives.forEach(ship);

        System.out.println("the Manhattan distance between that location and the ship's starting position is " + ship.distance());
    }

    private static void part2(List<CourseDirective> directives) {
        final var ship = new Ship();
        final var wayPoint = new WayPoint();

        for (var directive : directives) {
            if (directive.command() == Command.FORWARD)
                ship.forward(directive, wayPoint);
            else
                wayPoint.accept(directive);
        }

        System.out.println("the Manhattan distance between that location and the ship's starting position using waypoints is " + ship.distance());
    }
}
