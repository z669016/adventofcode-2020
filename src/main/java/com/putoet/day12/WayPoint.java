package com.putoet.day12;

import com.putoet.utilities.Point;

import java.util.function.Consumer;

public class WayPoint implements Consumer<CourseDirective> {
    private Point point = Point.of(10, 1);

    public Point point() { return point; }

    @Override
    public void accept(CourseDirective courseDirective) {
        assert courseDirective != null;

        point = switch (courseDirective.command()) {
            case NORTH -> point.add(Point.of(0, courseDirective.length()));
            case EAST -> point.add(Point.of(courseDirective.length(), 0));
            case SOUTH -> point.add(Point.of(0, -1 * courseDirective.length()));
            case WEST -> point.add(Point.of(-1 * courseDirective.length(), 0));
            case LEFT -> rotateLeft(point, courseDirective.length() / 90);
            case RIGHT -> rotateRight(point, courseDirective.length() / 90);
            default -> throw new IllegalArgumentException("Cannot forward waypoint");
        };
    }

    private Point rotateRight(Point point, int length) {
        while(length > 0) {
            point = Point.of(point.y, -1 * point.x);
            length--;
        }

        return point;
    }

    private Point rotateLeft(Point point, int length) {
        while(length > 0) {
            point = Point.of(-1 * point.y, point.x);
            length--;
        }

        return point;
    }
}
