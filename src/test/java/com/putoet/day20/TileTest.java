package com.putoet.day20;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TileTest {
    private static final List<String> LINES = List.of(
            "Tile 2311:",
            "..##.#..#.",
            "##..#.....",
            "#...##..#.",
            "####.#...#",
            "##.##.###.",
            "##...#.###",
            ".#.#.#..##",
            "..#....#..",
            "###...#.#.",
            "..###..##.");

    private static final List<String> OTHER_LINES = List.of(
            "Tile 9999:",
            "..###..##.",
            ".#..#....#",
            "....##..##",
            "####.#...#",
            ".#.##.####",
            "##...#.###",
            "##.#.#..#.",
            "..#....#..",
            ".##...#.##",
            "..##.#..#.");

    private static final Tile TILE = Tile.of(LINES);
    private static final Tile OTHER = Tile.of(OTHER_LINES);

    @Test
    void tiles() {
        final var tiles = Tile.tiles(ResourceLines.list("/day20.txt"));

        assertEquals(9, tiles.size());
        assertEquals(Set.of(1951, 2311, 3079, 2729, 1427, 2473, 2971, 1489, 1171),
                tiles.stream().mapToInt(Tile::id).boxed().collect(Collectors.toSet()));
    }

    @Test
    void north() {
        assertEquals("..##.#..#.", TILE.north());
    }

    @Test
    void south() {
        assertEquals("..###..##.", TILE.south());
    }

    @Test
    void west() {
        assertEquals(".#####..#.", TILE.west());
    }

    @Test
    void east() {
        assertEquals("...#.##...", TILE.east());
    }

    @Test
    void matchNorth() {
        assertTrue(TILE.matchNorth(OTHER));
    }

    @Test
    void matchEast() {
        assertTrue(TILE.matchEast(OTHER));
    }

    @Test
    void matchSouth() {
        assertTrue(TILE.matchSouth(OTHER));
    }

    @Test
    void matchWest() {
        assertTrue(TILE.matchWest(OTHER));
    }
}