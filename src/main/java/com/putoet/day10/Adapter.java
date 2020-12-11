package com.putoet.day10;

import java.util.Objects;
import java.util.Optional;

public class Adapter {
    private final String name;
    private final int jolts;
    private Adapter prev;

    public Adapter(String name, int jolts) {
        this.name = name + "-" + jolts;
        this.jolts = jolts;
    }

    public Adapter(int jolts) {
        this.name = "adapter-" + jolts;
        this.jolts = jolts;
    }

    public int jolts() { return jolts; }
    public String name() { return name; }
    public Adapter prev() { return prev; }

    public Adapter stack(Adapter prev) {
        assert prev != null;

        if (jolts - prev.jolts < 0 || jolts - prev.jolts > 3)
            throw new IllegalArgumentException("Difference is too big between " + this + " an " + prev);
        this.prev = prev;

        return prev;
    }

    public int difference() {
        return  (prev == null) ? jolts : jolts - prev.jolts;
    }

    @Override
    public String toString() {
        return name + (prev != null ? " -> " + prev.toString() : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adapter)) return false;
        Adapter adapter = (Adapter) o;
        return name.equals(adapter.name) && Objects.equals(prev, adapter.prev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static Optional<Adapter> prev(Adapter adapter, int offset) {
        assert adapter != null;

        do {
            adapter = adapter.prev();
            offset--;
        } while (adapter != null && offset > 0);

        return offset == 0 && adapter != null ? Optional.of(adapter) : Optional.empty();
    }

}
