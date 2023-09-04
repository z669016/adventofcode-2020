package com.putoet.day25;

class Crypto {
    public static final long SECRET = 20_201_227L;

    public static int loopSize(long subjectNumber, long publicKey) {
        var loopSize = 0;
        var value = loopSize + 1L;

        do {
            value = (value * subjectNumber) ;
            value = value % SECRET;
            loopSize++;
        } while (value != publicKey);
        return loopSize;
    }

    public static long encryptionKey(long loopSize, long publicKey) {
        var value = 1L;
        for (var i = 0; i < loopSize; i++) {
            value = value * publicKey;
            value = value % SECRET;
        }

        return value;
    }
}
