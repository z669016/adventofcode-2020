package com.putoet.day13;

import com.putoet.math.EuclideanAlgorithm;
import com.putoet.resources.ResourceLines;
import org.javatuples.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Day13 {
    public static void main(String[] args) {
        final List<String> puzzleInput = ResourceLines.list(("/day13.txt"));
        final int departureTime = Integer.parseInt(puzzleInput.get(0));

        part1(departureTime, puzzleInput);

        final List<String> busses = Arrays.stream(puzzleInput.get(1).split(",")).collect(Collectors.toList());
        part2(busses);
    }

    private static void part1(int departureTime, List<String> puzzleInput) {
        final Pair<Integer, Integer> bus = firstDeparture(departureTime, puzzleInput);
        System.out.println("First bus to leave is " + bus.getValue0() + " in " + bus.getValue1() + " minutes");
        System.out.println("The ID of the earliest bus you can take to the airport multiplied by the number of minutes you'll need to wait for that bus is " + bus.getValue0() * bus.getValue1());
    }

    public static void part2(List<String> busses) {
        final List<Pair<Integer, Integer>> delays = delays(busses);
        System.out.println("First matching timestamp is " + findFirstMatchingTimestamp(delays, 100_000_000_000_000L));

        // 2144202323094770313 is too high
        // 1207316338570002 is too high
        //  103162733339807 is too low
        //  159383494549004 is
        //  100000000000000L

        System.out.println("First matching timestamp is " + findFirstMatchingTimestamp(delays, 159383494549004L));

    }

    public static long findFirstMatchingTimestamp(List<Pair<Integer, Integer>> delays, long min) {
        assert delays != null && delays.size() > 0;

        System.out.println("Delays: " + delays);
        final long maxTimestamp = EuclideanAlgorithm.lcm(delays.stream().mapToLong(Pair::getValue0).boxed().collect(Collectors.toList()));

        final boolean[] matched = new boolean[delays.size()];
        final Pair<Integer,Integer> max = delays.stream().max(Comparator.comparingLong(Pair::getValue0)).get();

        long step = max.getValue0();
        long timestamp = step - max.getValue1();
        boolean allMatches = false;

        System.out.println("Initial step size is " + step);
        System.out.println("Starting at timestamp " + timestamp);
        while (!allMatches && timestamp <= maxTimestamp) {
            System.out.print(timestamp);
            System.out.print("\r");

            allMatches = true;
            for (int idx = 0; idx < delays.size(); idx++) {
                final Pair<Integer, Integer> pair = delays.get(idx);
//                if (!matched[idx]) {
                    matched[idx] = (pair.getValue1() == waitingTime(timestamp, pair.getValue0()));
//                    if (matched[idx]) {
//                        step = EuclideanAlgorithm.lcm(step, pair.getValue0());
//                        System.out.println("Increase step to " + step + " idx = " + idx);
//                    }
//                }
                allMatches = allMatches && matched[idx];
            }

            if (!allMatches)
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
                final int id = Integer.parseInt(bus);
                delays.add(new Pair<>(id, idx % id));
            }
        }

        return delays;
    }
}
