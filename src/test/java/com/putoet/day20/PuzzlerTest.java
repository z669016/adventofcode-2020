package com.putoet.day20;

import com.putoet.grid.GridUtils;
import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PuzzlerTest {
    private Puzzler puzzler;
    private List<Tile> tiles;

    @BeforeEach
    void setup() {
        tiles = Tile.tiles(ResourceLines.list("/day20.txt"));
        puzzler = new Puzzler(tiles);
    }

    @Test
    void corners() {
        final Set<Tile> corners = puzzler.corners();

        assertEquals(Set.of(1951, 3079, 2971, 1171),
                corners.stream().mapToInt(Tile::id).boxed().collect(Collectors.toSet()));
        assertEquals(20899048083289L,
                corners.stream().mapToLong(Tile::id).reduce(1L, (a, b) -> a * b));
    }

    @Test
    void grid() {
        final Tile[][] tiles = puzzler.grid();
        for (int y = 0; y <tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                System.out.print(tiles[y][x].id());
                System.out.print(" ");
            }
            System.out.println();
        }

        // If this test doesn't throw an exception, a valid grid was created
    }

    @Test
    void createImage() {
        final Tile[][] tiles = puzzler.grid();
        final char[][] image = Puzzler.createImage(tiles);
        assertEquals(2, Puzzler.seaMonsterCount(image));
    }

    @Test
    void paintSeaMonsters() {
        final Tile[][] tiles = puzzler.grid();
        final char[][] image = Puzzler.paintSeaMonsters(Puzzler.createImage(tiles));

        assertEquals(273L, GridUtils.count(image, '#'));
    }
}