package com.putoet.day7;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Bag {
    private final String color;
    private final List<Bag> contains = new ArrayList<>();
    private final List<Integer> counts = new ArrayList<>();
    private int count = -1;

    public Bag(@NotNull String color) {
        this.color = color;
    }

    public String color() {
        return color;
    }

    public List<Bag> contains() {
        return contains;
    }

    public void addContent(@NotNull Bag bag, int count) {
        if (contains.contains(bag))
            throw new IllegalArgumentException(color + " bag already contains " + bag.color + " bag(s)");

        this.contains.add(bag);
        this.counts.add(count);
    }

    public int count() {
        // memoization ... -1 means field not calculated yet
        if (count == -1) {
            count = 0;
            for (var idx = 0; idx < contains.size(); idx++) {
                count += (counts.get(idx) + counts.get(idx) * contains.get(idx).count());
            }
        }

        return count;
    }

    public boolean contains(@NotNull Bag bag) {
        if (equals(bag))
            return false;

        if (contains.contains(bag))
            return true;

        var containsColor = false;
        for (var inner : contains) {
            containsColor = (containsColor || inner.contains(bag));
        }
        return containsColor;
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder(color);
        sb.append(" [");
        var first = true;
        for (var idx = 0; idx < contains.size(); idx++) {
            if (!first)
                sb.append(", ");
            sb.append(counts.get(idx)).append(" ").append(contains.get(idx).color);
            first = false;
        }
        sb.append("]");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bag bag)) return false;
        return count == bag.count && Objects.equals(color, bag.color) && Objects.equals(contains, bag.contains) && Objects.equals(counts, bag.counts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, contains, counts, count);
    }
}
