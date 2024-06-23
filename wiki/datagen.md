# Data Generation
Some information is taken from the [fabric wiki](https://fabricmc.net/wiki/tutorial:datagen_setup)

First open up your `build.gradle` file in the root folder of your project, and add the following anywhere inside that file:
```groovy
    //
    // ... (The rest of the file)
    //
     
    fabricApi {
        configureDataGeneration()
    }
```
Next we'll define a new class in our project `NeptuneTestDataGenerator`, name that whatever you like, then have it extend the `NeptuneDataGenerator` class
```java
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

public class NeptuneTestDataGenerator extends NeptuneDataGenerator {
    @Override
    public String getModId() {
        return "neptunetest";
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
        NeptuneRecipe shapeless_recipe = new NeptuneRecipe(
                RecipeCategory.MISC,
                RecipeInput.createShapelessRecipe(TestItems.TEST_ITEM, TestItems.OTHER_ITEM),
                new RecipeOutput(TestBlocks.TEST_BLOCK_WITH_ITEM.asItem(), 1)
        );
        this.addRecipe(shapeless_recipe);
        
    }
}
```

Currently, this datagen system only supports the built-in part of the registration system or translations and recipes

The translation part only supports English, Spanish, and French, but more will be added later. You can use a specific language code if you want to as shown above.


Since `NeptuneDataGenerator` implements `DataGeneratorEntrypoint` you can continue the rest of any fabric tutorial to create datagen for other things, to get the `FabricDataGenerator` object just use `this.fabricDataGenerator` as we pass this along to you for any custom use