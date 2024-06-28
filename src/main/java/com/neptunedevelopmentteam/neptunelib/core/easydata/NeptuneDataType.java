package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Decoder;
import com.mojang.serialization.Encoder;
import com.neptunedevelopmentteam.neptunelib.core.encoding.NeptuneEncoderDecoder;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class NeptuneDataType<A> {
    private final A default_value;
    private final Codec codec;
    private final PacketCodec packetCodec;
    private final Boolean isCustom;
    private final NeptuneEncoderDecoder<A> encoderDecoder;
    private Identifier identifier = null;



    protected NeptuneDataType(@NotNull A default_value, Codec codec, PacketCodec packetCodec) {
        this.default_value = default_value;
        this.codec = codec;
        this.packetCodec = packetCodec;
        this.isCustom = false;
        this.encoderDecoder = null;
    }

    /**
     * Creates a custom data type, requires an encoder/decoder (Everything is internally a byte array or byte buffer)
     * @param default_value The default value
     * @param encoderDecoder The encoder/decoder
     */
    public NeptuneDataType(@NotNull A default_value, NeptuneEncoderDecoder<A> encoderDecoder) {
        this.default_value = default_value;
        this.codec = Codec.BYTE_BUFFER;
        this.packetCodec = PacketCodecs.BYTE_ARRAY;
        this.isCustom = true;
        this.encoderDecoder = encoderDecoder;
    }

    public Codec getCodec() {
        return codec;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public boolean hasEncoderDecoder() {
        return encoderDecoder != null;
    }

    public NeptuneEncoderDecoder<A> getEncoderDecoder() {
        return encoderDecoder;
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
}
