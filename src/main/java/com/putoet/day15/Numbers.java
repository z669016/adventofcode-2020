package com.putoet.day15;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

class Numbers implements Supplier<Long> {
    private final Map<Long,Long> times = new HashMap<>();
    private final Map<Long,Long> lastTurns = new HashMap<>();
    private final Map<Long,Long> prevTurns = new HashMap<>();
    private long last;
    private long turn;

    public Numbers(@NotNull List<Integer> numbers) {
        assert !numbers.isEmpty();

        for (var idx = 0; idx < numbers.size(); idx++) {
            turn = idx + 1L;
            last = numbers.get(idx);
            times.put(last, 0L);
            lastTurns.put(last, turn);
        }
    }

    public long turns() {
        return turn;
    }

    @Override
    public Long get() {
        turn++;

        var next = times.getOrDefault(last, 0L);
        if (next > 0) {
            next = lastTurns.get(last) - prevTurns.get(last);
        }

        times.put(next, times.computeIfAbsent(next, k -> -1L) + 1L);
        prevTurns.put(next, lastTurns.getOrDefault(next, 0L));
        lastTurns.put(next, turn);
        last = next;

        return last;
    }
}
