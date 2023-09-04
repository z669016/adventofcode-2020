package com.putoet.day14;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

interface Memory {
    void mask(@NotNull String mask);

    long get(long offset);

    long set(long offset, long value);

    Collection<Long> values();

    void run(@NotNull List<Instruction> instructions);

    void run(@NotNull Instruction instruction);
}
