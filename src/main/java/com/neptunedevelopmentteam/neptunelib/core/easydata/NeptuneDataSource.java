package com.neptunedevelopmentteam.neptunelib.core.easydata;

public interface NeptuneDataSource {

     default <A> A getOrCreateData(NeptuneDataType<A> type) {
        A data = neptunelib$getData(type);
        if (data != null) return data;
        data = type.getDefaultValue();
        if (data == null) throw new NullPointerException("Default value for " + type.getIdentifier() + " is null");
        neptunelib$setData(type, data);
        return data;
    }

    default <A> A getOrCreateData(NeptuneDataType<A> type, A default_value) {
        A data = neptunelib$getData(type);
        if (data != null) return data;
        data = default_value;
        neptunelib$setData(type, data);
        return data;
    }

    default <A> A neptunelib$getData(NeptuneDataType<A> type) {
        throw new UnsupportedOperationException("Implemented through mixin");
    }

    default <A> A getDataOrElse(NeptuneDataType<A> type, A default_value) {
        A data = neptunelib$getData(type);
        return data == null ? default_value : data;
    }

    default <A> A getDataOrDefault(NeptuneDataType<A> type) {
        return getDataOrElse(type, type.getDefaultValue());
    }

    default <A> void neptunelib$setData(NeptuneDataType<A> type, A data) {
        throw new UnsupportedOperationException("Implemented through mixin");
    }
}
