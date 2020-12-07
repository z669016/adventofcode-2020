package com.putoet.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bag {
    private final String color;
    private List<Bag> contains = new ArrayList<>();
    private List<Integer> counts = new ArrayList<>();
    private int count = -1;

    public Bag(String color) {
        assert color != null;

        this.color = color;
    }

    public String color() {
        return color;
    }

    public List<Bag> contains() {
        return contains;
    }

    public void addContent(Bag bag, int count) {
        assert bag != null;
        assert color != null;

        if (contains.contains(bag))
            throw new IllegalArgumentException(color + " bag already contains " + bag.color + " bag(s)");

        this.contains.add(bag);
        this.counts.add(count);
    }

    public int count() {
        if (count == -1) {
            count = 0;
            for (int idx = 0; idx < contains.size(); idx++) {
                count += (counts.get(idx) + counts.get(idx) * contains.get(idx).count());
            }
        }

        return count;
    }

    public boolean contains(Bag bag) {
        assert bag != null;

        if (equals(bag))
            return false;

        if (contains.contains(bag))
            return true;

        boolean containsColor = false;
        for (Bag inner : contains) {
            containsColor = (containsColor || inner.contains(bag));
        }
        return containsColor;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(color);
        sb.append(" [");
        boolean first = true;
        for (int idx = 0; idx < contains.size(); idx++) {
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
        if (!(o instanceof Bag)) return false;
        Bag bag = (Bag) o;
        return color.equals(bag.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
