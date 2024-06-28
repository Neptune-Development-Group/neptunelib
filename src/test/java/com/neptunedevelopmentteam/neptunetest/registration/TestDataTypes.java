package com.neptunedevelopmentteam.neptunetest.registration;

import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataRegistry;
import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataType;
import com.neptunedevelopmentteam.neptunelib.core.easydata.NeptuneDataTypes;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneDataRegistrationType;
import net.minecraft.util.Identifier;

public class TestDataTypes implements NeptuneDataRegistrationType {
    public static final NeptuneDataType<Integer> INTEGER_DATA_TYPE = NeptuneDataTypes.INTEGER(0);

    public static final NeptuneDataType<String> STRING_DATA_TYPE = NeptuneDataTypes.STRING("");

    public static final NeptuneDataType<Boolean> BOOLEAN_DATA_TYPE = NeptuneDataTypes.BOOLEAN(true);
}
