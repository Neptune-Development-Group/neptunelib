package com.neptunedevelopmentteam.neptunelib.core.easydata;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

import java.util.UUID;

public interface NeptunePacketCodecs {
    PacketCodec<ByteBuf, java.util.UUID> UUID = PacketCodecs.STRING.xmap(java.util.UUID::fromString, java.util.UUID::toString);
}
