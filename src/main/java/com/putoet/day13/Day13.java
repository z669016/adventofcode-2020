package com.putoet.day13;

import com.putoet.resources.ResourceLines;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day13 {
    private static List<Pair<Integer, Integer>> delays;

    public static void main(String[] args) {
        final List<String> puzzleInput = ResourceLines.list(("/day13.txt"));
        final int departureTime = Integer.parseInt(puzzleInput.get(0));

        part1(departureTime, puzzleInput);

        final List<String> busses = Arrays.stream(puzzleInput.get(1).split(",")).collect(Collectors.toList());
        part2(busses);
    }

    static void part1(int departureTime, List<String> puzzleInput) {
        final Pair<Integer, Integer> bus = firstDeparture(departureTime, puzzleInput);
        System.out.println("First bus to leave is " + bus.getValue0() + " in " + bus.getValue1() + " minutes");
        System.out.println("The ID of the earliest bus you can take to the airport multiplied by the number of minutes you'll need to wait for that bus is " + bus.getValue0() * bus.getValue1());
    }

    static void part2(List<String> busses) {
        final List<Pair<Integer, Integer>> delays = delays(busses);
        System.out.println("First matching timestamp is " + findFirstMatchingTimestamp(delays));
    }

    static long findFirstMatchingTimestamp(@NotNull List<Pair<Integer, Integer>> delays) {
        Day13.delays = delays;
        assert !delays.isEmpty();

        final long maxTimestamp = IntStream.range(1, delays.size())
                .mapToLong(i -> (long) delays.get(i).getValue0())
                .reduce(delays.get(0).getValue0(), ArithmeticUtils::lcm);

        final boolean[] matched = new boolean[delays.size()];
        final Pair<Integer,Integer> max = delays.stream().max(Comparator.comparingLong(Pair::getValue0)).orElseThrow();

        long step = max.getValue0();
        long timestamp = step - max.getValue1();
        boolean allMatches = false;

        while (!allMatches && timestamp <= maxTimestamp) {
            boolean adjusted = false;

            allMatches = true;
            for (int idx = 0; idx < delays.size(); idx++) {
                final Pair<Integer, Integer> pair = delays.get(idx);
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

    public static Pair<Integer, Integer> firstDeparture(int departureTime, List<String> puzzleInput) {
        return Arrays.stream(puzzleInput.get(1).split(","))
                .filter(bus -> !"x".equals(bus))
                .mapToInt(Integer::parseInt)
                .mapToObj(bus -> new Pair<>(bus, waitingTime(departureTime, bus)))
                .min(Comparator.comparing(Pair::getValue1))
                .orElseThrow(NoSuchElementException::new);
    }

    private static int waitingTime(long timestamp, int bus) {
        final int nextArrival = (int) timestamp % bus;
        return nextArrival == 0 ? nextArrival : bus - nextArrival;
    }

    public static List<Pair<Integer, Integer>> delays(List<String> busses) {
        final List<Pair<Integer, Integer>> delays = new ArrayList<>();
        for (int idx = 0; idx < busses.size(); idx++) {
            final String bus = busses.get(idx);
            if (!"x".equals(bus)) {
                delays.add(new Pair<>(Integer.parseInt(bus), idx));
            }
        }

        return delays;
    }
}
