package com.putoet.day8;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandHeldGameConsoleTest {

    @Test
    void run() {
        final var handHeldGameConsole =
                new HandHeldGameConsole(Compiler.compile(ResourceLines.list("/day8.txt")))
                .enableTerminateOnRepeat();

        handHeldGameConsole.run();

        assertEquals(5, handHeldGameConsole.getAccumulator());
    }
}