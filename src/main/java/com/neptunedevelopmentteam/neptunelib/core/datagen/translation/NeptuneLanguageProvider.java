package com.neptunedevelopmentteam.neptunelib.core.datagen.translation;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class NeptuneLanguageProvider {
    private final HashMap<String, HashMap<String, String>> translations = new HashMap<>();

    private FabricDataGenerator fabricDataGenerator;
    private FabricDataGenerator.Pack pack;
    public NeptuneLanguageProvider(FabricDataGenerator fabricDataGenerator) {
        this.fabricDataGenerator = fabricDataGenerator;
        this.pack = fabricDataGenerator.createPack();
    }

    public void addTranslation(String key, String language_identifier, String translation) {
        HashMap<String, String> temp = new HashMap<>();
        temp.put(language_identifier, translation);
        this.translations.put(key, temp);
    }

    public void generate() {
        HashMap<String, NeptuneSubLanguageProvider> providers = new HashMap<>();
        translations.forEach((translation_key, map) -> {
            map.forEach((language_identifier, translation) -> {
                if (!providers.containsKey(language_identifier)) {
                    NeptuneSubLanguageProvider subLanguageProvider = pack.addProvider((FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) -> new NeptuneSubLanguageProvider(language_identifier, dataOutput, registryLookup));
                    subLanguageProvider.addTranslation(language_identifier, translation);
                    providers.put(language_identifier, subLanguageProvider);
                }
                else {
                    providers.get(language_identifier).addTranslation(translation_key, translation);
                }
            });
        });
    }
}
