package com.putoet.day18;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {

    @Test
    void next() {
        final Tokenizer tokenizer = new Tokenizer("1 + (2 * 3) + (4 * (5 + 6))");

        assertTrue(tokenizer.hasMoreTokens());
        Pair<Tokenizer.Type,String> token = tokenizer.next();
        test(token, Tokenizer.Type.VALUE, "1");

        assertTrue(tokenizer.hasMoreTokens());
        token = tokenizer.next();
        test(token, Tokenizer.Type.OPERATOR, "+");

        assertTrue(tokenizer.hasMoreTokens());
        token = tokenizer.next();
        test(token, Tokenizer.Type.OPEN, "(");

        assertTrue(tokenizer.hasMoreTokens());
        token = tokenizer.next();
        test(token, Tokenizer.Type.VALUE, "2");

        assertTrue(tokenizer.hasMoreTokens());
        token = tokenizer.next();
        test(token, Tokenizer.Type.OPERATOR, "*");

        assertTrue(tokenizer.hasMoreTokens());
        token = tokenizer.next();
        test(token, Tokenizer.Type.VALUE, "3");

        assertTrue(tokenizer.hasMoreTokens());
        token = tokenizer.next();
        test(token, Tokenizer.Type.CLOSE, ")");

        assertTrue(tokenizer.hasMoreTokens());
        token = tokenizer.next();
        test(token, Tokenizer.Type.OPERATOR, "+");

        assertTrue(tokenizer.hasMoreTokens());
        token = tokenizer.next();
        test(token, Tokenizer.Type.OPEN, "(");

        assertTrue(tokenizer.hasMoreTokens());
        token = tokenizer.next();
        test(token, Tokenizer.Type.VALUE, "4");

        assertTrue(tokenizer.hasMoreTokens());
        token = tokenizer.next();
        test(token, Tokenizer.Type.OPERATOR, "*");

        assertTrue(tokenizer.hasMoreTokens());
        token = tokenizer.next();
        test(token, Tokenizer.Type.OPEN, "(");

        assertTrue(tokenizer.hasMoreTokens());
        token = tokenizer.next();
        test(token, Tokenizer.Type.VALUE, "5");

        assertTrue(tokenizer.hasMoreTokens());
        token = tokenizer.next();
        test(token, Tokenizer.Type.OPERATOR, "+");

        assertTrue(tokenizer.hasMoreTokens());
        token = tokenizer.next();
        test(token, Tokenizer.Type.VALUE, "6");

        assertTrue(tokenizer.hasMoreTokens());
        token = tokenizer.next();
        test(token, Tokenizer.Type.CLOSE, ")");

        assertTrue(tokenizer.hasMoreTokens());
        token = tokenizer.next();
        test(token, Tokenizer.Type.CLOSE, ")");

        assertFalse(tokenizer.hasMoreTokens());
    }

    private void test(Pair<Tokenizer.Type,String> token, Tokenizer.Type type, String value) {
        assertEquals(type,token.getValue0());
        assertEquals(value, token.getValue1());

    }
}