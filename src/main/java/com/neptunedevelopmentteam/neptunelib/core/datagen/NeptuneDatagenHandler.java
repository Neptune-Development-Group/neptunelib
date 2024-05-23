package com.neptunedevelopmentteam.neptunelib.core.datagen;

import com.neptunedevelopmentteam.neptunelib.core.datagen.translation.NeptuneLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryWrapper;

import java.util.HashMap;

public class NeptuneDatagenHandler {
    private final String mod_id;
    private FabricDataGenerator fabricDataGenerator;
    private static  HashMap<String, NeptuneDatagenHandler> datagenHandlers = new HashMap<>();
    private NeptuneLanguageProvider languageProvider;
    public static NeptuneDatagenHandler register(FabricDataGenerator fabricDataGenerator) {
        datagenHandlers.put(fabricDataGenerator.getModId(), new NeptuneDatagenHandler(fabricDataGenerator));
        return new NeptuneDatagenHandler(fabricDataGenerator);
    }

    public static NeptuneDatagenHandler get(String mod_id) {
        return datagenHandlers.get(mod_id);
    }

    public NeptuneDatagenHandler(FabricDataGenerator fabricDataGenerator) {
        this.fabricDataGenerator = fabricDataGenerator;
        this.mod_id = fabricDataGenerator.getModId();
        this.languageProvider = new NeptuneLanguageProvider(fabricDataGenerator);
    }

    public void addTranslation(String key, String language_identifier, String translation) {
        this.languageProvider.addTranslation(key, language_identifier, translation);
    }

    public void generate() {
        languageProvider.generate();
    }
}
