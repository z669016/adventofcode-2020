package com.putoet.day21;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day21 {
    public static void main(String[] args) {
        final List<Food> foods = Food.of(ResourceLines.list("/day21.txt"));
        Timer.run(() -> part1(foods));
        Timer.run(() -> part2(foods));
    }

    static void part1(List<Food> foods) {
        final var ingredientsWithoutAllergen = Food.ingredientsWithoutAllergen(foods);
        final var count = ingredientsWithoutAllergen.stream()
                .mapToLong(ingredient -> Food.containsIngredientCount(foods, ingredient))
                .sum();

        System.out.println("Number of times an ingredients without allergen are used is " + count);
    }

    static void part2(List<Food> foods) {
        final var possibleAllergensPerIngredient = Food.possibleAllergensPerIngredient(foods);

        System.out.println("My canonical dangerous ingredient list: " +
                possibleAllergensPerIngredient.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .map(Map.Entry::getValue)
                        .flatMap(Collection::stream)
                        .map(Ingredient::name)
                        .collect(Collectors.joining(",")));
    }
}
