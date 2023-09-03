package com.putoet.day5;

import com.putoet.grid.Point;
import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Day5 {
    public static void main(String[] args) {
        final var boardingPassDecoder = new BoardingPassDecoder();
        final var seatsUsed = ResourceLines.list("/day5.txt").stream()
                .map(boardingPassDecoder::decode)
                .collect(Collectors.toSet());

        Timer.run(() -> part1(seatsUsed));
        Timer.run(() -> part2(seatsUsed));
    }

    private static void part1(Set<Point> seatsUsed) {
        final var seatDecoder = new SeatIDDecoder();
        final var max = seatsUsed.stream()
                .mapToInt(seatDecoder::decode)
                .max()
                .orElseThrow();

        System.out.println("the highest seat ID on a boarding pass is " + max);
    }

    private static void part2(Set<Point> seatsUsed) {
        final var seatDecoder = new SeatIDDecoder();

        final var seatsIDsUsed = seatsUsed.stream().
                map(seatDecoder::decode)
                .collect(Collectors.toSet());

        final var mySeat = findEmptySeats(seatsUsed).stream()
                .filter(Day5::isNotFirstOrLastRow)
                .mapToInt(seatDecoder::decode)
                .filter(id -> prevAndNextSeatUsed(seatsIDsUsed, id))
                .findFirst()
                .orElseThrow();

        System.out.println("The empty seat has seatID " + mySeat);
    }

    private static boolean prevAndNextSeatUsed(Set<Integer> seatsIDsUsed, Integer id) {
        return seatsIDsUsed.contains(id + 1) && seatsIDsUsed.contains(id - 1);
    }

    private static boolean isNotFirstOrLastRow(Point point) {
        return point.y() != 0 && point.y() != 127;
    }

    private static Set<Point> findEmptySeats(Set<Point> seatsUsed) {
        final var emptySeats = new HashSet<Point>();
        for (var y = 0; y < 128; y++) {
            for (var x = 0; x < 8; x++) {
                final var seat = Point.of(x, y);
                if (!seatsUsed.contains(seat))
                    emptySeats.add(seat);
            }
        }
        return emptySeats;
    }
}
