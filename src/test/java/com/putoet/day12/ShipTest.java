package com.putoet.day12;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    private static final List<CourseDirective> directives = ResourceLines.list("/day12.txt").stream()
            .map(CourseDirective::of)
            .collect(Collectors.toList());

    @Test
    void distance() {

        final Ship ship = new Ship();
        directives.forEach(ship::accept);
        assertEquals(25, ship.distance());
    }

    @Test
    void forward() {
        final Ship ship = new Ship();
        final WayPoint wayPoint = new WayPoint();

        for (CourseDirective directive : directives) {
            if (directive.command() == Command.FORWARD)
                ship.forward(directive, wayPoint);
            else
                wayPoint.accept(directive);
        }

        assertEquals(286, ship.distance());
    }
}