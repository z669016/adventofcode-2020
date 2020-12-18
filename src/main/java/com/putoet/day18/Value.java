package com.putoet.day18;

public class Value implements Operand {
    private final long value;

    public Value(long value) {
        this.value = value;
    }

    @Override
    public Long get() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
