package com.neptunedevelopmentteam.neptunelib.core.registration;

import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataRegistry;
import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataType;

import java.lang.reflect.Field;

public interface NeptuneDataRegistrationType extends NeptuneEasyRegistrationType<NeptuneDataType> {
    default void register(NeptuneDataType object, Field fieldSource, String namespace) {
        NeptuneDataRegistry.register(object);
    }
}
