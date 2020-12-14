package com.putoet.day14;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryDecoderV2 extends AbstractMemory {

    @Override
    public long set(long offset, long value) {
        assert offset >= 0;

        final List<Long> offsets = addresses(offset);
        offsets.forEach(address -> values.put(address, value));

        return value;
    }

    private List<Long> addresses(long offset) {
        List<String> addresses = new ArrayList<>();

        for (int idx = 0; idx < mask.length(); idx++) {
            switch (mask.charAt(idx)) {
                case '0' -> addresses = append(addresses, bit(35 - idx, offset));
                case '1' -> addresses = append(addresses, '1');
                case 'X' -> addresses = append(addresses, '0', '1');
            }
        }

//        System.out.printf("%36s%n", Long.toBinaryString(offset));
//        System.out.println(mask);
//        addresses.forEach(System.out::println);
//        System.out.println();

        return addresses.stream()
                .mapToLong(address -> Long.parseLong(address, 2))
                .boxed()
                .collect(Collectors.toList());
    }

    private char bit(int idx, long offset) {
        return ((offset & bit(idx)) != 0) ? '1' : '0';
    }

    private List<String> append(List<String> addresses, char ... bits) {
        if (bits == null || bits.length == 0)
            return addresses;

        if (bits.length == 1) {
            if (addresses.size() == 0) {
                addresses.add(String.valueOf(bits[0]));
            } else {
                for (int idx = 0; idx < addresses.size(); idx++) {
                    addresses.set(idx, addresses.get(idx) + bits[0]);
                }
            }
            return addresses;
        }

        final List<String> newAddresses = new ArrayList<>();
        for (char bit : bits) {
            if (addresses.size() == 0) {
                newAddresses.add(String.valueOf(bit));
            } else {
                for (String address : addresses) {
                    newAddresses.add(address + bit);
                }
            }
        }
        return newAddresses;
    }
}
