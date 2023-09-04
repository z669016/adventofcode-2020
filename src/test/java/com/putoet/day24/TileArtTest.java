package com.putoet.day24;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileArtTest {

    @Test
    void next() {
        final var visitor = new TileVisitor();
        visitor.visit(ResourceLines.list("/day24.txt"));

        var tileArt = new TileArt(visitor.tiles());
        assertEquals(10, tileArt.blackCount());
        for (var i = 0; i < 100; i++){
            tileArt = tileArt.next();

            if (i == 0) assertEquals(15, tileArt.blackCount());
            if (i == 1) assertEquals(12, tileArt.blackCount());
            if (i == 2) assertEquals(25, tileArt.blackCount());
            if (i == 3) assertEquals(14, tileArt.blackCount());
        }

        assertEquals(2208, tileArt.blackCount());
    }
}