package com.putoet.day8;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Compiler {
    public static List<Instruction> compile(List<String> program) {
        return IntStream.range(0, program.size())
                .mapToObj(idx -> Instruction.of(idx, program.get(idx)))
                .collect(Collectors.toList());
    }
}
