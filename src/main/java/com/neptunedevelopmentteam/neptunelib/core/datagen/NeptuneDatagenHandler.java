package com.neptunedevelopmentteam.neptunelib.core.datagen;

import com.neptunedevelopmentteam.neptunelib.core.datagen.sound.NeptuneSound;
import com.neptunedevelopmentteam.neptunelib.core.datagen.sound.NeptuneSoundProvider;
import com.neptunedevelopmentteam.neptunelib.core.datagen.translation.NeptuneLanguageProvider;
import com.neptunedevelopmentteam.neptunelib.core.datagen.translation.NeptuneTranslation;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryWrapper;

import java.util.HashMap;

public class NeptuneDatagenHandler {
    private final String mod_id;
    private FabricDataGenerator fabricDataGenerator;
    private static  HashMap<String, NeptuneDatagenHandler> datagenHandlers = new HashMap<>();
    private NeptuneLanguageProvider languageProvider;
    private NeptuneSoundProvider soundProvider;
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
        this.soundProvider = fabricDataGenerator.createPack().addProvider(NeptuneSoundProvider::new);
    }

    public void addSound(NeptuneSound neptuneSound) {
        this.soundProvider.addSound(neptuneSound);
    }

    public void addTranslation(String key, NeptuneTranslation... translations) {
        for (NeptuneTranslation translation : translations) {
            translation.getLanguageIdentifiers().forEach(identifier -> languageProvider.addTranslation(key, identifier, translation.getTranslation()));
        }
    }

    public void addTranslation(Item item, NeptuneTranslation... translations) {
        this.addTranslation(item.getTranslationKey(), translations);
    }
    public void addTranslation(Block block, NeptuneTranslation... translations) {
        this.addTranslation(block.getTranslationKey(), translations);
    }

    public void addTranslation(NeptuneSound sound, NeptuneTranslation... translations) {
        this.addTranslation(sound.getTranslationKey(), translations);
    }

    public void generate() {
        languageProvider.generate();
    }
}
