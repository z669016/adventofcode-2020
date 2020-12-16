package com.putoet.day16;

import java.util.List;

public class Ticket {
    private final String name;
    private final List<Integer> fields;

    public Ticket(String name, List<Integer> fields) {
        this.name = name;
        this.fields = fields;
    }

    public String name() { return name; }
    public List<Integer> fields() { return fields; }
    public int field(int idx) {
        assert idx >= 0 && idx < fields.size();

        return fields.get(idx);
    }

    @Override
    public String toString() {
        return "{name: " + name + ", fields: " + fields + "}";
    }
}
