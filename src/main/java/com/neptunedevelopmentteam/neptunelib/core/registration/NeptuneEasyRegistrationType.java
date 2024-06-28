package com.neptunedevelopmentteam.neptunelib.core.registration;

import org.apache.commons.lang3.NotImplementedException;

import java.lang.reflect.Field;
import java.util.Locale;

public interface NeptuneEasyRegistrationType<T> {

    default <T> void register(T object, Field fieldSource, String namespace) {
        throw new NotImplementedException("Must be implemented through child interface");
    }

    static String getName(Field fieldSource) {
        String name = NeptuneRegistrationManager.getCustomNameFromField(fieldSource);
        return name.toLowerCase(Locale.ROOT);
    }

}
