package com.putoet.day23;

import java.util.Arrays;

public class CupCircle {
    private final int[] init;
    private final int[] next;
    private int current;
    private final int max;

    public CupCircle(String init, int length) {
        assert init.length() == 9;
        assert length >= init.length();

        this.init = new int[]{
                init.charAt(0) - '0',
                init.charAt(1) - '0',
                init.charAt(2) - '0',
                init.charAt(3) - '0',
                init.charAt(4) - '0',
                init.charAt(5) - '0',
                init.charAt(6) - '0',
                init.charAt(7) - '0',
                init.charAt(8) - '0'
        };
        next = new int[length];

        for (int i = 0; i < length - 1; i++)
            next[i] = i + 1;
        next[length - 1] = 0;

        current = 0;

        max = length <= init.length() ? Arrays.stream(this.init).max().getAsInt() : length;
    }

    public CupCircle(String init) {
        this(init, init.length());
    }

    private int indexOf(int value) {
        assert value > 0 && value <= next.length;

        if (value < 10) {
            for (int i = 0; i < 10; i++)
                if (init[i] == value)
                    return i;
        }

        return value - 1;
    }

    private int valueAt(int offset) {
        assert offset >= 0 && offset < next.length;

        if (offset < 9)
            return init[offset];

        return offset + 1;
    }

    public void nextCircle() {
        int afterCurrent = next[current];
        int[] from = new int[]{afterCurrent, next[afterCurrent], next[next[afterCurrent]]};
        int[] selected = new int[]{valueAt(from[0]), valueAt(from[1]), valueAt(from[2])};

        int destination = destination(selected);

        int to = indexOf(destination);
        int temp = next[current];
        next[current] = next[from[2]];
        next[from[2]] = next[to];
        next[to] = temp;

        current = next[current];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(valueAt(current));

        int i = next[current];
        while (i != current) {
            sb.append(" ").append(valueAt(i));
            i = next[i];
        }
        return sb.toString();
    }

    private int destination(int[] selected) {
        int destination = valueAt(current) - 1;
        while (selected[0] == destination || selected[1] == destination || selected[2] == destination)
            destination--;

        if (destination == 0)
            destination = max;

        while (selected[0] == destination || selected[1] == destination || selected[2] == destination)
            destination--;

        return destination;
    }

    public int size() {
        return next.length;
    }

    public String afterOne() {
        int one = indexOf(1);
        int offset = next[one];
        int count = 0;

        final StringBuilder sb = new StringBuilder();
        while (offset != one && count++ < 10) {
            sb.append(valueAt(offset));
            offset = next[offset];
        }
        return sb.toString();
    }

    public long afterOneTwoValues() {
        int one = indexOf(1);

        int offset = next[one];
        final long firstValue = valueAt(offset);

        offset = next[offset];
        final long secondValue = valueAt(offset);

        return firstValue * secondValue;
    }
}
