package com.putoet.day8;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.ArrayList;
import java.util.List;

public class Day8 {
    public static void main(String[] args) {
        final var program = Compiler.compile(ResourceLines.list("/day8.txt"));
        Timer.run(() -> part1(program));
        Timer.run(() -> part2(program));
    }

    private static void part1(List<Instruction> program) {
        final var handHeldGameConsole = new HandHeldGameConsole(program).enableTerminateOnRepeat();
        handHeldGameConsole.run();
        System.out.println("Value in the accumulator is " + handHeldGameConsole.getAccumulator());
    }

    private static void part2(List<Instruction> program) {
        Processor handHeldGameConsole;

        int ip = 0;
        do {
            final var changedProgram = new ArrayList<>(program);
            final var instruction = changedProgram.get(ip);
            if ("nop|jmp".contains(instruction.name())) {
                if ("nop".equals(instruction.name()))
                    changedProgram.set(ip, Instruction.jmp(instruction.operand()));
                else
                    changedProgram.set(ip, Instruction.nop(instruction.operand()));
            }
            handHeldGameConsole = new HandHeldGameConsole(changedProgram).enableTerminateOnRepeat();
            handHeldGameConsole.run();
        } while (handHeldGameConsole.terminatedOnRepeat() && ++ip < program.size());

        if (handHeldGameConsole.terminatedOnRepeat())
            System.out.println("Program terminated on repeat");
        else {
            System.out.println("changed instruction is at line " + ip);
            System.out.println("Value in the accumulator after normal termination is " + handHeldGameConsole.getAccumulator());
        }
    }
}
