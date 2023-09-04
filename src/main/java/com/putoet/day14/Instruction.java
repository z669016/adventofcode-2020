package com.putoet.day14;

import java.util.function.Consumer;

abstract class Instruction implements Consumer<Memory> {
    private final String name;

    protected Instruction(String name) {
        this.name = name;
    }

    public String name() { return name; }

    public static Instruction mask(String mask) {
        return new Instruction("mask") {
            @Override
            public void accept(Memory memory) {
                memory.mask(mask);
            }

            @Override
            public String toString() {
                return name() + " = " + mask;
            }
        };
    }

    public static Instruction set(int offset, long value) {
        return new Instruction("mem") {
            @Override
            public void accept(Memory memory) {
                memory.set(offset, value);
            }

            @Override
            public String toString() {
                return name() + "[" + offset + "] = " + value;
            }
        };
    }
}
