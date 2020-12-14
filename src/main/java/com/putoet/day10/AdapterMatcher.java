package com.putoet.day10;

import java.util.*;
import java.util.stream.Collectors;

public class AdapterMatcher {
    public static List<Adapter> of(List<String> lines) {
        assert lines != null && lines.size() > 0;

        final List<Adapter> adapters = lines.stream()
                .mapToInt(Integer::parseInt)
                .mapToObj(Adapter::new).collect(Collectors.toCollection(ArrayList::new));
        final int max = adapters.stream().mapToInt(Adapter::jolts).max().getAsInt();

        adapters.add(new Adapter("device", max + 3));
        adapters.add(new Adapter("socket", 0));
        adapters.sort(Comparator.comparing(Adapter::jolts));
        return adapters;
    }

    public static int difference(List<Adapter> adapters, int value) {
        int count = 0;
        for (int idx = 0; idx < adapters.size() - 1; idx++) {
            if (difference(adapters, idx, idx + 1) == value)
                count++;
        }

        return count;
    }

    public static int difference(List<Adapter> adapters, int from, int to) {
        assert adapters != null;
        assert from >= 0;
        assert from < to;
        assert to < adapters.size();

        return Math.abs(adapters.get(to).jolts() - adapters.get(from).jolts());
    }

    public static long combinations(List<Adapter> stackedAdapter) {
        final List<List<Adapter>> split = split(stackedAdapter);
        return split.stream()
                .mapToLong(list -> combinations(list, 0))
                .reduce(1, (a, b) -> a * b);
    }

    private static long combinations(List<Adapter> stackedAdapter, int start) {
        int count = 1;
        for (int idx = start; idx < stackedAdapter.size() - 2; idx++) {
            if (difference(stackedAdapter, idx, idx + 2) <= 3) {
                final List<Adapter> alternative = new ArrayList<>(stackedAdapter);
                alternative.remove(idx + 1);
                count += combinations(alternative, idx);
            }
        }
        return count;
    }

    private static List<List<Adapter>> split(List<Adapter> adapters) {
        final List<List<Adapter>> split = new ArrayList<>();

        List<Adapter> subList = new ArrayList<>();
        for (int start = -1, idx = 0; idx < adapters.size() - 2; idx++) {
            if (start == -1 && difference(adapters, idx, idx + 2) <= 3) start = idx;
            if (start != -1 && difference(adapters, idx, idx + 2) <= 3) subList.add(adapters.get(idx));
            if (start != -1 && difference(adapters, idx, idx + 2) > 3) {
                subList.add(adapters.get(idx));
                subList.add(adapters.get(idx+1));
                split.add(subList);
                subList = new ArrayList<>();
                start = -1;
                idx++;
            }
        }

        return split;
    }
}
