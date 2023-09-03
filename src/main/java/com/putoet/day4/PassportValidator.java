package com.putoet.day4;

import com.putoet.utilities.Validator;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

class PassportValidator implements Validator<Passport> {
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
    public boolean isValid(@NotNull Passport passport) {
        final var fields = passport.fields();
        return fields.keySet().containsAll(REQUIRED);
    }
}
