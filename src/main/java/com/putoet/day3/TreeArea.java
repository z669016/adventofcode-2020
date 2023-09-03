package com.putoet.day3;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.List;

class TreeArea {
    private final Grid grid;

    private static final char TREE = '#';

    private TreeArea(@NotNull Grid grid) {
        this.grid = grid;
    }

    public char[][] grid() { return grid.grid(); }
    public int height() {return grid.height(); }

    public boolean treeAt(@NotNull Point point) {
        assert point.y() >= 0 && point.y() < grid.height();

        final var x = point.x() % grid.grid()[point.y()].length;
        return grid.get(x, point.y()) == TREE;
    }

    public static TreeArea of(@NotNull List<String> lines) {
        return new TreeArea(new Grid(GridUtils.of(lines)));
    }
}
