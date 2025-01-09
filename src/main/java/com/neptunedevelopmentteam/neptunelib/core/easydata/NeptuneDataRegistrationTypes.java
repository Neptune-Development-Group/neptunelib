package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.mojang.serialization.Codec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.nio.ByteBuffer;

public interface NeptuneDataRegistrationTypes {
    NeptuneDataRegistrationTypeHolder INTEGER = NeptuneDataTypeRegistry.register(Identifier.of("minecraft", "integer"), Integer.class, Codec.INT, PacketCodecs.INTEGER);
    NeptuneDataRegistrationTypeHolder FLOAT = NeptuneDataTypeRegistry.register(Identifier.of("minecraft", "float"), Float.class, Codec.FLOAT, PacketCodecs.FLOAT);
    NeptuneDataRegistrationTypeHolder BOOLEAN = NeptuneDataTypeRegistry.register(Identifier.of("minecraft", "boolean"), Boolean.class, Codec.BOOL, PacketCodecs.BOOL);
    NeptuneDataRegistrationTypeHolder STRING = NeptuneDataTypeRegistry.register(Identifier.of("minecraft", "string"), String.class, Codec.STRING, PacketCodecs.STRING);
    NeptuneDataRegistrationTypeHolder DOUBLE = NeptuneDataTypeRegistry.register(Identifier.of("minecraft", "double"), Double.class, Codec.DOUBLE, PacketCodecs.DOUBLE);
    NeptuneDataRegistrationTypeHolder LONG = NeptuneDataTypeRegistry.register(Identifier.of("minecraft", "long"), Long.class, Codec.LONG, PacketCodecs.LONG);
    NeptuneDataRegistrationTypeHolder BYTE = NeptuneDataTypeRegistry.register(Identifier.of("minecraft", "byte"), Byte.class, Codec.BYTE, PacketCodecs.BYTE);
    NeptuneDataRegistrationTypeHolder BYTE_BUFFER = NeptuneDataTypeRegistry.register(Identifier.of("minecraft", "byte_buffer"), ByteBuffer.class, Codec.BYTE_BUFFER, PacketCodecs.BYTE_ARRAY);
    NeptuneDataRegistrationTypeHolder SHORT = NeptuneDataTypeRegistry.register(Identifier.of("minecraft", "short"), Short.class, Codec.SHORT, PacketCodecs.SHORT);
    NeptuneDataRegistrationTypeHolder IDENTIFIER = NeptuneDataTypeRegistry.register(Identifier.of("minecraft", "identifier"), Identifier.class, Identifier.CODEC, Identifier.PACKET_CODEC);
    NeptuneDataRegistrationTypeHolder BLOCK_POS = NeptuneDataTypeRegistry.register(Identifier.of("minecraft", "block_pos"), BlockPos.class, BlockPos.CODEC, BlockPos.PACKET_CODEC);
    NeptuneDataRegistrationTypeHolder UUID = NeptuneDataTypeRegistry.register(Identifier.of("minecraft", "uuid"), java.util.UUID.class, NeptuneCodecs.UUID, NeptunePacketCodecs.UUID);
}
