package com.putoet.day9;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.IntStream;

class XMAS {
    private final int preamble;

    public XMAS(int preamble) {
        this.preamble = preamble;
    }

    public int firstInvalidOffset(@NotNull List<Long> numbers) {
        var offset = preamble;
        while (isValid(numbers, offset))
            offset++;

        return offset >= numbers.size() ? -1 : offset;
    }

    private boolean isValid(List<Long> numbers, int offset) {
        assert offset >= preamble && offset < numbers.size();

        long search = numbers.get(offset);
        int n1, n2 = offset - preamble;
        for (n1 = offset - preamble; n1 < offset; n1++) {
            for (n2 = n1 + 1; n2 < offset; n2++) {
                if (numbers.get(n1) + numbers.get(n2) == search)
                    return true;
            }
        }
        return n1 != numbers.size() && (n1 != n2);
    }

    public OptionalLong weakness(@NotNull List<Long> numbers, long invalidNumber) {
        final var weaknessList = findWeakness(numbers, invalidNumber);
        if (weaknessList.isEmpty())
            return OptionalLong.empty();

        return OptionalLong.of(weaknessList.get(0) + weaknessList.get(weaknessList.size() - 1));

    }

    private List<Long> findWeakness(List<Long> numbers, long invalidNumber) {
        boolean[] selected = null;

        for (var offset = 0; offset < numbers.size(); offset++) {
            selected = new boolean[numbers.size()];
            if (numbers.get(offset) != invalidNumber) {
                selected[offset] = true;

                selected = findWeakness(selected, offset, numbers, invalidNumber);
                if (sum(selected, numbers) == invalidNumber)
                    break;
            }
        }

        return sum(selected, numbers) == invalidNumber ? list(Objects.requireNonNull(selected), numbers) : List.of();
    }

    private boolean[] findWeakness(boolean[] selected, int lastSelected, List<Long> numbers, long invalidNumber) {
        var copy = selected;
        var found = sum(copy, numbers);

        for (var offset = lastSelected + 1; offset < numbers.size() && found < invalidNumber; offset++) {
            copy = Arrays.copyOf(selected, selected.length);
            if (numbers.get(offset) != invalidNumber) {
                copy[offset] = true;

                copy = findWeakness(copy, offset, numbers, invalidNumber);
                found = sum(copy, numbers);
            }
        }

        return copy;
    }

    private long sum(boolean[] selected, List<Long> numbers) {
        if (selected == null)
            return -1;

        var sum = 0L;
        for (var idx = 0; idx < selected.length; idx++)
            if (selected[idx]) sum += numbers.get(idx);

        return sum;
    }

    private List<Long> list(boolean[] selected, List<Long> numbers) {
        assert selected!= null && numbers != null && selected.length == numbers.size();

        return IntStream.range(0, selected.length)
                .filter(idx -> selected[idx])
                .mapToObj(numbers::get)
                .sorted()
                .toList();
    }
}
