package com.putoet.day16;

import org.jetbrains.annotations.NotNull;

import java.util.List;

record Ticket(@NotNull String name, @NotNull List<Integer> fields) {
    public int field(int idx) {
        assert idx >= 0 && idx < fields.size();

        return fields.get(idx);
    }
}
