package com.putoet.day2;

import org.jetbrains.annotations.NotNull;

record SledRentalPlacePasswordPolicy(int min, int max, char character) implements PasswordPolicy {
    @Override
    public boolean isValid(String password) {
        final var count = count(password);
        return count >= min && count <= max;
    }

    private long count(@NotNull String password) {
        return password.chars().filter(c -> c == character).count();
    }
}
