package com.putoet.day13;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.IntStream;

public class Day13 {
    public static void main(String[] args) {
        final var puzzleInput = ResourceLines.list(("/day13.txt"));
        final var departureTime = Long.parseLong(puzzleInput.get(0));

        Timer.run(() -> part1(departureTime, puzzleInput));

        final var busses = Arrays.stream(puzzleInput.get(1).split(",")).toList();
        Timer.run(() -> part2(busses));
    }

    static void part1(long departureTime, @NotNull List<String> puzzleInput) {
        final var bus = firstDeparture(departureTime, puzzleInput);
        System.out.println("The ID of the earliest bus you can take to the airport multiplied by the number of minutes you'll need to wait for that bus is " + bus.getValue0() * bus.getValue1());
    }

    static void part2(@NotNull List<String> busses) {
        final var delays = delays(busses);
        System.out.println("First matching timestamp is " + findFirstMatchingTimestamp(delays));
    }

    static long findFirstMatchingTimestamp(@NotNull List<Pair<Long,Long>> delays) {
        assert !delays.isEmpty();

        final var maxTimestamp = IntStream.range(1, delays.size())
                .mapToLong(i -> delays.get(i).getValue0())
                .reduce(delays.get(0).getValue0(), ArithmeticUtils::lcm);

        final var matched = new boolean[delays.size()];
        final var max = delays.stream().max(Comparator.comparingLong(Pair::getValue0)).orElseThrow();

        var step = max.getValue0();
        var timestamp = step - max.getValue1();
        var allMatches = false;

        while (!allMatches && timestamp <= maxTimestamp) {
            var adjusted = false;

            allMatches = true;
            for (var idx = 0; idx < delays.size(); idx++) {
                final var pair = delays.get(idx);
                if (!matched[idx]) {
                    matched[idx] = (timestamp + pair.getValue1()) % pair.getValue0() == 0;
                    if (matched[idx]) {
                        step = ArithmeticUtils.lcm(step, pair.getValue0());
                        adjusted = true;
                    }
                }
                allMatches = allMatches && matched[idx];
            }

            if (!allMatches && !adjusted)
                timestamp += step;
        }
        return timestamp;
    }

    static Pair<Integer, Integer> firstDeparture(long departureTime, @NotNull List<String> puzzleInput) {
        return Arrays.stream(puzzleInput.get(1).split(","))
                .filter(bus -> !"x".equals(bus))
                .mapToInt(Integer::parseInt)
                .mapToObj(bus -> Pair.with(bus, waitingTime(departureTime, bus)))
                .min(Comparator.comparing(Pair::getValue1))
                .orElseThrow(NoSuchElementException::new);
    }

    private static int waitingTime(long timestamp, int bus) {
        final var nextArrival = (int) timestamp % bus;
        return nextArrival == 0 ? nextArrival : bus - nextArrival;
    }

    static List<Pair<Long,Long>> delays(@NotNull List<String> busses) {
        final var delays = new ArrayList<Pair<Long,Long>>();
        for (var idx = 0; idx < busses.size(); idx++) {
            final var bus = busses.get(idx);
            if (!"x".equals(bus)) {
                delays.add(Pair.with(Long.parseLong(bus), (long) idx));
            }
        }

        return delays;
    }
}
