package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.mojang.serialization.DataResult;

import java.util.UUID;

public class NeptuneCodecManager {

    static DataResult<UUID> UUID_validate(String uuid) {
        try {
            return DataResult.success(UUID.fromString(uuid));
        } catch (IllegalArgumentException e) {
            return DataResult.error(() -> "Invalid UUID: " + uuid);
        }
    }
}
