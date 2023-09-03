package com.putoet.day6;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class GroupAnswers {
    private final List<String> answers;

    private GroupAnswers(List<String> answers) {
        assert answers != null;

        this.answers = answers;
    }

    public long allYesses() {
        final var set = new HashSet<Character>();
        final var personAnswers = answers.get(0);
        for (var offset = 0; offset < personAnswers.length(); offset++)
            set.add(answers.get(0).charAt(offset));

        for (var idx = 1; idx < answers.size(); idx++) {
            final var someAnswers = answers.get(idx);
            set.removeIf(c -> someAnswers.indexOf(c) == -1);
        }
        return set.size();
    }

    public long yesCount() {
        return answers.stream()
                .flatMapToInt(String::chars)
                .distinct()
                .count();
    }

    public static List<GroupAnswers> of(@NotNull List<String> lines) {
        final var allAnswers = new ArrayList<GroupAnswers>();

        var answers = new ArrayList<String>();
        for (var line: lines) {
            if (line.isEmpty()) {
                allAnswers.add(new GroupAnswers(answers));
                answers = new ArrayList<>();
            } else {
                answers.add(line);
            }
        }
        allAnswers.add(new GroupAnswers(answers));

        return allAnswers;
    }
}
