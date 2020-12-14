package com.putoet.day14;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryDecoderTest {

    @Test
    void set() {
        final List<String> program = ResourceLines.list("/day14.txt");
        final List<Instruction> instructions = Compiler.compile(program);

        final Memory memory = new MemoryDecoder();
        memory.run(instructions);

        final long sum = memory.values().stream().mapToLong(l -> l).sum();
        assertEquals(165, sum);
    }
}