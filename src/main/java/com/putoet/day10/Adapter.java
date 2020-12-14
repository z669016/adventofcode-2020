package com.putoet.day10;

import java.util.Objects;

public class Adapter {
    private final String name;
    private final int jolts;

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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adapter)) return false;
        Adapter adapter = (Adapter) o;
        return name.equals(adapter.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
