package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

import java.util.UUID;

public interface NeptuneCodecs {


    Codec<UUID> UUID = Codec.STRING.comapFlatMap(NeptuneCodecManager::UUID_validate, java.util.UUID::toString).stable();
}
