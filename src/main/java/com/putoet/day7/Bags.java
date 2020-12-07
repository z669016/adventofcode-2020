package com.putoet.day7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bags {
    private static final Pattern BAG_DEFINITION = Pattern.compile("^(\\d)+ (.+) bag.*$");
    private static final Pattern BAG_DECLARATION = Pattern.compile("^(.+) bags contain .*$");

    public static Map<String, Bag> loadBags(List<String> lines) {
        final Map<String, Bag> bags = new HashMap<>();

        for (String line : lines) {
            final Bag bag = declareBag(bags, line);
            final String[] bagDefinitions = bagDefinitions(line);

            for (String bagDefinition : bagDefinitions) {
                if (!bagDefinition.startsWith("no ")) {
                    final Matcher matcher = BAG_DEFINITION.matcher(bagDefinition);
                    if (!matcher.matches())
                        throw new IllegalArgumentException("Invalid bag definition '" + bagDefinition + "'");

                    final int count = Integer.parseInt(matcher.group(1));
                    final String color = matcher.group(2);
                    bag.addContent(createBag(bags, color), count);
                }
            }
        }

        return bags;
    }

    private static Bag declareBag(Map<String, Bag> bags, String line) {
        Bag bag = bagDeclaration(line);
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
        final Matcher matcher = BAG_DECLARATION.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid bag declaration '" + line + "'");

        return new Bag(matcher.group(1));
    }

    private static String[] bagDefinitions(String line) {
        return line.substring(line.indexOf("contain ") + 8).split(", ");
    }
}
