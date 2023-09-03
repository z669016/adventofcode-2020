package com.putoet.day2;

import org.jetbrains.annotations.NotNull;

class Password {
    public static String password(@NotNull String line) {
        final var colon = line.indexOf(':');
        return line.substring(colon + 2);
    }
}
