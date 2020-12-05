package com.putoet.day5;

import com.putoet.utilities.Decoder;
import com.putoet.utilities.Point;

public class SeatIDDecoder implements Decoder<Point,Integer> {
    @Override
    public Integer decode(Point encoded) {
        return encoded.y * 8 + encoded.x;
    }
}
