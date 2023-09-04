package com.putoet.day18;

import org.jetbrains.annotations.NotNull;

class ExpressionBuilderPlusPrecedence extends ExpressionBuilder {
    public static Expression of(@NotNull String line) {
        return ExpressionBuilder.of(line, ExpressionBuilderPlusPrecedence::new);
    }

    @Override
    public ExpressionBuilder open() {
        return new ExpressionBuilderPlusPrecedence();
    }

    @Override
    public ExpressionBuilder operand(@NotNull Operand operand) {
        operands.add(operand);

        final var lastOperatorIndex = operators.size() - 1;
        if (lastOperatorIndex > -1 && operators.get(lastOperatorIndex) instanceof TimesOperator) {
            return this;
        }

        final var lastOperandIndex = operands.size() - 1;
        if (lastOperandIndex < 2)
            return this;

        operand = new Expression(operands.get(lastOperandIndex - 1), operators.get(lastOperatorIndex), operands.get(lastOperandIndex));

        operators.remove(lastOperatorIndex);
        operands.remove(lastOperandIndex);
        operands.remove(lastOperandIndex - 1);
        operands.add(operand);

        return this;
    }

    @Override
    public ExpressionBuilder operand(@NotNull String operand) {
        return operand(new Value(Long.parseLong(operand)));
    }
}