package com.putoet.day20;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tile {
    private static final Pattern TILE_PATTERN = Pattern.compile("^Tile (\\d+):$");
    private final int id;
    private final Grid grid;

    public Tile(int id, char[][] grid) {
        this.id = id;
        this.grid = new Grid(grid);
    }

    private static String column(char[][] tile, int x) {
        final StringBuilder sb = new StringBuilder();
        for (char[] chars : tile) sb.append(chars[x]);
        return sb.toString();
    }

    public static List<Tile> tiles(List<String> lines) {
        assert lines != null;

        final List<Tile> tiles = new ArrayList<>();

        List<String> tileLines = new ArrayList<>();
        for (String line : lines) {
            if (line.length() == 0 && tileLines.size() > 0) {
                tiles.add(of(tileLines));
                tileLines = new ArrayList<>();
            }

            if (line.length() > 0)
                tileLines.add(line);
        }
        if (tileLines.size() > 0) {
            tiles.add(of(tileLines));
        }

        return tiles;
    }

    public static Tile of(List<String> lines) {
        assert lines != null;

        final List<String> tileLines = new ArrayList<>(lines);
        final Matcher matcher = TILE_PATTERN.matcher(tileLines.get(0));
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid tile definition: " + tileLines);
        }

        final int id = Integer.parseInt(matcher.group(1));
        tileLines.remove(0);
        final char[][] grid = GridUtils.of(tileLines);

        return new Tile(id, grid);
    }

    public int id() {
        return id;
    }

    public Grid grid() {
        return grid;
    }

    public int width() {
        return grid.grid()[0].length;
    }

    public int height() {
        return grid.grid().length;
    }

    public String north() {
        final char[][] tile = grid.grid();
        return String.valueOf(tile[0]);
    }

    public String south() {
        final char[][] tile = grid.grid();
        return String.valueOf(tile[tile.length - 1]);
    }

    public String west() {
        final char[][] tile = grid.grid();
        return column(tile, 0);
    }

    public String east() {
        final char[][] tile = grid.grid();
        return column(tile, tile[0].length - 1);
    }

    public Tile flipHorizontally() {
        return new Tile(id, GridUtils.flipHorizontally(grid.grid()));
    }

    public Tile flipVertically() {
        return new Tile(id, GridUtils.flipHorizontally(grid.grid()));
    }

    public Tile rotate() {
        return new Tile(id, GridUtils.rotate(grid.grid()));
    }

    public boolean matchNorth(Tile other) {
        if (this.id == other.id)
            throw new IllegalStateException("You cannot match a tile with itself");

        return this.north().equals(other.south());
    }

    public boolean matchEast(Tile other) {
        if (this.id == other.id)
            throw new IllegalStateException("You cannot match a tile with itself");

        return this.east().equals(other.west());
    }

    public boolean matchSouth(Tile other) {
        if (this.id == other.id)
            throw new IllegalStateException("You cannot match a tile with itself");

        return other.matchNorth(this);
    }

    public boolean matchWest(Tile other) {
        if (this.id == other.id)
            throw new IllegalStateException("You cannot match a tile with itself");

        return other.matchEast(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Tile: ").append(id).append("\n");
        GridUtils.toList(grid.grid()).forEach(line -> sb.append(line).append("\n"));

        return sb.toString();
    }
}
