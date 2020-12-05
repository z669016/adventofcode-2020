package com.putoet.day4;

import com.putoet.resources.ResourceLines;
import com.putoet.utilities.Validator;

import java.util.List;

public class Day4 {
    public static void main(String[] args) {
        final List<Passport> passports = Batch.of(ResourceLines.list("/day4.txt"));

        Validator<Passport> validator = new PassportValidator();
        System.out.println("Number of valid passports in the batch is " + passports.stream().filter(validator::isValid).count());

        validator = new EnhancedPassportValidator();
        System.out.println("Number of valid (enhanced) passports in the batch is " + passports.stream().filter(validator::isValid).count());
    }
}
