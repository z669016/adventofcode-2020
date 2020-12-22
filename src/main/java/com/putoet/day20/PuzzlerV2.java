package com.putoet.day20;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PuzzlerV2 {
    private final List<Tile> tiles;
    private final int size;

    public PuzzlerV2(List<Tile> tiles) {
        size = (int) Math.sqrt(tiles.size());
        assert size * size == tiles.size();

        this.tiles = tiles.stream()
                .map(tile -> {
                    List<Tile> list = List.of(tile, tile.flipHorizontally(), tile.flipVertically());
                    final List<Tile> allPossiblePositions = new ArrayList<>(list);
                    for (int x = 0; x < 3; x++) {
                         list = rotate(list);
                         allPossiblePositions.addAll(list);
                    }
                    return allPossiblePositions;
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static List<Tile> rotate(List<Tile> tiles) {
        return tiles.stream()
                .map(Tile::rotate)
                .collect(Collectors.toList());
    }

    public List<Tile> tiles() { return Collections.unmodifiableList(tiles); }

//    public Tile[][] grid() {
//        int pos = 0;
//        Tile[][] grid = new Tile[size][size];
//        for (int offset = 0; offset < tiles().size(); offset++) {
//            grid[0][0] = tiles.get(offset);
//
//            int prev = pos;
//            for (int next = 0; next < tiles.size(); next++) {
//                if (next != prev) {
//                    if (grid[y(prev)][x(prev)].matchEast(tiles.get(next))) {
//
//                    }
//                }
//            }
//        }
//    }
//
    private int x(int pos) { return pos % size;}
    private int y(int pos) { return pos / size;}
}
