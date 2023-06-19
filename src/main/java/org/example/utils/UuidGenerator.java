package org.example.utils;

import java.util.UUID;

public class UuidGenerator {
    public static String generateShortUniqueId() {
        UUID uuid = UUID.randomUUID();
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();

        String uniqueId = Long.toHexString(mostSignificantBits ^ leastSignificantBits);
        return uniqueId.substring(0, 4); // Return the first 8 characters for a short identifier
    }
}
