package com.putoet.day20;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

record Tile(int id, Grid grid) {
    public Tile(int id, char[][] grid) {
        this(id, new Grid(grid));
    }

    public static List<Tile> tiles(@NotNull List<String> lines) {
        final var tiles = new ArrayList<Tile>();

        var tileLines = new ArrayList<String>();
        for (var line : lines) {
            if (line.isEmpty() && !tileLines.isEmpty()) {
                tiles.add(of(tileLines));
                tileLines = new ArrayList<>();
            }

            if (!line.isEmpty())
                tileLines.add(line);
        }
        if (!tileLines.isEmpty()) {
            tiles.add(of(tileLines));
        }

        return tiles;
    }

    private static final Pattern TILE_PATTERN = Pattern.compile("^Tile (\\d+):$");
    public static Tile of(@NotNull List<String> lines) {
        final var tileLines = new ArrayList<>(lines);
        final var matcher = TILE_PATTERN.matcher(tileLines.get(0));
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid tile definition: " + tileLines);
        }

        final var id = Integer.parseInt(matcher.group(1));
        tileLines.remove(0);
        final var grid = GridUtils.of(tileLines);

        return new Tile(id, grid);
    }

    public int width() {
        return grid.grid()[0].length;
    }

    public int height() {
        return grid.grid().length;
    }

    public String north() {
        final var tile = grid.grid();
        return String.valueOf(tile[0]);
    }

    public String south() {
        final var tile = grid.grid();
        return String.valueOf(tile[tile.length - 1]);
    }

    public String west() {
        final var tile = grid.grid();
        return column(tile, 0);
    }

    public String east() {
        final var tile = grid.grid();
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
        return this.north().equals(other.south());
    }

    public boolean matchEast(Tile other) {
        return this.east().equals(other.west());
    }

    public boolean matchSouth(Tile other) {
        return other.matchNorth(this);
    }

    public boolean matchWest(Tile other) {
        return other.matchEast(this);
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder();
        sb.append("Tile: ").append(id).append("\n");
        GridUtils.toList(grid.grid()).forEach(line -> sb.append(line).append("\n"));

        return sb.toString();
    }

    private static String column(char[][] tile, int x) {
        final var sb = new StringBuilder();
        for (char[] chars : tile) sb.append(chars[x]);
        return sb.toString();
    }
}
