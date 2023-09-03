package com.putoet.day8;

import org.jetbrains.annotations.NotNull;

import java.util.List;

class HandHeldGameConsole implements Processor {
    private int accumulator;
    private int ip;
    private final List<Instruction> instructions;
    private final boolean[] executed;
    private boolean terminateOnRepeat;
    private boolean terminatedOnRepeat;

    public HandHeldGameConsole(@NotNull List<Instruction> instructions) {
        this.instructions = instructions;
        this.executed = new boolean[instructions.size()];
    }

    @Override
    public int getAccumulator() {
        return accumulator;
    }

    @Override
    public void setAccumulator(int value) {
        accumulator = value;
    }

    @Override
    public int getIP() {
        return ip;
    }

    @Override
    public void setIP(int value) {
        ip = value;
    }

    @Override
    public Processor enableTerminateOnRepeat() {
        terminateOnRepeat = true;
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
            final var instruction = instructions.get(ip);

            if (terminateOnRepeat && executed[ip]) {
                terminatedOnRepeat = true;
                return;
            }

            executed[ip] = true;
            instruction.accept(this);
            if (!instruction.name().equals("jmp"))
                ip++;
        }
    }

    @Override
    public String toString() {
        return String.format("[ip=%d, acc=%d]", ip, accumulator);
    }
}
