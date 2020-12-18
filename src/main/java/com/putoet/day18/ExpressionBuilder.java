package com.putoet.day18;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Supplier;

public class ExpressionBuilder {
    protected final List<Operand> operands = new ArrayList<>();
    protected final List<Operator> operators = new ArrayList<>();

    public static Expression of(String line) {
        return of(line, ExpressionBuilder::new);
    }

    protected static Expression of(String line, Supplier<ExpressionBuilder> supplier) {
        final Stack<ExpressionBuilder> builders = new Stack<>();
        final Tokenizer tokenizer = new Tokenizer(line);

        ExpressionBuilder builder = supplier.get();
        while (tokenizer.hasMoreTokens()) {
            final Pair<Tokenizer.Type, String> token = tokenizer.next();

            switch (token.getValue0()) {
                case OPEN -> {
                    builders.push(builder);
                    builder = builder.open();
                }
                case CLOSE -> {
                    final Expression expression = builder.close();
                    builder = builders.pop();
                    builder.operand(expression);
                }
                case VALUE -> builder.operand(token.getValue1());
                case OPERATOR -> builder.operator(token.getValue1());
            }
        }

        return builder.build();
    }

    public ExpressionBuilder operator(Operator operator) {
        operators.add(operator);
        return this;
    }

    public ExpressionBuilder operator(String operator) {
        operators.add(Operator.of(operator));
        return this;
    }

    public ExpressionBuilder operator(char operator) {
        operators.add(Operator.of(operator));

        return this;
    }

    public ExpressionBuilder operand(Operand operand) {
        operands.add(operand);
        return this;
    }

    public ExpressionBuilder operand(String operand) {
        operands.add(new Value(Long.parseLong(operand)));
        return this;
    }

    public ExpressionBuilder operand(long operand) {
        operands.add(new Value(operand));
        return this;
    }

    public ExpressionBuilder open() {
        return new ExpressionBuilder();
    }

    public Expression close() {
        return build();
    }

    public Expression build() {
        if (operands.size() == 1 && operands.get(0) instanceof Expression)
            return (Expression) operands.get(0);

        if (operands.size() < 2)
            throw new IllegalStateException("Not enough operators end/or operands to build an expression.");

        if (operators.size() != operands.size() - 1)
            throw new IllegalStateException("Insufficient number of operators or operands.");


        return build(operands.size() - 1);
    }

    protected Expression build(int offset) {
        if (offset == 1)
            return new Expression(operands.get(1), operators.get(0), operands.get(0));

        return new Expression(operands.get(offset), operators.get(offset - 1), build(offset - 1));
    }
}
