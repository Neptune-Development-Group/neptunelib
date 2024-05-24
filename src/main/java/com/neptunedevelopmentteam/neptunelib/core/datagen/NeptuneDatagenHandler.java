package com.neptunedevelopmentteam.neptunelib.core.datagen;

import com.neptunedevelopmentteam.neptunelib.core.datagen.recipe.NeptuneRecipe;
import com.neptunedevelopmentteam.neptunelib.core.datagen.recipe.NeptuneRecipeProvider;
import com.neptunedevelopmentteam.neptunelib.core.datagen.sound.NeptuneSound;
import com.neptunedevelopmentteam.neptunelib.core.datagen.sound.NeptuneSoundProvider;
import com.neptunedevelopmentteam.neptunelib.core.datagen.translation.NeptuneLanguageProvider;
import com.neptunedevelopmentteam.neptunelib.core.datagen.translation.NeptuneTranslation;
import com.neptunedevelopmentteam.neptunelib.core.datagen.worldgen.NeptuneWorldGenProvider;
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
    private NeptuneRecipeProvider recipeProvider;
    public static NeptuneDatagenHandler register(FabricDataGenerator fabricDataGenerator) {
        datagenHandlers.put(fabricDataGenerator.getModId(), new NeptuneDatagenHandler(fabricDataGenerator));
        return get(fabricDataGenerator.getModId());
    }

    public static NeptuneDatagenHandler get(String mod_id) {
        return datagenHandlers.get(mod_id);
    }

    public NeptuneDatagenHandler(FabricDataGenerator fabricDataGenerator) {
        this.fabricDataGenerator = fabricDataGenerator;
        this.mod_id = fabricDataGenerator.getModId();
        this.languageProvider = new NeptuneLanguageProvider(fabricDataGenerator);
        var pack = fabricDataGenerator.createPack();
        this.soundProvider = pack.addProvider(NeptuneSoundProvider::new);
        pack.addProvider(NeptuneWorldGenProvider::new);
        this.recipeProvider = pack.addProvider(NeptuneRecipeProvider::new);
    }

    public void addSound(NeptuneSound neptuneSound) {
        this.soundProvider.addSound(neptuneSound);
    }

    public void addTranslation(String key, NeptuneTranslation... translations) {
        for (NeptuneTranslation translation : translations) {
            translation.getLanguageIdentifiers().forEach(identifier -> languageProvider.addTranslation(key, identifier, translation.getTranslation()));
        }
    }

    public void generate() {
        languageProvider.generate();
    }

    public void addRecipe(NeptuneRecipe recipe) {
        this.recipeProvider.addRecipe(recipe);
    }
}
