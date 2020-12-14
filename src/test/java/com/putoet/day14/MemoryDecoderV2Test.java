package com.putoet.day14;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryDecoderV2Test {

    @Test
    void set() {
        final List<String> program = List.of(
                "mask = 000000000000000000000000000000X1001X",
                "mem[42] = 100",
                "mask = 00000000000000000000000000000000X0XX",
                "mem[26] = 1");
        final List<Instruction> instructions = Compiler.compile(program);

        final Memory memory = new MemoryDecoderV2();
        memory.run(instructions);

        final long sum = memory.values().stream().mapToLong(l -> l).sum();
        assertEquals(208, sum);
    }
}