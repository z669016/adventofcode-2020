package com.putoet.day14;

import java.util.*;

public abstract class AbstractMemory implements Memory {
    protected static final long MAX_MASK = 0xfffffffffL;

    protected final Map<Long, Long> values = new HashMap<>();
    protected String mask;

    public static long bit(int idx) {
        return 1L << idx;
    }

    @Override
    public void mask(String mask) {
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
    public void run(List<Instruction> instructions) {
        instructions.forEach(this::run);
    }

    @Override
    public void run(Instruction instruction) {
        instruction.accept(this);
    }

    @Override
    public void dump() {
        values.entrySet().stream()
                .sorted(Comparator.comparingLong(Map.Entry::getKey))
                .forEach(entry -> System.out.println("mem[" + entry.getKey() + "] = " + entry.getValue()));
    }
}
