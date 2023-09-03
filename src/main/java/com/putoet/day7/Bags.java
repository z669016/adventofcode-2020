package com.putoet.day7;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

class Bags {
    private static final Pattern BAG_DEFINITION = Pattern.compile("^(\\d)+ (.+) bag.*$");
    private static final Pattern BAG_DECLARATION = Pattern.compile("^(.+) bags contain .*$");

    public static Map<String, Bag> loadBags(@NotNull List<String> lines) {
        final var bags = new HashMap<String, Bag>();

        for (var line : lines) {
            final var bag = declareBag(bags, line);
            final var bagDefinitions = bagDefinitions(line);

            for (var bagDefinition : bagDefinitions) {
                if (!bagDefinition.startsWith("no ")) {
                    final var matcher = BAG_DEFINITION.matcher(bagDefinition);
                    if (!matcher.matches())
                        throw new IllegalArgumentException("Invalid bag definition '" + bagDefinition + "'");

                    final var count = Integer.parseInt(matcher.group(1));
                    final var color = matcher.group(2);
                    bag.addContent(createBag(bags, color), count);
                }
            }
        }

        return bags;
    }

    private static Bag declareBag(Map<String, Bag> bags, String line) {
        final var bag = bagDeclaration(line);
        if (bags.containsKey(bag.color()))
            return bags.get(bag.color());

        bags.put(bag.color(), bag);
        return bag;
    }

    private static Bag createBag(Map<String, Bag> bags, String color) {
        if (!bags.containsKey(color))
            bags.put(color, new Bag(color));

        return bags.get(color);
    }

    private static Bag bagDeclaration(String line) {
        final var matcher = BAG_DECLARATION.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid bag declaration '" + line + "'");

        return new Bag(matcher.group(1));
    }

    private static String[] bagDefinitions(String line) {
        return line.substring(line.indexOf("contain ") + 8).split(", ");
    }
}
