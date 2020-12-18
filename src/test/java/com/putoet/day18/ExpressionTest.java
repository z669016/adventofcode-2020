package com.putoet.day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTest {

    @Test
    void getSimplePlus() {
        final Expression expression = new Expression(new Value(3), new PlusOperator(), new Value(5));
        assertEquals(8, expression.get());
    }

    @Test
    void getSimpleTimes() {
        final Expression expression = new Expression(new Value(3), new TimesOperator(), new Value(5));
        assertEquals(15, expression.get());
    }
}