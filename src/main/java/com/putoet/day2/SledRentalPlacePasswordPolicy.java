package com.putoet.day2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SledRentalPlacePasswordPolicy implements PasswordPolicy {
    private final int min;
    private final int max;
    private final char character;

    public SledRentalPlacePasswordPolicy(int min, int max, char character) {
        this.min = min;
        this.max = max;
        this.character = character;
    }

    @Override
    public boolean isValid(String password) {
        final long count = count(password);
        return count >= min && count <= max;
    }

    private long count(String password) {
        assert password != null;

        return password.chars().filter(c -> c == character).count();
    }

    private static final Pattern POLICY_PATTERN = Pattern.compile("(\\d+)-(\\d+) ([a-z]): ([a-z]+)");
    public static SledRentalPlacePasswordPolicy of(String line) {
        assert line != null;

        final Matcher matcher = POLICY_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid password policy: " + line);

        final int min = Integer.parseInt(matcher.group(1));
        final int max = Integer.parseInt(matcher.group(2));
        final char character = matcher.group(3).charAt(0);

        return new SledRentalPlacePasswordPolicy(min, max, character);
    }
}
