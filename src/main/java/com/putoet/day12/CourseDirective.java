package com.putoet.day12;

import com.putoet.grid.Point;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseDirective {
    private final Command command;
    private final int length;

    protected CourseDirective(String command, int length) {
        this.command = Command.of(command);
        this.length = length;
    }

    public Command command() { return command; }
    public int length() { return length; }

    private static void moveShip(Ship ship, Command command, int length) {
        switch (command) {
            case NORTH -> ship.setLocation(ship.getLocation().add(Point.of(0, length)));
            case EAST -> ship.setLocation(ship.getLocation().add(Point.of(length, 0)));
            case SOUTH -> ship.setLocation(ship.getLocation().add(Point.of(0, -1 * length)));
            case WEST -> ship.setLocation(ship.getLocation().add(Point.of(-1 * length, 0)));
            case LEFT -> ship.setDirection(ship.getDirection() - length);
            case RIGHT -> ship.setDirection(ship.getDirection() + length);
            case FORWARD -> moveShip(ship, direction(ship), length);
        }
    }

    private static Command direction(Ship ship) {
        return switch (ship.getDirection()) {
            case 0 -> Command.EAST;
            case 90 -> Command.SOUTH;
            case 180 -> Command.WEST;
            case 270 -> Command.NORTH;
            default -> throw new IllegalArgumentException("Invalid ship direction " + ship.getDirection());
        };
    }

    private static final Pattern COURSE_DIRECTIVE = Pattern.compile("([NESWLRF])(\\d+)");
    public static CourseDirective of(String line) {
        assert line != null;

        final Matcher matcher = COURSE_DIRECTIVE.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid course directive " + line);

        return new CourseDirective(matcher.group(1), Integer.parseInt(matcher.group(2)));
    }

    @Override
    public String toString() {
        return command.toString() + "-" + length;
    }
}
