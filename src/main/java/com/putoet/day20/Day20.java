package com.putoet.day20;

import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;

import java.util.List;
import java.util.Set;

public class Day20 {
    public static void main(String[] args) {
        final List<Tile> tiles = Tile.tiles(ResourceLines.list("/day20.txt"));
        System.out.println("The puzzel has " + tiles.size() + " tiles");

        part1(tiles);
        part2(tiles);
    }

    private static void part1(List<Tile> tiles) {
        final Puzzler puzzler = new Puzzler(tiles);
        final Set<Tile> corners = puzzler.corners();

        System.out.println("if you multiply together the IDs of the four corner tiles, you get " +
                corners.stream().mapToLong(Tile::id).reduce(1L, (a, b) -> a * b));
    }

    private static void part2(List<Tile> tiles) {
        final Puzzler puzzler = new Puzzler(tiles);
        final Tile[][] grid = puzzler.grid();
        final char[][] image = Puzzler.paintSeaMonsters(Puzzler.createImage(grid));

        System.out.println("Number of # not part of a sea monster is " + GridUtils.count(image, '#'));
    }
}
