package com.neptunedevelopmentteam.neptunetest;

import com.neptunedevelopmentteam.neptunelib.core.datagen.NeptuneDataGenerator;
import com.neptunedevelopmentteam.neptunelib.core.datagen.recipe.NeptuneRecipe;
import com.neptunedevelopmentteam.neptunelib.core.datagen.recipe.RecipeInput;
import com.neptunedevelopmentteam.neptunelib.core.datagen.recipe.RecipeOutput;
import com.neptunedevelopmentteam.neptunelib.core.datagen.recipe.RecipeRow;
import com.neptunedevelopmentteam.neptunelib.core.datagen.translation.NeptuneLanguages;
import com.neptunedevelopmentteam.neptunelib.core.datagen.translation.NeptuneTranslation;
import com.neptunedevelopmentteam.neptunetest.registration.TestBlocks;
import com.neptunedevelopmentteam.neptunetest.registration.TestItems;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryBuilder;

public class NeptuneTestDataGenerator extends NeptuneDataGenerator {
    @Override
    public String getModId() {
        return "neptunetest";
    }

    @Override
    public void onRegistryBuild(RegistryBuilder registryBuilder) {
        // Custom registry build code
    }

    @Override
    public void onInit() {
        handleTranslations();
        handleRecipes();
    }

    private void handleTranslations() {
        NeptuneTranslation english_translation = new NeptuneTranslation(NeptuneLanguages.English, "Test Item");
        NeptuneTranslation spanish_translation = new NeptuneTranslation(NeptuneLanguages.Spanish, "Elemento de prueba");
        NeptuneTranslation french_translation = new NeptuneTranslation(NeptuneLanguages.French, "élément de test");
        NeptuneTranslation other_translation = new NeptuneTranslation("en_us", "Test Item"); // You can put a custom identifier, if one of the preset languages doesn't work for you
        this.addTranslation(TestItems.TEST_ITEM_WITH_GROUP, english_translation, spanish_translation, french_translation, other_translation);
    }

    private void handleRecipes() {
        NeptuneRecipe shaped_recipe = new NeptuneRecipe(
                RecipeCategory.MISC,
                RecipeInput.createShapedRecipe(
                        RecipeRow.create(null, Items.DIAMOND, null),
                        RecipeRow.create(Items.DIAMOND, TestItems.OTHER_ITEM, Items.DIAMOND),
                        RecipeRow.create(null, Items.NETHER_STAR, null)),
                new RecipeOutput(TestItems.TEST_ITEM, 1)
        );
        this.addRecipe(shaped_recipe);
//        NeptuneRecipe shapeless_recipe = new NeptuneRecipe(
//                RecipeCategory.MISC,
//                RecipeInput.createShapelessRecipe(TestItems.TEST_ITEM, TestItems.OTHER_ITEM),
//                new RecipeOutput(TestBlocks.TEST_BLOCK_WITH_ITEM.asItem(), 1)
//        );
//        this.addRecipe(shapeless_recipe);

    }
}
