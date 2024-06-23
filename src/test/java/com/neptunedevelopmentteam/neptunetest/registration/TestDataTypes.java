package com.neptunedevelopmentteam.neptunetest.registration;

import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataRegistry;
import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataType;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneDataRegistrationType;
import net.minecraft.util.Identifier;

public class TestDataTypes implements NeptuneDataRegistrationType {
    public static final NeptuneDataType<Integer> INTEGER_DATA_TYPE = NeptuneDataRegistry.create(Identifier.of("neptunetest", "integer_data_type"), 0);

    public static final NeptuneDataType<String> STRING_DATA_TYPE = NeptuneDataRegistry.create(Identifier.of("neptunetest", "string_data_type"), "");

    public static final NeptuneDataType<Boolean> BOOLEAN_DATA_TYPE = NeptuneDataRegistry.create(Identifier.of("neptunetest", "boolean_data_type"), false);
}
