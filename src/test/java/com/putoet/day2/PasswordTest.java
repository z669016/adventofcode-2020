package com.putoet.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {
    public static final String POLICY1 = "1-3 a: abcde";
    public static final String POLICY2 = "1-3 b: cdefg";
    public static final String POLICY3 = "2-9 c: ccccccccc";

    @Test
    void password() {
        assertEquals("abcde", Password.password(POLICY1));
        assertEquals("cdefg", Password.password(POLICY2));
        assertEquals("ccccccccc", Password.password(POLICY3));
    }
}