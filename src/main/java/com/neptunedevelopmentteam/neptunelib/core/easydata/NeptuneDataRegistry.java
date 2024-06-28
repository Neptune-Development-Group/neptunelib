package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.UnaryOperator;

public class NeptuneDataRegistry {
    private static final HashMap<Identifier, ComponentType> registry = new HashMap<>();

    public static void register(NeptuneDataType type, Identifier identifier) {
        Codec<?> codec = type.getCodec();
        PacketCodec<?, ?> packetCodec = type.getPacketCodec();
        if (registry.containsKey(identifier)) throw new IllegalArgumentException("Data type already registered with this identifier: " + identifier);
        ComponentType ComponentType = NeptuneDataRegistry.registerComponentType(identifier, builder -> builder.codec((Codec<Object>) codec).packetCodec((PacketCodec<? super RegistryByteBuf, Object>) packetCodec));
        registry.put(identifier, ComponentType);
        type.setIdentifier(identifier);
    }

    public static <T> ComponentType getFromType(NeptuneDataType<T> type) {
        return registry.get(type.getIdentifier());
    }

    private static <T> ComponentType<T> registerComponentType(Identifier identifier, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        String id = identifier.toString().toLowerCase(Locale.ROOT);
        return Registry.register(Registries.DATA_COMPONENT_TYPE, id,  (ComponentType<T>) ((ComponentType.Builder)builderOperator.apply(ComponentType.builder())).build());
    }
}
