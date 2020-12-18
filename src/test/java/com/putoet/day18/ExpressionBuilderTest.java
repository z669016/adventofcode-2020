package com.putoet.day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionBuilderTest {

    @Test
    void simpleAdd() {
        ExpressionBuilder builder = new ExpressionBuilder();
        final Expression expression = builder
                .operand(new Value(3))
                .operator(Operator.of('+'))
                .operand(new Value(5))
                .build();

        System.out.println(expression);
        assertEquals(8, expression.get());
    }

    @Test
    void complicated() {
        ExpressionBuilder builder = new ExpressionBuilder();
        final Expression expression = builder // 3+ 5 * 2 + 7
                .operand(new Value(3))
                .operator(Operator.of('+'))
                .operand(new Value(5))
                .operator(Operator.of('*'))
                .operand(new Value(2))
                .operator(Operator.of('+'))
                .operand(new Value(7))
                .build();

        System.out.println(expression);
        assertEquals(23, expression.get());
    }

    @Test
    void complex() {
        ExpressionBuilder builder = new ExpressionBuilder();
        final Expression expression = builder // 1 + (2 * 3) + (4 * (5 + 6))
                .operand(1)
                .operator('+')
                .operand(builder.open()
                        .operand(2)
                        .operator('*')
                        .operand(3)
                    .close())
                .operator('+')
                .operand(builder.open()
                        .operand(4)
                        .operator('*')
                        .operand(builder.open()
                                .operand(5)
                                .operator('+')
                                .operand(6)
                                .close())
                        .close())
                .build();

        System.out.println(expression);
        assertEquals(51, expression.get());
    }

    @Test
    void of() {
        final Expression expression = ExpressionBuilder.of("1 + (2 * 3) + (4 * (5 + 6))");
        System.out.println(expression);
        assertEquals(51, expression.get());
    }

    @Test
    void samples() {
        assertEquals(26, ExpressionBuilder.of("2 * 3 + (4 * 5)").get());
        assertEquals(437, ExpressionBuilder.of("5 + (8 * 3 + 9 + 3 * 4 * 3)").get());
        assertEquals(12240, ExpressionBuilder.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))").get());
        assertEquals(13632, ExpressionBuilder.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2").get());
    }
}