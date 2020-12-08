package com.putoet.day8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InstructionTest {
    private Processor processor;

    @BeforeEach
    void setup() {
        processor = mock(Processor.class);
    }

    @Test
    void acc() {
        final Instruction instruction = Instruction.of(0, "acc +3");
        instruction.accept(processor);
        assertEquals("acc", instruction.name());
        verify(processor, times(1)).getAccumulator();
        verify(processor, times(1)).setAccumulator(3);
        verify(processor, times(0)).getIP();
        verify(processor, times(0)).setIP(anyInt());
    }

    @Test
    void nop() {
        final Instruction instruction = Instruction.of(0, "nop +7");
        instruction.accept(processor);
        assertEquals("nop", instruction.name());
        verify(processor, times(0)).getAccumulator();
        verify(processor, times(0)).setAccumulator(anyInt());
        verify(processor, times(0)).getIP();
        verify(processor, times(0)).setIP(anyInt());
    }

    @Test
    void jmp() {
        final Instruction instruction = Instruction.of(0, "jmp -5");
        instruction.accept(processor);
        assertEquals("jmp", instruction.name());
        verify(processor, times(0)).getAccumulator();
        verify(processor, times(0)).setAccumulator(anyInt());
        verify(processor, times(1)).getIP();
        verify(processor, times(1)).setIP(-5);
    }

    @Test
    void of() {
    }
}