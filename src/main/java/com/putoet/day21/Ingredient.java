package com.putoet.day21;

record Ingredient(String name) {
    @Override
    public String toString() {
        return name;
    }
}
