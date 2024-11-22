package com.neptunedevelopmentteam.neptunelib.core.easydata;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.PrimitiveCodec;
import net.minecraft.util.Identifier;

import java.util.stream.Collectors;

public interface NeptuneCodec<A> extends Codec<A> {


    Codec<Identifier> IDENTIFIER = new Codec<Identifier>() {

        @Override
        public <T> DataResult<T> encode(Identifier input, DynamicOps<T> ops, T prefix) {
            String identifier_string = input.toString();
            DataResult<T> result = (DataResult<T>) DataResult.success(identifier_string);
            return result;
        }

        @Override
        public <T> DataResult<Pair<Identifier, T>> decode(DynamicOps<T> ops, T input) {
            return null;
        }
    };
}
