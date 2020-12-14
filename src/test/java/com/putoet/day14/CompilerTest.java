package com.putoet.day14;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompilerTest {
    @Test
    void compile() {
        final List<String> program = ResourceLines.list("/day14.txt");
        final List<Instruction> instructions = Compiler.compile(program);

        assertEquals(4, instructions.size());
        assertEquals("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X", instructions.get(0).toString());
        assertEquals("mem[8] = 11", instructions.get(1).toString());
        assertEquals("mem[7] = 101", instructions.get(2).toString());
        assertEquals("mem[8] = 0", instructions.get(3).toString());
    }

    @Test
    void compilerError() {
        assertThrows(IllegalArgumentException.class, () -> Compiler.compile(0,"mask = XXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"));
    }
}