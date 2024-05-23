package com.neptunedevelopmentteam.neptunelib.core.datagen;

import com.neptunedevelopmentteam.neptunelib.core.datagen.translation.NeptuneTranslation;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public abstract class NeptuneDataGenerator implements NeptuneDataGeneratorEntrypoint{
    NeptuneDatagenHandler handler; // Might have to make this a supplier, just in case it's null

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        this.handler = NeptuneDatagenHandler.register(fabricDataGenerator);
    }

    public NeptuneDataGenerator() {
        this.handler = NeptuneDatagenHandler.get(this.getModId());
        this.onInit();
        handler.generate();
    }

    public void addTranslation(String key, NeptuneTranslation... translations) {

    }
}
