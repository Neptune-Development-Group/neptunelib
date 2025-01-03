package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.mojang.serialization.*;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

import java.util.UUID;

public interface NeptuneCodecs {


    Codec<UUID> UUID_CODEC = Codec.STRING.comapFlatMap(NeptuneCodecManager::UUID_validate, java.util.UUID::toString).stable();
    PacketCodec<ByteBuf, UUID> UUID_PACKET_CODEC = PacketCodecs.STRING.xmap(UUID::fromString, UUID::toString);
}
