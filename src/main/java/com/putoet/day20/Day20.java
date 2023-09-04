package com.putoet.day20;

import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day20 {
    public static void main(String[] args) {
        final var tiles = Tile.tiles(ResourceLines.list("/day20.txt"));

        Timer.run(() -> part1(tiles));
        Timer.run(() -> part2(tiles));
    }

    private static void part1(List<Tile> tiles) {
        final var puzzler = new Puzzler(tiles);
        final var corners = puzzler.corners();

        System.out.println("if you multiply together the IDs of the four corner tiles, you get " +
                corners.stream().mapToLong(Tile::id).reduce(1L, (a, b) -> a * b));
    }

    private static void part2(List<Tile> tiles) {
        final var puzzler = new Puzzler(tiles);
        final var grid = puzzler.grid();
        final var image = Puzzler.paintSeaMonsters(Puzzler.createImage(grid));

        System.out.println("Number of # not part of a sea monster is " + GridUtils.count(image, '#'));
    }
}
