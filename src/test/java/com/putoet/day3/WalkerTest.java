package com.putoet.day3;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.putoet.utilities.Point;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WalkerTest {
    private TreeArea treeArea;

    @BeforeEach
    void setup() {
        final List<String> lines = ResourceLines.list("/day3.txt");
        treeArea = TreeArea.of(lines);
    }

    @Test
    void walkAndCountTrees() {
        final Walker walker = new Walker();
        assertEquals(7, walker.walkAndCountTrees(Point.of(3, 1), treeArea));
    }
}