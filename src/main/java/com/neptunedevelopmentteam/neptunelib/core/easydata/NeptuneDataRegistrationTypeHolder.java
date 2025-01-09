package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.mojang.serialization.Codec;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.Identifier;

public record NeptuneDataRegistrationTypeHolder(Identifier identifier, Class<?> clazz, Codec<?> codec,
                                                PacketCodec<?, ?> packetCodec) {

}
