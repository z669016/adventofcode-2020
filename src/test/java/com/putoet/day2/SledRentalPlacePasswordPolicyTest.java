package com.putoet.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SledRentalPlacePasswordPolicyTest {

    public static final String POLICY1 = "1-3 a: abcde";
    public static final String POLICY2 = "1-3 b: cdefg";
    public static final String POLICY3 = "2-9 c: ccccccccc";

    @Test
    void isValid() {
        SledRentalPlacePasswordPolicy policy = SledRentalPlacePasswordPolicy.of(POLICY1);
        assertTrue(policy.isValid(Password.password(POLICY1)));

        policy = SledRentalPlacePasswordPolicy.of(POLICY2);
        assertFalse(policy.isValid(Password.password(POLICY2)));

        policy = SledRentalPlacePasswordPolicy.of(POLICY3);
        assertTrue(policy.isValid(Password.password(POLICY3)));
    }
}