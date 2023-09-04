package com.putoet.day21;

import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

record Food(@NotNull Set<Ingredient> ingredients, @NotNull Set<String> allergens) {

    public static List<Food> of(@NotNull List<String> lines) {
        return lines.stream().map(Food::of).toList();
    }

    public static Food of(@NotNull String line) {
        final var pair = splitAllergens(line);
        final var ingredients = pair.getValue0().split(" ");
        final var allergens = pair.getValue1().split(", ");

        return new Food(Arrays.stream(ingredients)
                .map(Ingredient::new)
                .collect(Collectors.toSet()), Arrays.stream(allergens).collect(Collectors.toSet()));
    }

    private static Pair<String, String> splitAllergens(String line) {
        final var offset = line.indexOf("(contains");
        if (offset != -1) {
            final var allergens = line.substring(offset + 10, line.indexOf(")"));
            line = line.substring(0, offset - 1);
            return new Pair<>(line, allergens);
        }

        return new Pair<>(line, "");
    }

    public static Set<String> allergens(@NotNull List<Food> foods) {
        return foods.stream()
                .map(Food::allergens)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public static Map<String, Ingredient> ingredients(@NotNull List<Food> foods) {
        return foods.stream()
                .map(Food::ingredients)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toMap(Ingredient::name, ingredient -> ingredient));
    }

    public static Set<String> possibleAllergensFor(@NotNull List<Food> foods, @NotNull Ingredient ingredient) {
        return foods.stream()
                .filter(food -> food.ingredients().contains(ingredient))
                .map(Food::allergens)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public static Set<Food> possibleFoodsWith(@NotNull List<Food> foods, @NotNull String allergen) {
        return foods.stream()
                .filter(food -> food.allergens().contains(allergen))
                .collect(Collectors.toSet());
    }

    public static Set<Ingredient> possibleIngredientsWith(@NotNull List<Food> foods, @NotNull String allergen) {
        final var foodsWith = Food.possibleFoodsWith(foods, allergen);

        if (foodsWith.size() == 1)
            return new HashSet<>(foodsWith.stream().findFirst().get().ingredients());

        if (foodsWith.size() > 1) {
            final var intersect = new HashSet<>(foodsWith.stream().findFirst().orElseThrow().ingredients());
            for (var food : foodsWith) {
                intersect.retainAll(food.ingredients());
            }
            return intersect;
        }

        return Collections.emptySet();
    }

    public static Map<String, Set<Ingredient>> possibleAllergensPerIngredient(@NotNull List<Food> foods) {
        final var possibleAllergensPerIngredient =
                Food.allergens(foods).stream()
                        .collect(Collectors.toMap(allergen -> allergen, allergen -> Food.possibleIngredientsWith(foods, allergen)));

        var changed = true;
        while (changed) {
            changed = false;

            final var ingredientsWithOneAllergen = possibleAllergensPerIngredient.values().stream()
                    .filter(ingredients -> ingredients.size() == 1)
                    .map(ingredients -> ingredients.stream().findFirst().get())
                    .collect(Collectors.toSet());

            for (var allergen : possibleAllergensPerIngredient.keySet()) {
                final var possibleIngredientsWithAllergen = possibleAllergensPerIngredient.get(allergen);
                if (possibleIngredientsWithAllergen.size() > 1) {
                    possibleIngredientsWithAllergen.removeIf(ingredientsWithOneAllergen::contains);
                    changed = true;
                }
            }
        }

        return possibleAllergensPerIngredient;
    }

    public static Set<Ingredient> ingredientsWithoutAllergen(@NotNull List<Food> foods) {
        final var possibleAllergensPerIngredient = Food.possibleAllergensPerIngredient(foods);
        final var ingredientsWithPossibleAllergen =
                possibleAllergensPerIngredient.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());

        return Food.ingredients(foods).values().stream()
                .filter(ingredient -> !ingredientsWithPossibleAllergen.contains(ingredient))
                .collect(Collectors.toSet());

    }

    public static long containsIngredientCount(@NotNull List<Food> foods, @NotNull Ingredient ingredient) {
        return foods.stream()
                .filter(food -> food.ingredients().contains(ingredient))
                .count();
    }
}
