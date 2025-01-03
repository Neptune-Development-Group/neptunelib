package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.nio.ByteBuffer;
import java.util.UUID;

public interface NeptuneDataTypes {
    // https://wiki.fabricmc.net/tutorial:codec
    static NeptuneDataType<Integer> INTEGER(int default_value) {
        return new NeptuneDataType<>(default_value, Codec.INT, PacketCodecs.INTEGER);
    }

    static NeptuneDataType<Float> FLOAT(float default_value) {
        return new NeptuneDataType<>(default_value, Codec.FLOAT, PacketCodecs.FLOAT);
    }

    static NeptuneDataType<Boolean> BOOLEAN(boolean default_value) {
        return new NeptuneDataType<>(default_value, Codec.BOOL, PacketCodecs.BOOL);
    }

    static NeptuneDataType<String> STRING(String default_value) {
        return new NeptuneDataType<>(default_value, Codec.STRING, PacketCodecs.STRING);
    }

    static NeptuneDataType<Double> DOUBLE(double default_value) {
        return new NeptuneDataType<>(default_value, Codec.DOUBLE, PacketCodecs.DOUBLE);
    }

    static NeptuneDataType<Long> LONG(long default_value) {
        return new NeptuneDataType<>(default_value, Codec.LONG, PacketCodecs.VAR_LONG);
    }

    static NeptuneDataType<Byte> BYTE(byte default_value) {
        return new NeptuneDataType<>(default_value, Codec.BYTE, PacketCodecs.BYTE);
    }

    static NeptuneDataType<ByteBuffer> BYTE_BUFFER(ByteBuffer default_value) {
        return new NeptuneDataType<>(default_value, Codec.BYTE_BUFFER, PacketCodecs.BYTE_ARRAY);
    }

    static NeptuneDataType<Short> SHORT(short default_value) {
        return new NeptuneDataType<>(default_value, Codec.SHORT, PacketCodecs.SHORT);
    }

    static NeptuneDataType<Identifier> IDENTIFIER(Identifier default_value) {
        return new NeptuneDataType<>(default_value, Identifier.CODEC, Identifier.PACKET_CODEC);
    }

    static NeptuneDataType<BlockPos> BLOCK_POS(BlockPos default_value) {
        return new NeptuneDataType<>(default_value, BlockPos.CODEC, BlockPos.PACKET_CODEC);
    }

    static NeptuneDataType<UUID> UUID(UUID default_value) {
        return new NeptuneDataType<>(default_value, NeptuneCodecs.UUID_CODEC, NeptuneCodecs.UUID_PACKET_CODEC);
    }

    // add vec3d later
}
