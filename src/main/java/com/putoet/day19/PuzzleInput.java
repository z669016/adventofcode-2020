package com.putoet.day19;

import java.util.ArrayList;
import java.util.List;

public class PuzzleInput {
    private final List<String> lines;

    public PuzzleInput(List<String> lines) {
        this.lines = lines;
    }

    public Rules rules() {
        final List<String> ruleLines = new ArrayList<>();
        for (int idx = 0; lines.get(idx).length() > 0; idx++) {
            ruleLines.add(lines.get(idx));
        }
        return Rules.of(ruleLines);
    }

    public List<String> messages() {
        int idx = 0;
        while (lines.get(idx).length() > 0)
            idx++;

        return lines.subList(idx + 1, lines.size());
    }
}
