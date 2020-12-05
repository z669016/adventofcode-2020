package com.putoet.day3;

import com.putoet.utilities.GridUtils;
import com.putoet.utilities.Point;

import java.util.List;

public class TreeArea {
    private final char[][] grid;
    private final int width;

    private static final char TREE = '#';

    public TreeArea(char[][] grid) {
        assert grid != null;
        this.width = grid[0].length;

        for (char[] row : grid) assert row.length == width;

        this.grid = grid;
    }

    public char[][] grid() { return grid; }
    public int height() {return grid.length; }

    public boolean treeAt(Point point) {
        assert point.y < grid.length;

        final int x = point.x % width;
        return grid[point.y][x] == TREE;
    }

    public static TreeArea of(List<String> lines) {
        return new TreeArea(GridUtils.of(lines));
    }
}
