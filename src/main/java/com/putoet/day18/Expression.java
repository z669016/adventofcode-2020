package com.putoet.day18;

import org.jetbrains.annotations.NotNull;

record Expression(@NotNull Operand left, @NotNull Operator operator, @NotNull Operand right) implements Operand {
    @Override
    public Long get() {
        return operator.apply(left.get(), right.get());
    }

    @Override
    public String toString() {
        return "(" + left + operator + right + ")";
    }
}
