package com.putoet.day2;

public class Password {
    public static String password(String line) {
        assert line != null;

        final int colon = line.indexOf(':');
        return line.substring(colon + 2);
    }
}
