package com.putoet.day21;

import com.putoet.resources.ResourceLines;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day21 {
    public static void main(String[] args) {
        final List<Food> foods = Food.of(ResourceLines.list("/day21.txt"));
        part1(foods);
        part2(foods);
    }

    public static void part1(List<Food> foods) {
        final Set<Ingredient> ingredientsWithoutAllergen = Food.ingredientsWithoutAllergen(foods);
        long count = ingredientsWithoutAllergen.stream()
                .mapToLong(ingredient -> Food.containsIngredientCount(foods, ingredient))
                .sum();

        // System.out.println("Ingredients without allergen are: " + ingredientsWithoutAllergen);
        System.out.println("Number of times an ingredients without allergen are used is " + count);
    }

    public static void part2(List<Food> foods) {
        final Map<String, Set<Ingredient>> possibleAllergensPerIngredient = Food.possibleAllergensPerIngredient(foods);
        // possibleAllergensPerIngredient.forEach((key, value) -> System.out.println(key + " is contained in " + value));

        System.out.println("My canonical dangerous ingredient list: " +
                possibleAllergensPerIngredient.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .map(Map.Entry::getValue)
                        .flatMap(Collection::stream)
                        .map(Ingredient::name)
                        .collect(Collectors.joining(",")));
    }
}
