package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class NeptuneDataType<A> {
    private final A default_value;
    private final Codec<A> codec;
    private final PacketCodec<ByteBuf, A> packetCodec;
    private final Boolean isCustom;
    private Identifier identifier = null;



    protected NeptuneDataType(@NotNull A default_value, NeptuneDataRegistrationTypeHolder registrationType) {
        this.default_value = default_value;
        this.codec = registrationType.codec();
        this.packetCodec = registrationType.packetCodec();
        this.isCustom = false;
    }

    public Codec<A> getCodec() {
        return codec;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public PacketCodec<ByteBuf, A> getPacketCodec() {
        return packetCodec;
    }

    public A getDefaultValue() {
        return default_value;
    }

    public Identifier getIdentifier() {
        if (identifier == null) throw new RuntimeException("Data type not registered: " + this);
        return identifier;
    }

    protected void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public ComponentType getDataComponentType() {
        return NeptuneDataRegistry.getFromType(this);
    }

    public NeptuneData<A> getDefaultData() {
        return new NeptuneData<>(this, default_value);
    }

    public NeptuneData<A> createData(A value) {
        return new NeptuneData<>(this, value);
    }
}
