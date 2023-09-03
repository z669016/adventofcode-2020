package com.putoet.day5;

import com.putoet.grid.Point;
import com.putoet.utilities.Decoder;
import org.jetbrains.annotations.NotNull;

class SeatIDDecoder implements Decoder<Point, Integer> {
    @Override
    public Integer decode(@NotNull Point encoded) {
        return encoded.y() * 8 + encoded.x();
    }
}
