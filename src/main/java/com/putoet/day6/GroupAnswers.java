package com.putoet.day6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupAnswers {
    private final List<String> answers;

    private GroupAnswers(List<String> answers) {
        assert answers != null;

        this.answers = answers;
    };

    public List<String> answers() {return answers; }

    public long allYesses() {
        final Set<Character> set = new HashSet<>();
        final String personAnswers = answers.get(0);
        for (int offset = 0; offset < personAnswers.length(); offset++)
            set.add(answers.get(0).charAt(offset));

        for (int idx = 1; idx < answers.size(); idx++) {
            final String someAnswers = answers.get(idx);
            set.removeIf(c -> someAnswers.indexOf(c) == -1);
        }
        // System.out.println("all yesses for " + answers + " is " + set);
        return set.size();
    }

    public long yesCount() {
        return answers.stream()
                .flatMapToInt(String::chars)
                .distinct()
                .count();
    }

    public static List<GroupAnswers> of(List<String> lines) {
        assert lines != null;

        final List<GroupAnswers> allAnswers = new ArrayList<>();

        List<String> answers = new ArrayList<>();
        for (String line: lines) {
            if (line.length() == 0) {
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
