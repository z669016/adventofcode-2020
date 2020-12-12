package com.putoet.day12;

public enum Command {
    FORWARD,
    NORTH,
    EAST,
    SOUTH,
    WEST,
    LEFT,
    RIGHT;

    public static Command of(String command) {
        assert command != null;
        return switch (command) {
            case "F" -> FORWARD;
            case "N" -> NORTH;
            case "E" -> EAST;
            case "S" -> SOUTH;
            case "W" -> WEST;
            case "L" -> LEFT;
            case "R" -> RIGHT;
            default -> throw new IllegalArgumentException("Invalid command '" + command + "'");
        };
    }
}
