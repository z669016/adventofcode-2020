package com.putoet.day14;

class MemoryDecoder extends AbstractMemory {

    @Override
    public long set(long offset, long value) {
        assert offset >= 0;

        final var maskedValue = mask(value);
        values.put(offset, maskedValue);

        return maskedValue;
    }

    private long mask(long value) {
        for (var idx = 0; idx < mask.length(); idx++) {
            value = switch (mask.charAt(35 - idx)) {
                case '0' -> value & (MAX_MASK - bit(idx));
                case '1' -> value | bit(idx);
                case 'X' -> value;
                default -> throw new IllegalStateException("Unexpected value: " + mask.charAt(35 - idx));
            };
        }

        return value;
    }
}
