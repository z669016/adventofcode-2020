package com.putoet.day21;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodTest {
    private List<Food> foods;

    @BeforeEach
    void setup() {
        foods = Food.of(ResourceLines.list("/day21.txt"));
    }

    @Test
    void of() {
        final Food food = Food.of("mxmxvkd kfcds sqjhc nhms (contains dairy, fish)");
        assertEquals(Set.of("mxmxvkd", "kfcds", "sqjhc", "nhms"),
                food.ingredients().stream().map(Ingredient::name).collect(Collectors.toSet()));
        assertEquals(Set.of("dairy", "fish"), food.allergens());
    }

    @Test
    void ofList() {
        assertEquals(4, foods.size());
    }

    @Test
    void allAllergens() {
        assertEquals(Set.of("dairy", "fish", "soy"), Food.allergens(foods));
    }

    @Test
    void allIngredients() {
        final Map<String, Ingredient> ingredients = Food.ingredients(foods);
        assertEquals(Set.of("mxmxvkd", "kfcds", "sqjhc", "nhms", "trh", "fvjkl", "sbzzf"), ingredients.keySet());
    }

    @Test
    void possibleAllergensFor() {
        final Map<String, Ingredient> ingredients = Food.ingredients(foods);
        assertEquals(Set.of("soy", "dairy"), Food.possibleAllergensFor(foods, ingredients.get("fvjkl")));
    }

    @Test
    void possibleFoodsWith() {
        Set<Food> foodsWith = Food.possibleFoodsWith(foods, "dairy");
        assertEquals(2, foodsWith.size());

    }

    @Test
    void possibleIngredientsWith() {
        Set<Ingredient> ingredientsWith = Food.possibleIngredientsWith(foods, "dairy");
        assertEquals(Set.of("mxmxvkd"), ingredientsWith.stream().map(Ingredient::name).collect(Collectors.toSet()));

        System.out.println("dairy: " + Food.possibleIngredientsWith(foods, "dairy"));
        System.out.println("soy: " + Food.possibleIngredientsWith(foods, "soy"));
        System.out.println("fish: " + Food.possibleIngredientsWith(foods, "fish"));
    }

    @Test
    void possibleAllergensPerIngredient() {
        final Map<String, Set<Ingredient>> possibleAllergensPerIngredient = Food.possibleAllergensPerIngredient(foods);
        final Set<Ingredient> ingredientsWithPossibleAllergen =
                possibleAllergensPerIngredient.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());

        assertEquals(Set.of("sqjhc", "fvjkl", "mxmxvkd"),
                ingredientsWithPossibleAllergen.stream().map(Ingredient::name).collect(Collectors.toSet()));
    }

    @Test
    void ingredientsWithoutAllergen() {
        final Set<Ingredient> ingredientsWithoutAllergen = Food.ingredientsWithoutAllergen(foods);
        assertEquals(Set.of("sbzzf", "kfcds", "trh", "nhms"),
                ingredientsWithoutAllergen.stream().map(Ingredient::name).collect(Collectors.toSet()));

        long count = ingredientsWithoutAllergen.stream()
                .mapToLong(ingredient -> Food.containsIngredientCount(foods, ingredient))
                .sum();
        assertEquals(5, count);
    }
}