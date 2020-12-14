package com.putoet.day14;

import java.util.Collection;
import java.util.List;

public interface Memory {
    void mask(String mask);

    long get(long offset);

    long set(long offset, long value);

    Collection<Long> values();

    void run(List<Instruction> instructions);

    void run(Instruction instruction);

    void dump();
}
