package com.neptunedevelopmentteam.neptunelib.core.datagen.recipe;

import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class NeptuneRecipe {
    private final RecipeCategory category;
    private final RecipeInput recipeInput;
    private final RecipeOutput recipeOutput;
    private final Identifier identifier;

    public NeptuneRecipe(Identifier id, RecipeCategory category, RecipeInput recipeInput, RecipeOutput recipeOutput) {
        this.category = category;
        this.recipeInput = recipeInput;
        this.recipeOutput = recipeOutput;
        this.identifier = id;
    }

    public void build(RecipeExporter exporter) {
        if (recipeInput.getType() == RecipeType.SHAPED) {
            ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(category, recipeOutput.getItem(), recipeOutput.getAmount());
            HashMap<ItemConvertible, Character> characterHashMap = new HashMap<>();
            for (int i = 0; i < recipeInput.getInputs().toArray().length; i++) {
                if (characterHashMap.containsKey(recipeInput.getInputs().get(i))) { continue; }
                characterHashMap.put(recipeInput.getInputs().get(i), Character.forDigit(i, 10));
            }
            String pattern1 = "";
            String pattern2 = "";
            String pattern3 = "";

            if (recipeInput.getRow_1().getItem_1() != null) pattern1 += characterHashMap.getOrDefault(recipeInput.getRow_1().getItem_1(), ' ');
            if (recipeInput.getRow_1().getItem_2() != null) pattern1 += characterHashMap.getOrDefault(recipeInput.getRow_1().getItem_2(), ' ');
            if (recipeInput.getRow_1().getItem_3() != null) pattern1 += characterHashMap.getOrDefault(recipeInput.getRow_1().getItem_3(), ' ');

            if (recipeInput.getRow_2().getItem_1() != null) pattern2 += characterHashMap.getOrDefault(recipeInput.getRow_2().getItem_1(), ' ');
            if (recipeInput.getRow_2().getItem_2() != null) pattern2 += characterHashMap.getOrDefault(recipeInput.getRow_2().getItem_2(), ' ');
            if (recipeInput.getRow_2().getItem_3() != null) pattern2 += characterHashMap.getOrDefault(recipeInput.getRow_2().getItem_3(), ' ');

            if (recipeInput.getRow_3().getItem_1() != null) pattern3 += characterHashMap.getOrDefault(recipeInput.getRow_3().getItem_1(), ' ');
            if (recipeInput.getRow_3().getItem_2() != null) pattern3 += characterHashMap.getOrDefault(recipeInput.getRow_3().getItem_2(), ' ');
            if (recipeInput.getRow_3().getItem_3() != null) pattern3 += characterHashMap.getOrDefault(recipeInput.getRow_3().getItem_3(), ' ');
            builder.pattern(pattern1).pattern(pattern2).pattern(pattern3);
            characterHashMap.forEach((ingredient, character) -> builder.criterion(NeptuneRecipeProvider.hasItem(ingredient), NeptuneRecipeProvider.conditionsFromItem(ingredient)));
            builder.offerTo(exporter, identifier);
        }
        else if (recipeInput.getType() == RecipeType.SHAPELESS) {
            ShapelessRecipeJsonBuilder builder = ShapelessRecipeJsonBuilder.create(category, recipeOutput.getItem(), recipeOutput.getAmount());
            recipeInput.getShapelessItems().forEach(item -> builder.criterion(NeptuneRecipeProvider.hasItem(item), NeptuneRecipeProvider.conditionsFromItem(item)));
            recipeInput.getShapelessItems().forEach(builder::input);
            builder.offerTo(exporter, identifier);
        }
    }
}
