package com.putoet.day22;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.IntStream;

class Player implements Iterator<Integer> {
    private final int id;
    private final LinkedList<Integer> cards;

    public Player(int id, @NotNull List<Integer> cards) {
        this.id = id;
        this.cards = new LinkedList<>(cards);
    }

    public int id() {
        return id;
    }

    public boolean lost() {
        return cards.isEmpty();
    }

    public void add(int card) {
        cards.add(card);
    }

    public long score() {
        final var size = cards.size();
        return IntStream.range(0, size)
                .map(index -> cards.get(index) * (size - index))
                .sum();
    }

    public List<Integer> cards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public boolean hasNext() {
        return !cards.isEmpty();
    }

    @Override
    public Integer next() {
        return cards.removeFirst();
    }

    @Override
    public String toString() {
        return "Player " + id + "'s deck: " + cards;
    }
}
