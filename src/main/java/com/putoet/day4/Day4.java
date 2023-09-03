package com.putoet.day4;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day4 {
    public static void main(String[] args) {
        final var passports = Batch.of(ResourceLines.list("/day4.txt"));

        Timer.run(() -> {
                    final var validator = new PassportValidator();
                    System.out.println("Number of valid passports in the batch is " + passports.stream().filter(validator::isValid).count());
                });

        Timer.run(() -> {
            final var validator = new EnhancedPassportValidator();
            System.out.println("Number of valid (enhanced) passports in the batch is " + passports.stream().filter(validator::isValid).count());
        });
    }
}
