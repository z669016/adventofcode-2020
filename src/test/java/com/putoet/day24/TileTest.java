package com.putoet.day24;

import com.putoet.grid.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void visit() {
        final var tile = new Tile(Point.ORIGIN);

        assertEquals(Tile.Color.WHITE, tile.color());
        tile.flip();
        assertEquals(Tile.Color.BLACK, tile.color());
        tile.flip();
        assertEquals(Tile.Color.WHITE, tile.color());
        tile.flip();
        assertEquals(Tile.Color.BLACK, tile.color());

        assertEquals(3, tile.flipped());
    }
}