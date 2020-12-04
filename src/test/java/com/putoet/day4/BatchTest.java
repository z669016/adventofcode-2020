package com.putoet.day4;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BatchTest {

    @Test
    void of() {
        final List<String> lines = ResourceLines.list("/day4.txt");
        final List<Passport> passports = Batch.of(lines);

        assertEquals(4, passports.size());
        System.out.println(passports);
    }
}