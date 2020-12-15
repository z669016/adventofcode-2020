package com.putoet.day15;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Numbers implements Supplier<Long> {
    private final Map<Long,Long> times = new HashMap<>();
    private final Map<Long,Long> lastTurns = new HashMap<>();
    private final Map<Long,Long> prevTurns = new HashMap<>();
    private long last;
    private long turn;

    public Numbers(List<Integer> numbers) {
        assert numbers != null && numbers.size() > 0;

        for (int idx = 0; idx < numbers.size(); idx++) {
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

        long next = times.getOrDefault(last, 0L);
        if (next > 0) {
            next = lastTurns.get(last) - prevTurns.get(last);
        }

        if (times.containsKey(next))
            times.put(next, times.get(next) + 1);
        else
            times.put(next, 0L);

        prevTurns.put(next, lastTurns.getOrDefault(next, 0L));
        lastTurns.put(next, turn);
        last = next;

        return last;
    }
}
