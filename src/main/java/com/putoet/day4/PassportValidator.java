package com.putoet.day4;

import utilities.Validator;

import java.util.Map;
import java.util.Set;

public class PassportValidator implements Validator<Passport> {
    private static final Set<String> REQUIRED = Set.of(
            Passport.BYR,
            Passport.IYR,
            Passport.EYR,
            Passport.HCL,
            Passport.HGT,
            Passport.ECL,
            Passport.PID
    );

    @Override
    public boolean isValid(Passport passport) {
        final Map<String, String> fields = passport.fields();
        return fields.keySet().containsAll(REQUIRED);
    }
}
