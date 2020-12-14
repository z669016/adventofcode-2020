package com.putoet.day10;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AdapterMatcherTest {
    private final List<Adapter> one = AdapterMatcher.of(ResourceLines.list("/day10-1.txt"));
    private final List<Adapter> two = AdapterMatcher.of(ResourceLines.list("/day10-2.txt"));

    @Test
    void of() {
        assertEquals(List.of(0, 1, 4, 5, 6, 7, 10, 11, 12, 15, 16, 19, 22), one.stream()
                .mapToInt(Adapter::jolts)
                .boxed()
                .collect(Collectors.toList()));
    }

    @Test
    void differenceOne() {
        final int diffOne = AdapterMatcher.difference(one, 1);
        assertEquals(7, diffOne);

        final int diffThree = AdapterMatcher.difference(one, 3);
        assertEquals(5, diffThree);
    }

    @Test
    void differenceTwo() {
        final int diffOne = AdapterMatcher.difference(two, 1);
        assertEquals(22, diffOne);

        final int diffThree = AdapterMatcher.difference(two, 3);
        assertEquals(10, diffThree);
    }

    @Test
    void combinations() {
        assertEquals(8L, AdapterMatcher.combinations(one));
        System.out.println();
        assertEquals(19208L, AdapterMatcher.combinations(two));
    }
}