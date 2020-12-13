package com.putoet.day12;

import com.putoet.grid.Point;
import lombok.Data;

import java.util.function.Consumer;

@Data
public class Ship implements Consumer<CourseDirective> {
    private Point location = Point.ORIGIN;
    private int direction = 0;

    public void setDirection(int direction) {
        this.direction = (360 + direction) % 360;
    }

    public int distance() {
        return location.manhattanDistance(Point.ORIGIN);
    }

    @Override
    public String toString() {
        return String.format("[%s, %d]", location.toString(), direction);
    }

    @Override
    public void accept(CourseDirective courseDirective) {
        move(courseDirective.command(), courseDirective.length());
    }

    public void forward(CourseDirective courseDirective, WayPoint wayPoint) {
        assert courseDirective != null && wayPoint != null;

        if (courseDirective.command() != Command.FORWARD)
            throw new IllegalArgumentException("cannot forward " + courseDirective);

        int count = courseDirective.length();
        while (count > 0) {
            location = location.add(wayPoint.point());
            count--;
        }
    }

    private void move(Command command, int length) {
        switch (command) {
            case NORTH -> setLocation(getLocation().add(Point.of(0, length)));
            case EAST -> setLocation(getLocation().add(Point.of(length, 0)));
            case SOUTH -> setLocation(getLocation().add(Point.of(0, -1 * length)));
            case WEST -> setLocation(getLocation().add(Point.of(-1 * length, 0)));
            case LEFT -> setDirection(getDirection() - length);
            case RIGHT -> setDirection(getDirection() + length);
            case FORWARD -> move(direction(), length);
        }
    }

    private Command direction() {
        return switch (getDirection()) {
            case 0 -> Command.EAST;
            case 90 -> Command.SOUTH;
            case 180 -> Command.WEST;
            case 270 -> Command.NORTH;
            default -> throw new IllegalArgumentException("Invalid ship direction " + getDirection());
        };
    }
}
