package com.putoet.day2;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

interface PasswordPolicy {
    boolean isValid(String password);

    Pattern POLICY_PATTERN = Pattern.compile("(\\d+)-(\\d+) ([a-z]): ([a-z]+)");
    @SneakyThrows
    static <T> T of(@NotNull String line, Class<T> clazz) {
        final var matcher = POLICY_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid password policy: " + line);

        final int min = Integer.parseInt(matcher.group(1));
        final int max = Integer.parseInt(matcher.group(2));
        final char character = matcher.group(3).charAt(0);

        return clazz.getDeclaredConstructor(int.class, int.class, char.class).newInstance(min, max, character);
    }
}
