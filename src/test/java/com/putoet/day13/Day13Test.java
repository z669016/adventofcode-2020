package com.putoet.day13;

import com.putoet.resources.ResourceLines;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class Day13Test {

    @Test
    void firstDeparture() {
        final List<String> puzzleInput = ResourceLines.list(("/day13.txt"));
        final int departureTime = Integer.parseInt(puzzleInput.get(0));
        final Pair<Integer,Integer> bus = Day13.firstDeparture(departureTime, puzzleInput);

        assertEquals(59, bus.getValue0());
        assertEquals(5, bus.getValue1());
    }

    @Test
    void delays() {
        final List<String> puzzleInput = ResourceLines.list(("/day13.txt"));
        final List<String> busses = Arrays.stream(puzzleInput.get(1).split(",")).collect(Collectors.toList());
        final List<Pair<Integer,Integer>> delays = Day13.delays(busses);

        final List<Pair<Integer,Integer>> answers = List.of(
                new Pair<>(7, 0),
                new Pair<>(13,1),
                new Pair<>(59,4),
                new Pair<>(31,6),
                new Pair<>(19,7)
        );
        IntStream.range(0, answers.size()).forEach(idx -> assertEquals(answers.get(idx), delays.get(idx)));
    }

    @Test
    void findFirstMatchingTimestamp() {
//        assertEquals(3417, Day13.findFirstMatchingTimestamp(delays("17,x,13,19"), 1_000L));
//        assertEquals(754018, Day13.findFirstMatchingTimestamp(delays("67,7,59,61"), 500_000L));
//        assertEquals(779210, Day13.findFirstMatchingTimestamp(delays("67,x,7,59,61"), 750_000L));
//        assertEquals(1261476, Day13.findFirstMatchingTimestamp(delays("67,7,x,59,61"), 1_000_000L));
//        assertEquals(1202161486, Day13.findFirstMatchingTimestamp(delays("1789,37,47,1889"), 1_200_000_000L));
        assertEquals(3417, Day13.findFirstMatchingTimestamp(delays("17,x,13,19"), 0L));
        assertEquals(754018, Day13.findFirstMatchingTimestamp(delays("67,7,59,61"), 0L));
        assertEquals(779210, Day13.findFirstMatchingTimestamp(delays("67,x,7,59,61"), 0L));
        assertEquals(1261476, Day13.findFirstMatchingTimestamp(delays("67,7,x,59,61"), 0L));
        assertEquals(1202161486, Day13.findFirstMatchingTimestamp(delays("1789,37,47,1889"), 0L));
    }

    List<Pair<Integer, Integer>> delays(String busses) {
        return Day13.delays(Arrays.stream(busses.split(",")).collect(Collectors.toList()));
    }

    @Test
    void part2() {
        final List<String> puzzleInput = ResourceLines.list(("/day13.txt"));
        final List<String> busses = Arrays.stream(puzzleInput.get(1).split(",")).collect(Collectors.toList());
        Day13.part2(busses);
    }
}