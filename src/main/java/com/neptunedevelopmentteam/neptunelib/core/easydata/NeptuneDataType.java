package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class NeptuneDataType<A> {
    private final A default_value;
    private final Codec codec;
    private final PacketCodec packetCodec;
    private final Boolean isCustom;
    private Identifier identifier = null;



    protected NeptuneDataType(@NotNull A default_value, NeptuneDataRegistrationTypeHolder registrationType) {
        this.default_value = default_value;
        this.codec = registrationType.codec();
        this.packetCodec = registrationType.packetCodec();
        this.isCustom = false;
    }

    public Codec getCodec() {
        return codec;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public PacketCodec getPacketCodec() {
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
