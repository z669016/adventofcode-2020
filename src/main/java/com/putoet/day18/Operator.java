package com.putoet.day18;


import java.util.function.BiFunction;

public abstract class Operator implements BiFunction<Long,Long,Long> {
    public static Operator of(char operator) {
        return switch (operator) {
            case '+' -> new PlusOperator();
            case '*' -> new TimesOperator();
            default -> throw new IllegalArgumentException("Invalid operator '" + operator + "'");
        };
    }

    public static Operator of(String operator) {
        return operator.length() == 1 ? of(operator.charAt(0)) : of('?');
    }
}
