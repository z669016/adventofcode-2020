package com.putoet.day8;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.regex.Pattern;

abstract class Instruction implements Consumer<Processor> {
    private final String name;
    private final int operant;

    private Instruction(String name, int operant) {
        this.name = name;
        this.operant = operant;
    }

    public String name() { return name; }
    public int operand() { return operant; }

    @Override
    public String toString() {
        return name() + " " + operant;
    }

    public static Instruction acc(int operant) {
        return new Instruction("acc", operant) {
            @Override
            public void accept(@NotNull Processor processor) {
                processor.setAccumulator(processor.getAccumulator() + operant);
            }
        };
    }

    public static Instruction nop(int operant) {
        return new Instruction("nop", operant) {
            @Override
            public void accept(@NotNull Processor processor) {}
        };
    }

    public static Instruction jmp(int operant) {
        return new Instruction("jmp", operant) {
            @Override
            public void accept(@NotNull Processor processor) {
                processor.setIP(processor.getIP() + operant);
            }
        };
    }

    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("(acc|jmp|nop) (([+\\-])(\\d+))");
    public static Instruction of(int line, @NotNull String code) {
        final var matcher = INSTRUCTION_PATTERN.matcher(code);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid instruction " + code + " at line " + line);

        return switch (matcher.group(1)) {
            case "acc" -> acc(operand(matcher.group(3), matcher.group(4)));
            case "nop" -> nop(operand(matcher.group(3), matcher.group(4)));
            case "jmp" -> jmp(operand(matcher.group(3), matcher.group(4)));
            default -> throw new IllegalArgumentException("Invalid instruction " + code + " at line " + line);
        };
    }

    private static int operand(String sign, String value) {
        return Integer.parseInt(value) * ("-".equals(sign) ? -1 : 1);
    }
}
