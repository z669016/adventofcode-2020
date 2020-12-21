package com.putoet.day21;

import org.javatuples.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Food {
    private final Set<Ingredient> ingredients;
    private final Set<String> allergens;

    public Food(Set<Ingredient> ingredients, Set<String> allergens) {
        this.ingredients = ingredients;
        this.allergens = allergens;
    }

    public static List<Food> of(List<String> lines) {
        return lines.stream().map(Food::of).collect(Collectors.toList());
    }

    public static Food of(String line) {
        final Pair<String, String> pair = splitAllergens(line);
        final String[] ingredients = pair.getValue0().split(" ");
        final String[] allergens = pair.getValue1().split(", ");

        return new Food(Arrays.stream(ingredients)
                .map(Ingredient::new)
                .collect(Collectors.toSet()),
                Arrays.stream(allergens).collect(Collectors.toSet()));
    }

    private static Pair<String, String> splitAllergens(String line) {
        final int offset = line.indexOf("(contains");
        if (offset != -1) {
            final String allergens = line.substring(offset + 10, line.indexOf(")"));
            line = line.substring(0, offset - 1);
            return new Pair<>(line, allergens);
        }

        return new Pair<>(line, "");
    }

    public static Set<String> allergens(List<Food> foods) {
        return foods.stream()
                .map(Food::allergens)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public static Map<String, Ingredient> ingredients(List<Food> foods) {
        return foods.stream()
                .map(Food::ingredients)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toMap(Ingredient::name, ingredient -> ingredient));
    }

    public static Set<String> possibleAllergensFor(List<Food> foods, Ingredient ingredient) {
        return foods.stream()
                .filter(food -> food.ingredients().contains(ingredient))
                .map(Food::allergens)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public static Set<Food> possibleFoodsWith(List<Food> foods, String allergen) {
        return foods.stream()
                .filter(food -> food.allergens().contains(allergen))
                .collect(Collectors.toSet());
    }

    public static Set<Ingredient> possibleIngredientsWith(List<Food> foods, String allergen) {
        final Set<Food> foodsWith = Food.possibleFoodsWith(foods, allergen);

        if (foodsWith.size() == 1)
            return new HashSet<>(foodsWith.stream().findFirst().get().ingredients());

        if (foodsWith.size() > 1) {
            final Set<Ingredient> intersect = new HashSet<>(foodsWith.stream().findFirst().get().ingredients());
            for (Food food : foodsWith) {
                intersect.retainAll(food.ingredients());
            }
            return intersect;
        }

        return Collections.emptySet();
    }

    public static Map<String, Set<Ingredient>> possibleAllergensPerIngredient(List<Food> foods) {
        final Map<String, Set<Ingredient>> possibleAllergensPerIngredient =
                Food.allergens(foods).stream()
                        .collect(Collectors.toMap(allergen -> allergen, allergen -> Food.possibleIngredientsWith(foods, allergen)));

        boolean changed = true;
        while (changed) {
            changed = false;

            final Set<Ingredient> ingredientsWithOneAllergen = possibleAllergensPerIngredient.values().stream()
                    .filter(ingredients -> ingredients.size() == 1)
                    .map(ingredients -> ingredients.stream().findFirst().get())
                    .collect(Collectors.toSet());

            for (String allergen : possibleAllergensPerIngredient.keySet()) {
                final Set<Ingredient> possibleIngredientsWithAllergen = possibleAllergensPerIngredient.get(allergen);
                if (possibleIngredientsWithAllergen.size() > 1) {
                    possibleIngredientsWithAllergen.removeIf(ingredientsWithOneAllergen::contains);
                    changed = true;
                }
            }
        }

        return possibleAllergensPerIngredient;
    }

    public static Set<Ingredient> ingredientsWithoutAllergen(List<Food> foods) {
        final Map<String, Set<Ingredient>> possibleAllergensPerIngredient = Food.possibleAllergensPerIngredient(foods);
        final Set<Ingredient> ingredientsWithPossibleAllergen =
                possibleAllergensPerIngredient.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());

        return Food.ingredients(foods).values().stream()
                .filter(ingredient -> !ingredientsWithPossibleAllergen.contains(ingredient))
                .collect(Collectors.toSet());

    }

    public static long containsIngredientCount(List<Food> foods, Ingredient ingredient) {
        return foods.stream()
                .filter(food -> food.ingredients().contains(ingredient))
                .count();
    }

    public Set<Ingredient> ingredients() {
        return Collections.unmodifiableSet(ingredients);
    }

    public Set<String> allergens() {
        return Collections.unmodifiableSet(allergens);
    }

    @Override
    public String toString() {
        return ingredients + " (contains " + String.join(", ", allergens) + ")";
    }

}
