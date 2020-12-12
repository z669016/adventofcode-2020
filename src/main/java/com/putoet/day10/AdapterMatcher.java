package com.putoet.day10;

import org.javatuples.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AdapterMatcher {
    public static List<Adapter> of(List<String> lines) {
        assert lines != null;

        final List<Adapter> adapters = lines.stream()
                .mapToInt(Integer::parseInt)
                .mapToObj(Adapter::new).collect(Collectors.toCollection(ArrayList::new));
        final OptionalInt max = adapters.stream().mapToInt(Adapter::jolts).max();
        if (max.isEmpty())
            throw new IllegalArgumentException("No max adapter joltage found.");

        adapters.add(new Adapter("device", max.getAsInt() + 3));
        adapters.add(new Adapter("socket", 0));
        return adapters;
    }

    public static Adapter stack(List<Adapter> adapters) {
        final Set<Adapter> toStack = new HashSet<>(adapters);
        Adapter last = null;

        int jolts = 0;
        while (!toStack.isEmpty()) {
            final Adapter next = findNext(jolts, toStack);
            if (last != null)
                next.stack(last);

            last = next;
            jolts = next.jolts();
            toStack.remove(next);
        }

        return last;
    }

    private static Adapter findNext(int jolts, Set<Adapter> toStack) {
        final List<Adapter> options = toStack.stream()
                .map(adapter -> new Pair<Integer, Adapter>(adapter.jolts() - jolts, adapter))
                .filter(pair -> pair.getValue0() <= 3)
                .map(Pair::getValue1)
                .collect(Collectors.toList());
        if (options.size() == 0)
            throw new IllegalArgumentException("No next adapter found for " + jolts + " in " + toStack);

        Adapter min = options.get(0);
        for (int idx = 1; idx < options.size(); idx++) {
            final Adapter option = options.get(idx);
            if (option.jolts() - jolts < min.jolts() - jolts)
                min = option;
        }

        return min;
    }

    public static int differenceCount(Adapter stackedAdapter, int difference) {
        assert stackedAdapter != null;

        int count = 0;
        do {
            if (stackedAdapter.difference() == difference) count++;
            stackedAdapter = stackedAdapter.prev();
        } while (stackedAdapter != null);

        return count;
    }

    public static long combinations(Adapter stackedAdapter) {
        assert stackedAdapter != null && stackedAdapter.prev() != null;

        if (stackedAdapter.prev().prev() == null)
            return 1;

        final List<Pair<Adapter, Adapter>> pairs = createPairs(stackedAdapter);
        return combinations(pairs);
    }

    private static long combinations(List<Pair<Adapter, Adapter>> pairs) {
        final List<Pair<Adapter, Adapter>> matches = matches(pairs, 0);
        return 1 + IntStream.range(0, matches.size())
                .mapToObj(idx -> matches(pairs, idx))
                .mapToInt(Collection::size)
                .sum();
    }

    private static List<Pair<Adapter, Adapter>> matches(List<Pair<Adapter, Adapter>> pairs, int offset) {
        final List<Pair<Adapter, Adapter>> matches = new ArrayList<>();

        final Pair<Adapter, Adapter> first = pairs.get(offset++);
        while (offset < pairs.size()) {
            final Pair<Adapter, Adapter> second = pairs.get(offset++);
            if (!((first.getValue0().jolts() >= second.getValue0().jolts()
                    && second.getValue0().jolts() >= first.getValue1().jolts())
                    || (first.getValue0().jolts() >= second.getValue1().jolts()
                    && second.getValue1().jolts() >= first.getValue1().jolts())))
                matches.add(second);
        }

        return matches;
    }

    protected static List<Pair<Adapter, Adapter>> createPairs(Adapter stackedAdapter) {
        final List<Pair<Adapter, Adapter>> pairs = new ArrayList<>();

        pairs.addAll(createPairs(stackedAdapter, 2));
        pairs.addAll(createPairs(stackedAdapter, 3));

        return pairs;
    }

    private static List<Pair<Adapter, Adapter>> createPairs(Adapter stackedAdapter, int distance) {
        assert stackedAdapter != null && Adapter.prev(stackedAdapter, distance).isPresent();

        final List<Pair<Adapter, Adapter>> pairs = new ArrayList<>();
        for (Adapter first = stackedAdapter, second = Adapter.prev(stackedAdapter, distance).get();
             second != null;
             first = first.prev(), second = second.prev()) {
            if (first.jolts() - second.jolts() <= 3) pairs.add(new Pair<>(first, second));
        }

        return pairs;
    }
}
