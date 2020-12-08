package com.putoet.day8;

import java.util.List;

public class HandHeldGameConsole implements Processor {
    private int accumulator;
    private int ip;
    private final List<Instruction> instructions;
    private final boolean[] executed;
    private boolean verbose;
    private boolean terminateOnRepeat;
    private boolean terminatedOnRepeat;

    public HandHeldGameConsole(List<Instruction> instructions) {
        assert instructions != null;

        this.instructions = instructions;
        this.executed = new boolean[instructions.size()];
    }

    @Override
    public int getAccumulator() {
        return accumulator;
    }

    @Override
    public Processor setAccumulator(int value) {
        accumulator = value;
        return this;
    }

    @Override
    public int getIP() {
        return ip;
    }

    @Override
    public Processor setIP(int value) {
        ip = value;
        return this;
    }

    @Override
    public Processor enableVerbose() {
        verbose = true;
        return this;
    }

    @Override
    public Processor disableVerbose() {
        verbose = false;
        return this;
    }

    @Override
    public Processor enableTerminateOnRepeat() {
        terminateOnRepeat = true;
        return this;
    }

    @Override
    public Processor disableTerminateOnRepeat() {
        terminateOnRepeat = false;
        return this;
    }

    @Override
    public boolean terminatedOnRepeat() {
        return terminatedOnRepeat;
    }

    @Override
    public void run() {
        ip = 0;

        while (ip < instructions.size()) {
            final Instruction instruction = instructions.get(ip);

            if (terminateOnRepeat && executed[ip]) {
                terminatedOnRepeat = true;
                return;
            }

            if (verbose)
                System.out.print(this + " "  + instruction);

            executed[ip] = true;
            instruction.accept(this);
            if (!instruction.name().equals("jmp"))
                ip++;

            if (verbose)
                System.out.println(" " + this);
        }
    }

    @Override
    public String toString() {
        return String.format("[ip=%d, acc=%d]", ip, accumulator);
    }
}
