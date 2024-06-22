package com.neptunedevelopmentteam.neptunelib.core.registration;

import com.neptunedevelopmentteam.neptunelib.core.datagen.sound.NeptuneSound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;

public interface NeptuneSoundRegistrationType extends NeptuneEasyRegistrationType<NeptuneSound> {

    static void register(NeptuneSound object, Field fieldSource, String namespace) {
        Registry.register(Registries.SOUND_EVENT, object.getSoundIdentifier(), object.getSoundEvent());
        NeptuneRegistrationDatagenHookupManager.addSound(object);
    }
}
