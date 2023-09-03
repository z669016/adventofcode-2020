package com.putoet.day8;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.IntStream;

class Compiler {
    public static List<Instruction> compile(@NotNull List<String> program) {
        return IntStream.range(0, program.size())
                .mapToObj(idx -> Instruction.of(idx, program.get(idx)))
                .toList();
    }
}
