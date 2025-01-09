package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.mojang.serialization.Codec;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.List;

public class NeptuneDataTypeRegistry {
    private static final HashMap<Identifier, NeptuneDataRegistrationTypeHolder> registry = new HashMap<>();

    public static NeptuneDataRegistrationTypeHolder register(Identifier identifier, Class<?> clazz, Codec<?> codec, PacketCodec<?, ?> packetCodec) {
        if (registry.containsKey(identifier)) throw new IllegalArgumentException("Data registration type already registered with this identifier: " + identifier);
        var dataRegistrationType = new NeptuneDataRegistrationTypeHolder(identifier, clazz, codec, packetCodec);
        registry.put(identifier, dataRegistrationType);
        return dataRegistrationType;
    }

    public static List<NeptuneDataRegistrationTypeHolder> getTypes() {return registry.values().stream().toList();}

    public static NeptuneDataRegistrationTypeHolder get(Identifier identifier) {return registry.get(identifier);}
}

