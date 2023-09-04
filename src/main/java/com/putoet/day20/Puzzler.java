package com.putoet.day20;

import com.putoet.grid.GridUtils;
import com.putoet.grid.Point;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/*
 * The Puzzler class is a helper to solve the puzzle. It finds the
 * corner tiles, and can put all tiles in a valid position.
 */
class Puzzler {
    public static final char[][] SEA_MONSTER = GridUtils.of(List.of(
            "                  # ",
            "#    ##    ##    ###",
            " #  #  #  #  #  #   "
    ));

    private final Map<Integer, Tile> tiles;
    private final Map<Integer, List<Integer>> matches;
    private Set<Tile> corners;

    public Puzzler(@NotNull List<Tile> tiles) {
        this.tiles = tiles.stream().collect(Collectors.toMap(Tile::id, tile -> tile));
        this.matches = matches(this.tiles);
    }

    private static Map<Integer, List<Integer>> matches(Map<Integer, Tile> tiles) {
        final var matches = new HashMap<Integer, List<Integer>>();

        for (var id : tiles.keySet()) {
            final var tileMatch = tileMatch(id, tiles);
            matches.put(id, tileMatch);
        }
        return matches;
    }

    private static List<Integer> tileMatch(Integer id, Map<Integer, Tile> tiles) {
        final var tileMatch = new ArrayList<Integer>();

        final var tile = tiles.get(id);
        for (var otherId : tiles.keySet()) {
            if (!Objects.equals(id, otherId)) {
                final var other = tiles.get(otherId);
                if (matchRotated(tile, other))
                    tileMatch.add(otherId);
            }
        }

        return tileMatch;
    }

    private static boolean matchRotated(Tile a, Tile b) {
        for (var i = 0; i < 4; i++) {
            if (match(a, b) || matchFlipped(a, b)) return true;
            b = b.rotate();
        }
        return false;
    }

    private static boolean matchFlipped(Tile a, Tile b) {
        var flipped = b.flipHorizontally();
        if (match(a, flipped)) return true;

        flipped = b.flipVertically();
        return match(a, flipped);
    }

    private static boolean match(Tile a, Tile b) {
        return a.matchNorth(b) || a.matchEast(b) || a.matchSouth(b) || a.matchWest(b);
    }

    /*
     * Create an image from a grid of tiles (i.e. remove all tile borders and compile a single image) and flip/rotate
     * the image into such a position that it contains sea monsters.
     */
    public static char[][] createImage(Tile[][] tiles) {
        final var height = tiles.length * tiles[0][0].height() - tiles.length * 2;
        final var width = tiles[0].length * tiles[0][0].width() - tiles[0].length * 2;

        final var tileHeight = tiles[0][0].height() - 2;
        final var tileWidth = tiles[0][0].width() - 2;

        final var image = new char[height][width];
        for (var y = 0; y < tiles.length; y++) {
            for (var x = 0; x < tiles[y].length; x++) {
                final var tile = tiles[y][x];
                final var tileGrid = tile.grid().grid();

                for (var idy = 0; idy < tileHeight; idy++) {
                    if (tileWidth >= 0)
                        System.arraycopy(tileGrid[idy + 1], 1, image[y * tileHeight + idy], x * tileWidth, tileWidth);
                }
            }
        }

        return ceateImageContainingSeaMonsters(image);
    }

    private static char[][] ceateImageContainingSeaMonsters(char[][] image) {
        var rotate = 0;
        while (seaMonsterCount(image) == 0 && rotate < 4) {
            image = GridUtils.flipHorizontally(image);
            if (seaMonsterCount(image) > 0)
                return image;

            image = GridUtils.flipVertically(GridUtils.flipHorizontally(image));
            if (seaMonsterCount(image) > 0)
                return image;

            image = GridUtils.rotate(GridUtils.flipVertically(image));
            rotate++;
        }
        if (seaMonsterCount(image) > 0)
            return image;

        throw new IllegalStateException("Cound not create an image containing sea monsters");
    }

    /*
     * Count the number of sea monsters in the water
     */
    public static int seaMonsterCount(char[][] image) {
        var seaMonsterCount = 0;
        for (var y = 0; y < image.length - SEA_MONSTER.length; y++) {
            for (var x = 0; x < image[y].length - SEA_MONSTER[0].length; x++) {
                if (seaMonsterAt(image, x, y))
                    seaMonsterCount++;
            }
        }
        return seaMonsterCount;
    }

    private static boolean seaMonsterAt(char[][] image, int x, int y) {
        for (var sy = 0; sy < SEA_MONSTER.length; sy++) {
            for (var sx = 0; sx < SEA_MONSTER[sy].length; sx++) {
                if (SEA_MONSTER[sy][sx] == '#' && image[y + sy][x + sx] != '#')
                    return false;
            }
        }
        return true;
    }

    /*
     * Copy the image and paint the sea monsters in the copied image
     */
    public static char[][] paintSeaMonsters(char[][] image) {
        final var paintedImage = GridUtils.copy(image);

        for (var y = 0; y < image.length - SEA_MONSTER.length; y++) {
            for (var x = 0; x < image[y].length - SEA_MONSTER[0].length; x++) {
                if (seaMonsterAt(image, x, y)) {
                    paintSeaMonsterAt(paintedImage, x, y);
                }
            }
        }

        return paintedImage;
    }

    private static void paintSeaMonsterAt(char[][] image, int x, int y) {
        for (var sy = 0; sy < SEA_MONSTER.length; sy++) {
            for (var sx = 0; sx < SEA_MONSTER[sy].length; sx++) {
                if (SEA_MONSTER[sy][sx] == '#')
                    image[y + sy][x + sx] = 'O';
            }
        }
    }

    /*
     * Returns the corner tiles (i.e. those tiles taht have at most 2 neighbours).
     */
    public Set<Tile> corners() {
        if (corners == null)
            corners = matches.entrySet().stream()
                    .filter(entry -> entry.getValue().size() == 2)
                    .map(entry -> tiles.get(entry.getKey()))
                    .collect(Collectors.toSet());

        return corners;
    }

    /*
     * Place the tiles in a valid grid (i.e. all sides match). This doesn't mean the grid is in the right position
     * to find sea monsters yet. It might still be required to rotate and/or flip.
     */
    public Tile[][] grid() {
        final var size = (int) Math.sqrt(tiles.size());
        assert size * size == tiles.size();

        final var grid = new Tile[size][size];
        grid[0][0] = positionForNorthEastFit(corners().stream().findFirst().orElseThrow());

        for (var y = 0; y < size; y++) {
            for (var x = 0; x < size; x++) {
                if (y == 0 && x == 0)
                    continue;

                final var prev = (x == 0) ? grid[y - 1][0] : grid[y][x - 1];
                final var next = (x == 0) ? southOf(prev) : eastOf(prev);

                if (next.isEmpty())
                    throw new IllegalStateException("No tile found to put east of " + prev + " for " + Point.of(x, y));

                if (x > 0)
                    grid[y][x] = positionForWestFit(prev, next.get());
                else
                    grid[y][x] = positionForNorthFit(prev, next.get());
            }
        }

        return grid;
    }

    private Tile positionForNorthEastFit(Tile tile) {
        var east = eastOf(tile);
        var rotate = 0;
        while (east.isEmpty() && rotate < 4) {
            tile = tile.flipHorizontally();
            east = eastOf(tile);
            if (east.isPresent())
                return tile;

            tile = tile.flipHorizontally().flipVertically();
            east = eastOf(tile);
            if (east.isPresent())
                return tile;

            tile = tile.flipVertically().rotate();
            rotate++;
        }

        if (east.isEmpty())
            throw new IllegalStateException("Cannot position tile " + tile.id() + " for a neighbour at the east");

        var south = southOf(tile);
        if (south.isEmpty())
            tile = tile.flipHorizontally();

        south = southOf(tile);
        if (south.isPresent())
            return tile;

        throw new IllegalStateException("Cannot position tile " + tile.id() + " for a neighbour at the east and south");
    }

    private Tile positionForWestFit(Tile prev, Tile next) {
        var rotate = 0;
        while (!next.matchWest(prev) && rotate < 4) {
            next = next.flipHorizontally();
            if (next.matchWest(prev))
                return next;

            next = next.flipHorizontally().flipVertically();
            if (next.matchWest(prev))
                return next;

            next = next.flipVertically().rotate();
            rotate++;
        }

        if (next.matchWest(prev))
            return next;

        throw new IllegalStateException("Cannot position tile " + next.id() + " for a neighbour at the west");
    }

    private Tile positionForNorthFit(Tile prev, Tile next) {
        var rotate = 0;
        while (!next.matchNorth(prev) && rotate < 4) {
            next = next.flipHorizontally();
            if (next.matchNorth(prev))
                return next;

            next = next.flipHorizontally().flipVertically();
            if (next.matchNorth(prev))
                return next;

            next = next.flipVertically().rotate();
            rotate++;
        }

        if (next.matchNorth(prev))
            return next;

        throw new IllegalStateException("Cannot position tile " + next.id() + " for a neighbour at the north");
    }

    private Optional<Tile> eastOf(Tile tile) {
        return forDirection(tile, (Tile a, Tile b) -> a.matchEast(b) ||
                a.matchEast(b.flipHorizontally()) ||
                a.matchEast(b.flipVertically()));
    }

    private Optional<Tile> southOf(Tile tile) {
        return forDirection(tile, (Tile a, Tile b) -> a.matchSouth(b) ||
                a.matchSouth(b.flipHorizontally()) ||
                a.matchSouth(b.flipVertically()));
    }

    private Optional<Tile> forDirection(Tile tile, BiFunction<Tile, Tile, Boolean> test) {
        final var neighbours = matches.get(tile.id());

        for (var nextTo : neighbours) {
            var neighbour = tiles.get(nextTo);

            for (var i = 0; i < 4; i++) {
                if (test.apply(tile, neighbour))
                    return Optional.of(neighbour);

                neighbour = neighbour.rotate();
            }
        }

        return Optional.empty();
    }
}
