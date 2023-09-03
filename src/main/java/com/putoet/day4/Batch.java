package com.putoet.day4;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

class Batch {
    public static List<Passport> of(@NotNull List<String> lines) {
        final var passports = new ArrayList<Passport>();

        final var iter = lines.iterator();
        while (iter.hasNext()) {
            final var passportLines = passportLines(iter);
            passports.add(Passport.of(linesAsFields(passportLines)));
        }

        return passports;
    }

    private static List<String> passportLines(Iterator<String> iter) {
        final var lines = new ArrayList<String >();

        while (iter.hasNext()) {
            var line = iter.next();
            if (!line.isEmpty())
                lines.add(line);
            else
                return lines;
        }

        return lines;
    }

    private static Map<String, String> linesAsFields(List<String> passportLines) {
        return passportLines.stream()
                .map(line -> line.split(" "))
                .flatMap(Arrays::stream)
                .collect(Collectors.toMap(keyValue -> keyValue.split(":")[0], keyValue -> keyValue.split(":")[1]));
    }
}
