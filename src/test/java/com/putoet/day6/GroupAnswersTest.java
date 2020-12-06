package com.putoet.day6;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupAnswersTest {

    @Test
    void yesCount() {
        final List<GroupAnswers> allAnswers = GroupAnswers.of(ResourceLines.list("/day6.txt"));
        final long yesCount = allAnswers.stream()
                .mapToLong(GroupAnswers::yesCount)
                .sum();

        assertEquals(11, yesCount);
    }

    @Test
    void allYesses() {
        final List<GroupAnswers> allAnswers = GroupAnswers.of(ResourceLines.list("/day6.txt"));
        final long yesCount = allAnswers.stream()
                .mapToLong(GroupAnswers::allYesses)
                .sum();

        assertEquals(6, yesCount);
    }
}