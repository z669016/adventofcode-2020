package com.putoet.day18;

import org.javatuples.Pair;

public class Tokenizer {
    public static final char OPEN = '(';
    public static final char CLOSE = ')';
    public static final char PLUS = '+';
    public static final char TIMES = '*';

    private final String line;

    private int offset = 0;

    public Tokenizer(String line) {
        assert line != null;
        this.line = line;
    }

    public boolean hasMoreTokens() {
        skipSpaces();
        return offset < line.length();
    }

    private void skipSpaces() {
        while (offset < line.length() && line.charAt(offset) == ' ')
            offset++;
    }

    public Pair<Type, String> next() {
        skipSpaces();

        if (line.charAt(offset) == OPEN) {
            offset++;
            return new Pair<>(Type.OPEN, String.valueOf(OPEN));
        }

        if (line.charAt(offset) == CLOSE) {
            offset++;
            return new Pair<>(Type.CLOSE, String.valueOf(CLOSE));
        }

        if (line.charAt(offset) == PLUS || line.charAt(offset) == TIMES) {
            return new Pair<>(Type.OPERATOR, String.valueOf(line.charAt(offset++)));
        }

        if (!Character.isDigit(line.charAt(offset)))
            throw new IllegalArgumentException("Invalid character in line '" + line + "' at offset " + offset);

        final StringBuilder sb = new StringBuilder();
        while (offset < line.length() && Character.isDigit(line.charAt(offset))) {
            sb.append(line.charAt(offset));
            offset++;
        }

        return new Pair<>(Type.VALUE, sb.toString());
    }

    enum Type {
        OPEN, VALUE, OPERATOR, CLOSE
    }
}
