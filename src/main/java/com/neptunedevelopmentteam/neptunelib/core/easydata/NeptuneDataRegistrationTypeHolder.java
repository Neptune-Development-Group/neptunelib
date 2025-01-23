package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.Identifier;

public record NeptuneDataRegistrationTypeHolder<A>(Identifier identifier, Class<?> clazz, Codec<A> codec,
                                                PacketCodec<ByteBuf, A> packetCodec) {

}
