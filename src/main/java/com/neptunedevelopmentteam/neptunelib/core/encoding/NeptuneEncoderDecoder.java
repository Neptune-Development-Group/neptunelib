package com.neptunedevelopmentteam.neptunelib.core.encoding;

import java.nio.ByteBuffer;

public interface NeptuneEncoderDecoder<A> {
    byte[] encodeToBytes(A value);
    default ByteBuffer encodeToByteBuffer(A value) {
        return ByteBuffer.wrap(encodeToBytes(value));
    }
    A decodeFromBytes(byte[] value);
    default A decodeFromByteBuffer(ByteBuffer value) {
        return decodeFromBytes(value.array());
    }
}
