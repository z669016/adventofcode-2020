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
        final var puzzleInput = ResourceLines.list(("/day13.txt"));
        final var departureTime = Integer.parseInt(puzzleInput.get(0));
        final var bus = Day13.firstDeparture(departureTime, puzzleInput);

        assertEquals(59, bus.getValue0());
        assertEquals(5, bus.getValue1());
    }

    @Test
    void delays() {
        final var puzzleInput = ResourceLines.list(("/day13.txt"));
        final var busses = Arrays.stream(puzzleInput.get(1).split(",")).collect(Collectors.toList());
        final var delays = Day13.delays(busses);

        final var answers = List.of(
                new Pair<>(7L, 0L),
                new Pair<>(13L, 1L),
                new Pair<>(59L, 4L),
                new Pair<>(31L, 6L),
                new Pair<>(19L, 7L)
        );
        IntStream.range(0, answers.size()).forEach(idx -> assertEquals(answers.get(idx), delays.get(idx)));
    }

    @Test
    void findFirstMatchingTimestamp() {
        assertEquals(3417, Day13.findFirstMatchingTimestamp(delays("17,x,13,19")));
        assertEquals(754018, Day13.findFirstMatchingTimestamp(delays("67,7,59,61")));
        assertEquals(779210, Day13.findFirstMatchingTimestamp(delays("67,x,7,59,61")));
        assertEquals(1261476, Day13.findFirstMatchingTimestamp(delays("67,7,x,59,61")));
        assertEquals(1202161486, Day13.findFirstMatchingTimestamp(delays("1789,37,47,1889")));
    }

    List<Pair<Long, Long>> delays(String busses) {
        return Day13.delays(Arrays.stream(busses.split(",")).toList());
    }

    @Test
    void part2() {
        final var puzzleInput = ResourceLines.list(("/day13.txt"));
        final var busses = Arrays.stream(puzzleInput.get(1).split(",")).collect(Collectors.toList());
        Day13.part2(busses);
    }
}