package com.putoet.day18;

public class Expression implements Operand {
    private final Operand left;
    private final Operator operator;
    private final Operand right;

    public Expression(Operand left, Operator operator, Operand right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public Long get() {
        return operator.apply(left.get(), right.get());
    }

    @Override
    public String toString() {
        return "(" + left.toString() + operator.toString()+ right.toString() + ")";
    }
}
