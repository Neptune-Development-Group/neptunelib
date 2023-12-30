package com.neptunedevelopmentteam.neptunelib.core.init_handlers;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;

public interface NeptuneSoundEventInit extends ObjectInit<SoundEvent>{
    @Override
    default Registry<SoundEvent> getRegistry() {
        return Registries.SOUND_EVENT;
    }
}
