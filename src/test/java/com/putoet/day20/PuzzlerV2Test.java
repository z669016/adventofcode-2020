package com.putoet.day20;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PuzzlerV2Test {
    private PuzzlerV2 puzzler;
    private List<Tile> tiles;

    @BeforeEach
    void setup() {
        tiles = Tile.tiles(ResourceLines.list("/day20.txt"));
        puzzler = new PuzzlerV2(tiles);
    }

    @Test
    void tiles() {
        assertEquals(tiles.size() * 12, puzzler.tiles().size());
    }
}