package com.putoet.day6;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupAnswersTest {

    @Test
    void yesCount() {
        final var allAnswers = GroupAnswers.of(ResourceLines.list("/day6.txt"));
        final var yesCount = allAnswers.stream()
                .mapToLong(GroupAnswers::yesCount)
                .sum();

        assertEquals(11, yesCount);
    }

    @Test
    void allYesses() {
        final var allAnswers = GroupAnswers.of(ResourceLines.list("/day6.txt"));
        final var yesCount = allAnswers.stream()
                .mapToLong(GroupAnswers::allYesses)
                .sum();

        assertEquals(6, yesCount);
    }
}