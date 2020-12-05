package com.putoet.utilities;

public interface Decoder<T,R> {
    R decode(T encoded);
}
