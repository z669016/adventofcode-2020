package com.putoet.day9;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day9 {
    public static void main(String[] args) {
        final var numbers = ResourceLines.list("/day9.txt", Long::parseLong);
        final var xmas = new XMAS(25);

        final var invalidNumber = Timer.run(() -> part1(xmas, numbers));
        Timer.run(() -> part2(xmas, numbers, invalidNumber));
    }

    private static long part1(XMAS xmas, List<Long> numbers) {
        final var offset = xmas.firstInvalidOffset(numbers);

        if (offset == -1)
            System.out.println("All numbers are valid");
        else
            System.out.println("The first invalid number is " + numbers.get(offset) + " at offset " + offset);

        return offset != -1 ? numbers.get(offset) : -1;
    }

    private static void part2(XMAS xmas, List<Long> numbers, long invalidNumber) {
        final var weakness = xmas.weakness(numbers, invalidNumber).orElseThrow();
        System.out.println("the encryption weakness in your XMAS-encrypted list of numbers " + weakness);
    }
}
