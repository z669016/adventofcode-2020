package com.putoet.day5;

import com.putoet.utilities.Decoder;
import com.putoet.utilities.Point;

public class BoardingPassDecoder implements Decoder<String, Point> {

    @Override
    public Point decode(String encoded) {
        assert encoded != null && encoded.length() == 10;
        final String row = encoded.substring(0, 7);
        final String column = encoded.substring(7);

        return Point.of(decodeColumn(column), decodeRow(row));
    }

    private int decodeRow(String encodedRow) {
        return decode(encodedRow, 'B');
    }

    private int decodeColumn(String encodedColumn) {
        return decode(encodedColumn, 'R');
    }

    private int decode(String encoded, char ifValue) {
        int decoded = 0;

        encoded = new StringBuilder(encoded).reverse().toString();
        for (int x = 0; x < encoded.length(); x++) {
            if (ifValue == encoded.charAt(x))
                decoded = decoded | (int) Math.pow(2, x);
        }

        return decoded;
    }
}
