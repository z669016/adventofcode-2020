package com.putoet.day14;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day14 {
    public static void main(String[] args) {
        final var instructions = Compiler.compile(ResourceLines.list("/day14.txt"));

        Timer.run(() -> part1(instructions));
        Timer.run(() -> part2(instructions));
    }

    private static void part1(List<Instruction> instructions) {
        final var memory = new MemoryDecoder();
        memory.run(instructions);

        System.out.println("the sum of all values left in memory after it completes is " +
                memory.values().stream().mapToLong(l -> l).sum());
    }

    private static void part2(List<Instruction> instructions) {
        final var memory = new MemoryDecoderV2();
        memory.run(instructions);

        System.out.println("the sum of all values left in memory V2 after it completes is " +
                memory.values().stream().mapToLong(l -> l).sum());
    }
}
