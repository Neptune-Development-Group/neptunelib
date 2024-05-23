package com.neptunedevelopmentteam.neptunelib.core.datagen.translation;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.data.DataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class NeptuneSubLanguageProvider extends FabricLanguageProvider {
    private HashMap<String, String> translations = new HashMap<>();
    private FabricDataOutput dataOutput;
    private CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup;

    protected NeptuneSubLanguageProvider(String language_identifier, FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, language_identifier, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translations.forEach(translationBuilder::add);
    }

    public void addTranslation(String key, String translation) {
        translations.put(key, translation);
    }
}
