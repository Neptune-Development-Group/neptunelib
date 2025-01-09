package com.neptunedevelopmentteam.neptunelib.core.easydata;

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

    public void setValue(Object value) {
        if (value.getClass() != type.getDefaultValue().getClass()) {
            throw new IllegalArgumentException("Value must be of type " + type.getDefaultValue().getClass());
        }
        this.value = (T) value;
    }
}
