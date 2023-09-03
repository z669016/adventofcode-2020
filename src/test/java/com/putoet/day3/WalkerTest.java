package com.putoet.day3;

import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WalkerTest {
    private TreeArea treeArea;

    @BeforeEach
    void setup() {
        treeArea = TreeArea.of(ResourceLines.list("/day3.txt"));
    }

    @Test
    void walkAndCountTrees() {
        final var walker = new Walker();
        assertEquals(7, walker.walkAndCountTrees(Point.of(3, 1), treeArea));
    }
}