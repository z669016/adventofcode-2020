package com.putoet.day19;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

record PuzzleInput(@NotNull List<String> lines) {
    public Rules rules() {
        final var ruleLines = new ArrayList<String>();
        for (int idx = 0; !lines.get(idx).isEmpty(); idx++) {
            ruleLines.add(lines.get(idx));
        }
        return Rules.of(ruleLines);
    }

    public List<String> messages() {
        var idx = 0;
        while (!lines.get(idx).isEmpty())
            idx++;

        return lines.subList(idx + 1, lines.size());
    }
}
