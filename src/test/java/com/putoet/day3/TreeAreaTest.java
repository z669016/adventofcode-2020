package com.putoet.day3;

import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TreeAreaTest {
    private TreeArea treeArea;

    @BeforeEach
    void setup() {
        final var lines = ResourceLines.list("/day3.txt");
        treeArea = TreeArea.of(lines);
    }

    @Test
    void of() {
        assertNotNull(treeArea);
        assertNotNull(treeArea.grid());
        assertEquals(treeArea.height(), 11);
        assertNotNull(treeArea.grid()[0]);
        assertEquals(treeArea.grid()[0].length, 11);
    }

    @Test
    void treeAt() {
        assertTrue(treeArea.treeAt(Point.of(6, 2)));
        assertTrue(treeArea.treeAt(Point.of(12, 4)));
        assertTrue(treeArea.treeAt(Point.of(15, 5)));
        assertTrue(treeArea.treeAt(Point.of(21, 7)));
        assertTrue(treeArea.treeAt(Point.of(24, 8)));
        assertTrue(treeArea.treeAt(Point.of(27, 9)));
        assertTrue(treeArea.treeAt(Point.of(30, 10)));
    }
}