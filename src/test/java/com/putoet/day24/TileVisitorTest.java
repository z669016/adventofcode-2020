package com.putoet.day24;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TileVisitorTest {

    @Test
    void visit() {
        final TileVisitor visitor = new TileVisitor();

        visitor.visit("nwwswee");
        assertEquals(1, visitor.blackCount());
        assertEquals(1, visitor.flipCount(1));
    }

    @Test
    void sample() {
        final TileVisitor visitor = new TileVisitor();
        visitor.visit(ResourceLines.list("/day24.txt"));
        assertEquals(10, visitor.blackCount());
        assertEquals(5, visitor.flipCount(2));

    }
}