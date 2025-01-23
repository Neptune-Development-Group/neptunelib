package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;

public class NeptuneData<T> {

    private final NeptuneDataType<T> type;
    private T value;
    public NeptuneData(NeptuneDataType<T> type, T value) {
        this.type = type;
        this.value = value;
    }

    public NeptuneDataType<T> getType() {
        return type;
    }

    public T getValue() {
        return value;
    }

    public NeptuneData<T> setValue(Object value) {
        if (value.getClass() != type.getDefaultValue().getClass()) {
            throw new IllegalArgumentException("Value must be of type " + type.getDefaultValue().getClass());
        }
        this.value = (T) value;
        return this;
    }

    public JsonElement getAsJson() {
        Codec<T> codec = type.getCodec();
        DataResult<JsonElement> jsonElementDataResult = codec.encodeStart(JsonOps.INSTANCE, value);
        return jsonElementDataResult.getOrThrow();
    }

    public void setValueFromJson(JsonElement jsonElement) {
        Codec<T> codec = type.getCodec();
        DataResult<T> result = codec.parse(JsonOps.INSTANCE, jsonElement);
        setValue(result.getOrThrow());
    }
}
