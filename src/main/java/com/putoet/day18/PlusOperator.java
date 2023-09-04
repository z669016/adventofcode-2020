package com.putoet.day18;

class PlusOperator extends Operator {
    @Override
    public Long apply(Long left, Long right) {
        return left + right;
    }

    @Override
    public String toString() {
        return " + ";
    }
}
