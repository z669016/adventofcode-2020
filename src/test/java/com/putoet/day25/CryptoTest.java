package com.putoet.day25;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptoTest {

    @Test
    void loopSizeCard() {
        final long publicKey = 5_764_801L;
        final long subjectNumber = 7L;

        assertEquals(8, Crypto.loopSize(subjectNumber,publicKey));
    }

    @Test
    void loopSizeDoor() {
        final var publicKey = 17_807_724L;
        final var subjectNumber = 7L;

        assertEquals(11, Crypto.loopSize(subjectNumber,publicKey));
    }

    @Test
    void encryptionKey() {
        assertEquals(14_897_079L, Crypto.encryptionKey(8, 17_807_724L));
        assertEquals(14_897_079L, Crypto.encryptionKey(11, 5_764_801L));
    }
}