package com.putoet.day8;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Instruction implements Consumer<Processor> {
    private final String name;
    private final int operant;

    private Instruction(String name, int operant) {
        this.name = name;
        this.operant = operant;
    }

    public String name() { return name; }
    public int operant() { return operant; }

    @Override
    public String toString() {
        return name() + " " + operant;
    }

    public static Instruction acc(int operant) {
        return new Instruction("acc", operant) {
            @Override
            public void accept(Processor processor) {
                processor.setAccumulator(processor.getAccumulator() + operant);
            }
        };
    }

    public static Instruction nop(int operant) {
        return new Instruction("nop", operant) {
            @Override
            public void accept(Processor processor) {}
        };
    }

    public static Instruction jmp(int operant) {
        return new Instruction("jmp", operant) {
            @Override
            public void accept(Processor processor) {
                processor.setIP(processor.getIP() + operant);
            }
        };
    }

    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("(acc|jmp|nop) (([+\\-])(\\d+))");
    public static Instruction of(int line, String code) {

        final Matcher matcher = INSTRUCTION_PATTERN.matcher(code);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid instruction " + code + " at line " + line);

        return switch (matcher.group(1)) {
            case "acc" -> acc(operant(matcher.group(3), matcher.group(4)));
            case "nop" -> nop(operant(matcher.group(3), matcher.group(4)));
            case "jmp" -> jmp(operant(matcher.group(3), matcher.group(4)));
            default -> throw new IllegalArgumentException("Invalid instruction " + code + " at line " + line);
        };
    }

    private static int operant(String sign, String value) {
        return Integer.parseInt(value) * ("-".equals(sign) ? -1 : 1);
    }
}
