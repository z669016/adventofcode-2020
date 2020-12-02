package com.putoet.day2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TobogganPasswordPolicy implements PasswordPolicy {
    private final int first;
    private final int second;
    private final char character;

    public TobogganPasswordPolicy(int first, int second, char character) {
        this.first = first - 1;
        this.second = second - 1;
        this.character = character;
    }

    @Override
    public boolean isValid(String password) {
        assert password != null;

        if (password.charAt(first) == character) {
            return second >= password.length() || password.charAt(second) != character;
        }

        return second < password.length() && password.charAt(second) == character;
    }

    private static final Pattern POLICY_PATTERN = Pattern.compile("(\\d+)-(\\d+) ([a-z]): ([a-z]+)");
    public static TobogganPasswordPolicy of(String line) {
        assert line != null;

        final Matcher matcher = POLICY_PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid password policy: " + line);

        final int first = Integer.parseInt(matcher.group(1));
        final int second = Integer.parseInt(matcher.group(2));
        final char character = matcher.group(3).charAt(0);

        return new TobogganPasswordPolicy(first, second, character);
    }

}
