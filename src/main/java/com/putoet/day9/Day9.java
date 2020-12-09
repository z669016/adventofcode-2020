package com.putoet.day9;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day9 {
    public static void main(String[] args) {
        final List<Long> numbers = ResourceLines.longList("/day9.txt");
        final XMAS xmas = new XMAS(25);

        final long invalidNumber = part1(xmas, numbers);
        part2(xmas, numbers, invalidNumber);
    }

    private static long part1(XMAS xmas, List<Long> numbers) {
        final int offset = xmas.firstInvalidOffset(numbers);

        if (offset == -1)
            System.out.println("All numbers are valid");
        else
            System.out.println("The first invalid number is " + numbers.get(offset) + " at offset " + offset);

        return offset != -1 ? numbers.get(offset) : -1;
    }

    private static void part2(XMAS xmas, List<Long> numbers, long invalidNumber) {
        final long weakness = xmas.weakness(numbers, invalidNumber);
        System.out.println("the encryption weakness in your XMAS-encrypted list of numbers " + weakness);
    }
}
