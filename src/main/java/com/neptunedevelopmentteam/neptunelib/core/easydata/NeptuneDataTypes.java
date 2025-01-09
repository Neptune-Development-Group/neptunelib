package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.UUID;

public interface NeptuneDataTypes {

    // https://wiki.fabricmc.net/tutorial:codec
    static NeptuneDataType<Integer> INTEGER(int default_value) {
        return new NeptuneDataType<>(default_value, NeptuneDataRegistrationTypes.INTEGER);
    }

    static NeptuneDataType<Float> FLOAT(float default_value) {
        return new NeptuneDataType<>(default_value, NeptuneDataRegistrationTypes.FLOAT);
    }

    static NeptuneDataType<Boolean> BOOLEAN(boolean default_value) {
        return new NeptuneDataType<>(default_value, NeptuneDataRegistrationTypes.BOOLEAN);
    }

    static NeptuneDataType<String> STRING(String default_value) {
        return new NeptuneDataType<>(default_value, NeptuneDataRegistrationTypes.STRING);
    }

    static NeptuneDataType<Double> DOUBLE(double default_value) {
        return new NeptuneDataType<>(default_value, NeptuneDataRegistrationTypes.DOUBLE);
    }

    static NeptuneDataType<Long> LONG(long default_value) {
        return new NeptuneDataType<>(default_value, NeptuneDataRegistrationTypes.LONG);
    }

    static NeptuneDataType<Byte> BYTE(byte default_value) {
        return new NeptuneDataType<>(default_value, NeptuneDataRegistrationTypes.BYTE);
    }

    static NeptuneDataType<ByteBuffer> BYTE_BUFFER(ByteBuffer default_value) {
        return new NeptuneDataType<>(default_value, NeptuneDataRegistrationTypes.BYTE_BUFFER);
    }

    static NeptuneDataType<Short> SHORT(short default_value) {
        return new NeptuneDataType<>(default_value, NeptuneDataRegistrationTypes.SHORT);
    }

    static NeptuneDataType<Identifier> IDENTIFIER(Identifier default_value) {
        return new NeptuneDataType<>(default_value, NeptuneDataRegistrationTypes.IDENTIFIER);
    }

    static NeptuneDataType<BlockPos> BLOCK_POS(BlockPos default_value) {
        return new NeptuneDataType<>(default_value, NeptuneDataRegistrationTypes.BLOCK_POS);
    }

    static NeptuneDataType<UUID> UUID(UUID default_value) {
        return new NeptuneDataType<>(default_value, NeptuneDataRegistrationTypes.UUID);
    }

    // add vec3d later
}
