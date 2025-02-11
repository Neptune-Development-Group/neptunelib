package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.component.ComponentMap;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public interface NeptuneDataHolder {
    // non functional
    Map<Identifier, NeptuneData<?>> map = new HashMap<>();
    default void registerDataTypes(NeptuneDataType<?>... types) {
        map.clear();
        for(NeptuneDataType<?> type : types) {
            map.put(type.getIdentifier(), type.getDefaultData());
        }
    }

    default <T> T getData(NeptuneDataType<T> type) {
        return getData(type.getIdentifier());
    }

    default <T> T getData(Identifier identifier) {
        if(!map.containsKey(identifier)) throw new RuntimeException("Data type not registered: " + identifier);
        var val = map.get(identifier);
        if(val == null) throw new RuntimeException("Data type not registered: " + identifier);
        if (val.getValue() == null) throw new RuntimeException("Data type not registered: " + identifier);
        return (T) val.getValue();
    }

    default <T> void setData(NeptuneDataType<T> type, T data) {
        if(!map.containsKey(type.getIdentifier())) throw new RuntimeException("Data type not registered: " + type.getIdentifier());
        map.computeIfPresent(type.getIdentifier(), (k, val) -> val.setValue(data));
    }

    default <T> void setData(Identifier identifier, T data) {
        if(!map.containsKey(identifier)) throw new RuntimeException("Data type not registered: " + identifier);
        map.computeIfPresent(identifier, (k, val) -> val.setValue(data));
    }

    default JsonObject getAsJson() {
        JsonObject root = new JsonObject();
        for (Map.Entry<Identifier, NeptuneData<?>> entry : map.entrySet()) {
            JsonElement json_element = entry.getValue().getAsJson();
            root.add(entry.getKey().toString(), json_element);
        }
        return root;
    }

    default void loadFromJson(JsonObject jsonObject) {
        for (Map.Entry<Identifier, NeptuneData<?>> entry: map.entrySet()) {
            JsonElement jsonElement = jsonObject.get(entry.getKey().toString());
            if (jsonElement != null) {
                entry.getValue().setValueFromJson(jsonElement);
            }
        }
    }
}
