package com.putoet.day8;

import com.putoet.resources.ResourceLines;

import java.util.ArrayList;
import java.util.List;

public class Day8 {
    public static void main(String[] args) {
        final List<Instruction> program = Compiler.compile(ResourceLines.list("/day8.txt"));
        part1(program);
        part2(program);
    }

    private static void part1(List<Instruction> program) {
        final Processor handHeldGameConsole = new HandHeldGameConsole(program).enableTerminateOnRepeat();

        handHeldGameConsole.run();

        System.out.println();
        System.out.println("Value in the accumulator is " + handHeldGameConsole.getAccumulator());
    }

    private static void part2(List<Instruction> program) {
        Processor handHeldGameConsole;

        int ip = 0;
        do {
            final List<Instruction> changedProgram = new ArrayList<>(program);
            final Instruction instruction = changedProgram.get(ip);
            if ("nop|jmp".contains(instruction.name())) {
                if ("nop".equals(instruction.name()))
                    changedProgram.set(ip, Instruction.jmp(instruction.operant()));
                else
                    changedProgram.set(ip, Instruction.nop(instruction.operant()));
            }
            handHeldGameConsole = new HandHeldGameConsole(changedProgram).enableTerminateOnRepeat();
            handHeldGameConsole.run();
        } while (handHeldGameConsole.terminatedOnRepeat() && ++ip < program.size());

        System.out.println();
        if (handHeldGameConsole.terminatedOnRepeat())
            System.out.println("Program terminated on repeat");
        else {
            System.out.println("changed instruction is at line " + ip);
            System.out.println("Value in the accumulator after normal termination is " + handHeldGameConsole.getAccumulator());
        }
    }
}
