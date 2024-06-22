package com.neptunedevelopmentteam.neptunelib.core.registration;

import com.neptunedevelopmentteam.neptunelib.core.datagen.worldgen.NeptuneOre;

import java.lang.reflect.Field;

public interface NeptuneOreRegistrationType extends NeptuneEasyRegistrationType<NeptuneOre> {

    static void register(NeptuneOre object, Field fieldSource, String namespace) {
        NeptuneRegistrationDatagenHookupManager.addNeptuneOre(object);
        object.generate();
    }
}
