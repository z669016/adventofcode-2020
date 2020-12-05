package com.putoet.day5;

import com.putoet.resources.ResourceLines;
import com.putoet.utilities.Decoder;
import com.putoet.utilities.Point;

import java.util.HashSet;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

public class Day5 {
    public static void main(String[] args) {
        final Decoder<String, Point> boardingPassDecoder = new BoardingPassDecoder();
        final Set<Point> seatsUsed = ResourceLines.list("/day5.txt").stream()
                .map(boardingPassDecoder::decode)
                .collect(Collectors.toSet());

        part1(seatsUsed);
        part2(seatsUsed);
    }

    private static void part1(Set<Point> seatsUsed) {
        final Decoder<Point, Integer> seatDecoder = new SeatIDDecoder();
        final OptionalInt max = seatsUsed.stream()
                .mapToInt(seatDecoder::decode)
                .max();

        if (max.isEmpty())
            System.out.println("No seats used");
        else
            System.out.println("the highest seat ID on a boarding pass is " + max.getAsInt());
    }

    private static void part2(Set<Point> seatsUsed) {
        final Decoder<Point, Integer> seatDecoder = new SeatIDDecoder();

        final Set<Integer> seatsIDsUsed = seatsUsed.stream().
                map(seatDecoder::decode)
                .collect(Collectors.toSet());

        final OptionalInt mySeat = findEmptySeats(seatsUsed).stream()
                .filter(Day5::isNotFirstOrLastRow)
                .mapToInt(seatDecoder::decode)
                .filter(id -> prevAndNextSeatUsed(seatsIDsUsed, id))
                .findFirst();

        if (mySeat.isEmpty())
            System.out.println("My seat not found");
        else
            System.out.println("The empty seat has seatID " + mySeat.getAsInt());
    }

    private static boolean prevAndNextSeatUsed(Set<Integer> seatsIDsUsed, Integer id) {
        return seatsIDsUsed.contains(id + 1) && seatsIDsUsed.contains(id - 1);
    }

    private static boolean isNotFirstOrLastRow(Point point) {
        return point.y != 0 && point.y != 127;
    }

    private static Set<Point> findEmptySeats(Set<Point> seatsUsed) {
        final Set<Point> emptySeats = new HashSet<>();
        for (int y = 0; y < 128; y++) {
            for (int x = 0; x < 8; x++) {
                final Point seat = Point.of(x, y);
                if (!seatsUsed.contains(seat))
                    emptySeats.add(seat);
            }
        }
        return emptySeats;
    }
}
