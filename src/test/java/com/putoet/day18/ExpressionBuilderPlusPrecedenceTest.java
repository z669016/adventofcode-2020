package com.putoet.day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionBuilderPlusPrecedenceTest {

    @Test
    void samples() {
        var expression = ExpressionBuilderPlusPrecedence.of("1 + (2 * 3) + (4 * (5 + 6))");
        System.out.println(expression);
        assertEquals(51, expression.get());

        expression = ExpressionBuilderPlusPrecedence.of("2 * 3 + (4 * 5)");
        System.out.println(expression);
        assertEquals(46, expression.get());

        expression = ExpressionBuilderPlusPrecedence.of("5 + (8 * 3 + 9 + 3 * 4 * 3)");
        System.out.println(expression);
        assertEquals(1445, expression.get());

        expression = ExpressionBuilderPlusPrecedence.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))");
        System.out.println(expression);
        assertEquals(669060, expression.get());

        expression = ExpressionBuilderPlusPrecedence.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");
        System.out.println(expression);
        assertEquals(23340, expression.get());
    }

}