package com.putoet.day14;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryDecoderTest {

    @Test
    void set() {
        final var program = ResourceLines.list("/day14.txt");
        final var instructions = Compiler.compile(program);

        final var memory = new MemoryDecoder();
        memory.run(instructions);

        final var sum = memory.values().stream().mapToLong(l -> l).sum();
        assertEquals(165, sum);
    }
}