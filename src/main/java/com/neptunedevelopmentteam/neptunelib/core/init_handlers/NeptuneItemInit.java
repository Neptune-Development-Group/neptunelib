package com.neptunedevelopmentteam.neptunelib.core.init_handlers;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface NeptuneItemInit extends ObjectInit<Item>{
    @Override
    default Registry<Item> getRegistry() {
        return Registries.ITEM;
    }
}
