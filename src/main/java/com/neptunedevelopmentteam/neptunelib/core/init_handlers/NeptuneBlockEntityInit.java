package com.neptunedevelopmentteam.neptunelib.core.init_handlers;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface NeptuneBlockEntityInit extends ObjectInit<BlockEntityType<?>> {
    @Override
    default Registry<BlockEntityType<?>> getRegistry() {
        return Registries.BLOCK_ENTITY_TYPE;
    }
}
