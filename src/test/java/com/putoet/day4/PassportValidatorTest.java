package com.putoet.day4;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;
import utilities.Validator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PassportValidatorTest {

    @Test
    void isValid() {
        final List<Passport> passports = Batch.of(ResourceLines.list("/day4.txt"));
        final Validator<Passport> validator = new PassportValidator();
        assertEquals(2L, passports.stream().filter(validator::isValid).count());
    }
}