package com.putoet.day10;

record Adapter(String name, int jolts) {
    public Adapter {
        name = name + "-" + jolts;
    }

    public Adapter(int jolts) {
        this("adapter", jolts);
    }

    @Override
    public String toString() {
        return name.startsWith("adapter") ? String.valueOf(jolts) : "(" + jolts + ")";
    }
}
