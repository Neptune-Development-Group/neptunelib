package com.neptunedevelopmentteam.neptunelib.core.init_handlers;

import net.minecraft.registry.Registry;

public interface ObjectInit<T> {
    Registry<T> getRegistry();
    static <T> void register(Class<? extends ObjectInit<T>> init_container, String namespace) {
        InitHandler.register(init_container, namespace);
    }
}
