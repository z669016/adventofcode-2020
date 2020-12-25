package com.putoet.day25;

public class Day25 {
    public static void main(String[] args) {
        final long cardPublicKey = 7_573_546L;
        final long doorPublicKey = 17_786_549L;

        part1(cardPublicKey, doorPublicKey);
    }

    private static void part1(long cardPublicKey, long doorPublicKey) {
        final int cardLoopSize = Crypto.loopSize(7, cardPublicKey);
        final int doorLoopSize = Crypto.loopSize(7, doorPublicKey);

        final long cardEncryptionKey = Crypto.encryptionKey(cardLoopSize, doorPublicKey);
        final long doorEncryptionKey = Crypto.encryptionKey(doorLoopSize, cardPublicKey);

        System.out.println("Generated encryption keys are " + (cardEncryptionKey != doorEncryptionKey ? "not " : "") + "a match");
        if (cardEncryptionKey == doorEncryptionKey)
            System.out.println("Encryption key is " + cardEncryptionKey);
    }
}
