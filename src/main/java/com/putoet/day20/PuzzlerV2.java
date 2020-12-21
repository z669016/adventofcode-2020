package com.putoet.day20;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PuzzlerV2 {
    private final List<Tile> tiles;

    public PuzzlerV2(List<Tile> tiles) {
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
}
