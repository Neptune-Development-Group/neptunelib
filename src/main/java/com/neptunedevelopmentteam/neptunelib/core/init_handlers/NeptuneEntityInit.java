package com.neptunedevelopmentteam.neptunelib.core.init_handlers;

import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface NeptuneEntityInit extends ObjectInit<EntityType<?>> {
    @Override
    default Registry<EntityType<?>> getRegistry() {
        return Registries.ENTITY_TYPE;
    }
}
