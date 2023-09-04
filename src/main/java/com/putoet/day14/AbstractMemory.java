package com.putoet.day14;

import org.jetbrains.annotations.NotNull;

import java.util.*;

abstract class AbstractMemory implements Memory {
    protected static final long MAX_MASK = 0xfffffffffL;

    protected final Map<Long, Long> values = new HashMap<>();
    protected String mask;

    public static long bit(int idx) {
        return 1L << idx;
    }

    @Override
    public void mask(@NotNull String mask) {
        this.mask = mask;
    }

    @Override
    public long get(long offset) {
        assert offset >= 0;
        return values.get(offset);
    }

    @Override
    public Collection<Long> values() {
        return values.values();
    }

    @Override
    public void run(@NotNull List<Instruction> instructions) {
        instructions.forEach(this::run);
    }

    @Override
    public void run(@NotNull Instruction instruction) {
        instruction.accept(this);
    }

}
