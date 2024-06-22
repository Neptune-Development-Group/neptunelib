package com.neptunedevelopmentteam.neptunelib.core.registration;

import org.apache.commons.lang3.NotImplementedException;

import java.lang.reflect.Field;

public interface NeptuneEasyRegistrationType<T> {

    static <T> void register(T object, Field fieldSource, String namespace) {
        throw new NotImplementedException("Must be implemented through child interface");
    }

    static String getName(Field fieldSource) {
        return NeptuneRegistrationManager.getCustomNameFromField(fieldSource);
    }

}
