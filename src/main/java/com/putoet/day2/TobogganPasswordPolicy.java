package com.putoet.day2;

import org.jetbrains.annotations.NotNull;

record TobogganPasswordPolicy(int first, int second, char character) implements PasswordPolicy {
    public TobogganPasswordPolicy {
        first = first - 1;
        second = second - 1;
    }

    @Override
    public boolean isValid(@NotNull String password) {
        if (password.charAt(first) == character) {
            return second >= password.length() || password.charAt(second) != character;
        }

        return second < password.length() && password.charAt(second) == character;
    }
}
