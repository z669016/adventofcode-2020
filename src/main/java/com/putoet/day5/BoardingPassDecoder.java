package com.putoet.day5;

import com.putoet.grid.Point;
import com.putoet.utilities.Decoder;
import org.jetbrains.annotations.NotNull;

class BoardingPassDecoder implements Decoder<String, Point> {

    @Override
    public Point decode(@NotNull String encoded) {
        assert encoded.length() == 10;

        final var row = encoded.substring(0, 7);
        final var column = encoded.substring(7);

        return Point.of(decodeColumn(column), decodeRow(row));
    }

    private int decodeRow(String encodedRow) {
        return decode(encodedRow, 'B');
    }

    private int decodeColumn(String encodedColumn) {
        return decode(encodedColumn, 'R');
    }

    private int decode(String encoded, char ifValue) {
        var decoded = 0;

        encoded = new StringBuilder(encoded).reverse().toString();
        for (var x = 0; x < encoded.length(); x++) {
            if (ifValue == encoded.charAt(x))
                decoded = decoded | (int) Math.pow(2, x);
        }

        return decoded;
    }
}
