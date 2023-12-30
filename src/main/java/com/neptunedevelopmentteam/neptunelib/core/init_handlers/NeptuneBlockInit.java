package com.neptunedevelopmentteam.neptunelib.core.init_handlers;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface NeptuneBlockInit extends ObjectInit<Block> {

    @Override
    default Registry<Block> getRegistry() {
        return Registries.BLOCK;
    }
}
