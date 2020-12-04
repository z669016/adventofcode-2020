package com.putoet.day4;

import java.util.*;
import java.util.stream.Collectors;

public class Batch {
    public static List<Passport> of(List<String> lines) {
        final List<Passport> passports = new ArrayList<>();

        final Iterator<String> iter = lines.iterator();
        while (iter.hasNext()) {
            final List<String> passportLines = passportLines(iter);
            passports.add(Passport.of(linesAsFields(passportLines)));
        }

        return passports;
    }

    private static List<String> passportLines(Iterator<String> iter) {
        final List<String> lines = new ArrayList<>();

        while (iter.hasNext()) {
            String line = iter.next();
            if (line.length() > 0)
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
