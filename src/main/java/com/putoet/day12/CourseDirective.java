package com.putoet.day12;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

record CourseDirective(@NotNull Command command, int length) {
    private static final Pattern COURSE_DIRECTIVE = Pattern.compile("([NESWLRF])(\\d+)");
    public static CourseDirective of(@NotNull String line) {
        final var matcher = COURSE_DIRECTIVE.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid course directive " + line);

        return new CourseDirective(Command.of(matcher.group(1)), Integer.parseInt(matcher.group(2)));
    }
}
