package com.putoet.day2;

import com.putoet.day2.Password;
import com.putoet.day2.TobogganPasswordPolicy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TobogganPasswordPolicyTest {

    public static final String POLICY1 = "1-3 a: abcde";
    public static final String POLICY2 = "1-3 b: cdefg";
    public static final String POLICY3 = "2-9 c: ccccccccc";

    @Test
    void isValid() {
        TobogganPasswordPolicy policy = TobogganPasswordPolicy.of(POLICY1);
        assertTrue(policy.isValid(Password.password(POLICY1)));

        policy = TobogganPasswordPolicy.of(POLICY2);
        assertFalse(policy.isValid(Password.password(POLICY2)));

        policy = TobogganPasswordPolicy.of(POLICY3);
        assertFalse(policy.isValid(Password.password(POLICY3)));
    }}