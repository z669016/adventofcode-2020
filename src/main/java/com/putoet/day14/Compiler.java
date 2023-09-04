package com.putoet.day14;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class Compiler {
    private static final Pattern MASK_PATTERN = Pattern.compile("mask = ([01X]{36})");
    private static final Pattern MEM_PATTERN = Pattern.compile("mem\\[(\\d+)] = (\\d+)");

    public static List<Instruction> compile(@NotNull List<String> program) {
        return IntStream.range(0, program.size())
                .mapToObj(idx -> compile(idx, program.get(idx)))
                .toList();
    }

    public static Instruction compile(int line, @NotNull String programLine) {
        var matcher = MEM_PATTERN.matcher(programLine);
        if (matcher.matches()) {
            final var offset = Integer.parseInt(matcher.group(1));
            return Instruction.set(offset, Long.parseLong(matcher.group(2)));
        }

        matcher = MASK_PATTERN.matcher(programLine);
        if (matcher.matches())
            return Instruction.mask(matcher.group(1));

        throw new IllegalArgumentException("Invalid instruction '" + programLine + "' at line " + line);
    }
}
