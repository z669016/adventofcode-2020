package utilities;

public enum DirectionTurn {
    LEFT,
    STRAIGHT,
    RIGHT;

    public DirectionTurn next() {
        return switch (this) {
            case LEFT -> STRAIGHT;
            case STRAIGHT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
