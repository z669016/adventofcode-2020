package com.putoet.day25;

public class Crypto {

    public static final long SECRET = 20_201_227L;

    public static int loopSize(long subjectNumber, long publicKey) {
        int loopSize = 0;
        long value = loopSize + 1;

        do {
            value = (value * subjectNumber) ;
            value = value % SECRET;
            loopSize++;
        } while (value != publicKey);
        return loopSize;
    }

    public static long encryptionKey(long loopSize, long publicKey) {
        long value = 1;
        for (int i = 0; i < loopSize; i++) {
            value = value * publicKey;
            value = value % SECRET;
        }

        return value;
    }

}
