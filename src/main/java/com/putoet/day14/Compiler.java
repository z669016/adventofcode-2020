package com.putoet.day14;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Compiler {
    private static final Pattern MASK_PATTERN = Pattern.compile("mask = ([01X]{36})");
    private static final Pattern MEM_PATTERN = Pattern.compile("mem\\[(\\d+)] = (\\d+)");

    public static List<Instruction> compile(List<String> program) {
        assert program != null;
        return IntStream.range(0, program.size())
                .mapToObj(idx -> compile(idx, program.get(idx)))
                .collect(Collectors.toList());
    }

    public static Instruction compile(int line, String programLine) {
        Matcher matcher = MEM_PATTERN.matcher(programLine);
        if (matcher.matches()) {
            final int offset = Integer.parseInt(matcher.group(1));
            return Instruction.set(offset, Long.parseLong(matcher.group(2)));
        }

        matcher = MASK_PATTERN.matcher(programLine);
        if (matcher.matches())
            return Instruction.mask(matcher.group(1));

        throw new IllegalArgumentException("Invalid instruction '" + programLine + "' at line " + line);
    }
}
