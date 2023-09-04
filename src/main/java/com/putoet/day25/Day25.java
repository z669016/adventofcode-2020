package com.putoet.day25;

import com.putoet.utils.Timer;

public class Day25 {
    public static void main(String[] args) {
        final long cardPublicKey = 7_573_546L;
        final long doorPublicKey = 17_786_549L;

        Timer.run(() -> part1(cardPublicKey, doorPublicKey));
    }

    private static void part1(long cardPublicKey, long doorPublicKey) {
        final var cardLoopSize = Crypto.loopSize(7, cardPublicKey);
        final var doorLoopSize = Crypto.loopSize(7, doorPublicKey);

        final var cardEncryptionKey = Crypto.encryptionKey(cardLoopSize, doorPublicKey);
        final var doorEncryptionKey = Crypto.encryptionKey(doorLoopSize, cardPublicKey);

        System.out.println("Generated encryption keys are " + (cardEncryptionKey != doorEncryptionKey ? "not " : "") + "a match");
        if (cardEncryptionKey == doorEncryptionKey)
            System.out.println("Encryption key is " + cardEncryptionKey);
    }
}
