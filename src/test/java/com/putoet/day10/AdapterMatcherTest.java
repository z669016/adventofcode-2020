package com.putoet.day10;

import com.putoet.resources.ResourceLines;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdapterMatcherTest {

    @Test
    void ofOne() {
        final List<Adapter> adapters = AdapterMatcher.of(ResourceLines.list("/day10-1.txt"));

        assertEquals(13, adapters.size());
    }

    @Test
    void ofTwo() {
        final List<Adapter> adapters = AdapterMatcher.of(ResourceLines.list("/day10-2.txt"));

        assertEquals(33, adapters.size());
    }

    @Test
    void stackOne() {
        final List<Adapter> adapters = AdapterMatcher.of(ResourceLines.list("/day10-1.txt"));
        final Adapter adapter = AdapterMatcher.stack(adapters);

        assertTrue(adapter.name().startsWith("device"));

        assertEquals(7, AdapterMatcher.differenceCount(adapter, 1));
        assertEquals(5, AdapterMatcher.differenceCount(adapter, 3));
    }

    @Test
    void stackTwo() {
        final List<Adapter> adapters = AdapterMatcher.of(ResourceLines.list("/day10-2.txt"));
        final Adapter adapter = AdapterMatcher.stack(adapters);

        assertTrue(adapter.name().startsWith("device"));
        System.out.println(adapter);

        assertEquals(22, AdapterMatcher.differenceCount(adapter, 1));
        assertEquals(10, AdapterMatcher.differenceCount(adapter, 3));
    }

    @Test
    void createPairs() {
        final List<Adapter> adapters = AdapterMatcher.of(ResourceLines.list("/day10-1.txt"));
        final Adapter stackedAdapter = AdapterMatcher.stack(adapters);
        System.out.println(stackedAdapter);

        final List<Pair<Adapter, Adapter>> pairs = AdapterMatcher.createPairs(stackedAdapter);
        pairs.forEach(pair -> System.out.printf("<%s,%s>%n", pair.getValue0().name(), pair.getValue1().name()));
    }

    @Test
    void combinations() {
        List<Adapter> adapters = AdapterMatcher.of(ResourceLines.list("/day10-1.txt"));
        Adapter stackedAdapter = AdapterMatcher.stack(adapters);

        assertEquals(8, AdapterMatcher.combinations(stackedAdapter));

        adapters = AdapterMatcher.of(ResourceLines.list("/day10-2.txt"));
        stackedAdapter = AdapterMatcher.stack(adapters);

        assertEquals(1920, AdapterMatcher.combinations(stackedAdapter));
    }
}