package com.putoet.day12;

import com.putoet.resources.ResourceLines;

import java.util.List;
import java.util.stream.Collectors;

public class Day12 {
    public static void main(String[] args) {
        final List<CourseDirective> directives = ResourceLines.list("/day12.txt").stream()
                .map(CourseDirective::of)
                .collect(Collectors.toList());

        part1(directives);
        part2(directives);
    }

    private static void part1(List<CourseDirective> directives) {
        final Ship ship = new Ship();
        directives.forEach(ship);

        System.out.println("the Manhattan distance between that location and the ship's starting position is " + ship.distance());
    }

    private static void part2(List<CourseDirective> directives) {
        final Ship ship = new Ship();
        final WayPoint wayPoint = new WayPoint();

        for (CourseDirective directive : directives) {
            if (directive.command() == Command.FORWARD)
                ship.forward(directive, wayPoint);
            else
                wayPoint.accept(directive);
        }

        System.out.println("the Manhattan distance between that location and the ship's starting position using waypoints is is " + ship.distance());
    }

}
