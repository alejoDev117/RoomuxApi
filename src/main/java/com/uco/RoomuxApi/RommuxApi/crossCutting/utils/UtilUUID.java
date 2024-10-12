package com.uco.RoomuxApi.RommuxApi.crossCutting.utils;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class UtilUUID {
    private final static String uuidString = "ffffffff-ffff-ffff-ffff-ffffffffffff";
    private final static UUID uuidDefaultValue = UUID.fromString(uuidString);

    private UtilUUID() {}

    public static String getUuidString() {
        return uuidString;
    }

    public static UUID getUuidDefaultValue() {
        return uuidDefaultValue;
    }

    public static UUID newUuid(JpaRepository repository) {
        boolean alreadyExist;
        UUID nuevoUuid;
        do {
            nuevoUuid = UUID.randomUUID();
            alreadyExist = repository.findById(nuevoUuid).isPresent();
        } while (alreadyExist);
        return nuevoUuid;
    }

    public static boolean isValidUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
