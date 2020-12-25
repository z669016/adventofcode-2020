package com.putoet.day24;

import com.putoet.grid.Point;

import java.util.Objects;

public class Tile {
    public enum Color {
        WHITE, BLACK
    };
    private final Point point;
    private int flipped;

    public Tile(Point point) {
        this.point = point;
    }
    public Tile(Tile tile) {
        this.point = tile.point;
        this.flipped = tile.flipped;
    }

    public Point point() { return point; }
    public int flipped() { return flipped; }
    public Color color() { return flipped % 2 == 0 ? Color.WHITE : Color.BLACK; }
    public void flip() {
        flipped++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile)) return false;
        Tile tile = (Tile) o;
        return point.equals(tile.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }

    @Override
    public String toString() {
        return point + " (" + flipped + ") " + color();
    }
}
