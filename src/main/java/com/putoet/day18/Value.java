package com.putoet.day18;

record Value(long value) implements Operand {
    @Override
    public Long get() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
