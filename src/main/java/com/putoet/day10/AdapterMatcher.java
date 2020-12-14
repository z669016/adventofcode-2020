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
            if (adapters.get(idx+1).jolts() - adapters.get(idx).jolts() == value)
                count++;
        }

        return count;
    }


}
