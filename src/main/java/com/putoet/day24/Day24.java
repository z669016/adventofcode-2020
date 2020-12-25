package com.putoet.day24;

import com.putoet.resources.ResourceLines;

import java.util.Collection;
import java.util.List;

public class Day24 {
    public static void main(String[] args) {
        final List<String> routes = ResourceLines.list("/day24.txt");
        final Collection<Tile> tiles = part1(routes);

        part2(tiles);
    }

    private static void part2(Collection<Tile> tiles) {
        TileArt tileArt = new TileArt(tiles);

        for (int i = 0; i < 100; i++)
            tileArt = tileArt.next();

        System.out.println("Number of tiles that will be black after 100 days of tile art is " + tileArt.blackCount());
    }

    private static Collection<Tile> part1(List<String> routes) {
        final TileVisitor visitor = new TileVisitor();
        visitor.visit(routes);

        System.out.println("The number of tiles left with the black side up is " + visitor.blackCount());

        return visitor.tiles();
    }
}
