package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.mojang.serialization.Codec;
import net.minecraft.component.DataComponentType;
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
    private static final HashMap<Identifier, DataComponentType> registry = new HashMap<>();

    // @TODO: Simplify this by inferring the identifier from the registration process
    public static <T> NeptuneDataType<T> create(@NotNull Identifier identifier, @NotNull T default_value) {
        if (registry.containsKey(identifier)) {
            throw new IllegalArgumentException("Identifier already registered: " + identifier);
        }
        return new NeptuneDataType<>(identifier, default_value);
    }

    public static void register(NeptuneDataType type) {
        Codec<?> codec;
        PacketCodec<?, ?> packetCodec;
        var default_value = type.getDefaultValue();
        Identifier identifier = type.getIdentifier();
        switch (default_value.getClass().getSimpleName()) {
            case "Integer":
                codec = Codec.INT;
                packetCodec = PacketCodecs.VAR_INT;
                break;
            case "Float":
                codec = Codec.FLOAT;
                packetCodec = PacketCodecs.FLOAT;
                break;
            case "Boolean":
                codec = Codec.BOOL;
                packetCodec = PacketCodecs.BOOL;
                break;
            case "String":
                codec = Codec.STRING;
                packetCodec = PacketCodecs.STRING;
                break;
            default:
                throw new IllegalArgumentException("Unsupported default value type: " + default_value.getClass());
        }
        DataComponentType dataComponentType = NeptuneDataRegistry.registerDataComponentType(identifier, builder -> builder.codec((Codec<Object>) codec).packetCodec((PacketCodec<? super RegistryByteBuf, Object>) packetCodec));
        registry.put(identifier, dataComponentType);
    }

    public static <T> DataComponentType getFromType(NeptuneDataType<T> type) {
        return registry.get(type.getIdentifier());
    }

    private static <T> DataComponentType<T> registerDataComponentType(Identifier identifier, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        String id = identifier.toString().toLowerCase(Locale.ROOT);
        return Registry.register(Registries.DATA_COMPONENT_TYPE, id,  (DataComponentType<T>) ((DataComponentType.Builder)builderOperator.apply(DataComponentType.builder())).build());
    }
}
