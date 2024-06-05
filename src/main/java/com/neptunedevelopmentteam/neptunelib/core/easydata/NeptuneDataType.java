package com.neptunedevelopmentteam.neptunelib.core.easydata;

import net.minecraft.component.DataComponentType;
import net.minecraft.util.Identifier;

public class NeptuneDataType<A> {

    private final Identifier identifier;
    private final A default_value;

    protected NeptuneDataType(Identifier identifier, A default_value) {
        this.identifier = identifier;
        this.default_value = default_value;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public A getDefaultValue() {
        return default_value;
    }

    public DataComponentType getDataComponentType() {
        return NeptuneDataRegistry.getFromType(this);
    }
}
