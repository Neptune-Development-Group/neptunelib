package com.neptunedevelopmentteam.neptunelib.core.datagen;

import com.neptunedevelopmentteam.neptunelib.core.datagen.recipe.NeptuneRecipe;
import com.neptunedevelopmentteam.neptunelib.core.datagen.sound.NeptuneSound;
import com.neptunedevelopmentteam.neptunelib.core.datagen.translation.NeptuneTranslation;
import com.neptunedevelopmentteam.neptunelib.core.itemgroup.NeptuneItemGroup;
import com.neptunedevelopmentteam.neptunelib.core.registration.NeptuneRegistrationDatagenHookupManager;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public abstract class NeptuneDataGenerator implements NeptuneDataGeneratorEntrypoint{
    NeptuneDatagenHandler handler; // Might have to make this a supplier, just in case it's null

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        this.handler = NeptuneDatagenHandler.register(fabricDataGenerator);
        this.onInit();
        handler.generate();
    }

    public void addRecipe(NeptuneRecipe recipe) {
        this.handler.addRecipe(recipe);
    }

    public void addTranslation(String key, NeptuneTranslation... translations) {
        this.handler.addTranslation(key, translations);
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

    public void addTranslation(NeptuneItemGroup itemGroup, NeptuneTranslation... translations) {
        this.addTranslation(itemGroup.getTranslationKey(), translations);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, NeptuneRegistrationDatagenHookupManager::bootstrap_configured_feature_ores);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, NeptuneRegistrationDatagenHookupManager::bootstrap_placeable_feature_ores);
        for (NeptuneSound sound : NeptuneRegistrationDatagenHookupManager.getSounds()) {
            this.handler.addSound(sound);
        }
    }
}
