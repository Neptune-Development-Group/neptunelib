package com.neptunedevelopmentteam.neptunetest.registration;

import com.neptunedevelopmentteam.neptunelib.core.datagen.sound.NeptuneSound;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneSoundRegistrationType;
import net.minecraft.util.Identifier;

public class TestSounds implements NeptuneSoundRegistrationType {
    public static final NeptuneSound TEST_SOUND = new NeptuneSound(Identifier.of("neptunetest", "test_sound"),
            Identifier.of("neptunetest", "test_sound_1"),
            Identifier.of("minecraft", "ambient.cave.cave1"));
}
