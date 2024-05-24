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

    public NeptuneRecipe(RecipeCategory category, RecipeInput recipeInput, RecipeOutput recipeOutput) {
        this.category = category;
        this.recipeInput = recipeInput;
        this.recipeOutput = recipeOutput;
        String temp_identifier = NeptuneRecipeProvider.getRecipeName(recipeOutput.getItem());
        if (recipeInput.getType() == RecipeType.SHAPED) {
            temp_identifier += "_shaped";
        } else if (recipeInput.getType() == RecipeType.SHAPELESS) {
            temp_identifier += "_shapeless";
        }
        this.identifier = new Identifier(temp_identifier);
    }

    public void build(RecipeExporter exporter) {
        if (recipeInput.getType() == RecipeType.SHAPED) {
            ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(category, recipeOutput.getItem(), recipeOutput.getAmount());
            HashMap<ItemConvertible, Character> characterHashMap = new HashMap<>();
            for (int i = 0; i < recipeInput.getInputs().toArray().length; i++) {
                if (characterHashMap.containsKey(recipeInput.getInputs().get(i))) { continue; }
                characterHashMap.put(recipeInput.getInputs().get(i), Character.forDigit(i, 10));
                builder.input(Character.forDigit(i, 10), recipeInput.getInputs().get(i));
            }
            String pattern1 = "";
            String pattern2 = "";
            String pattern3 = "";

            pattern1 += characterHashMap.getOrDefault(recipeInput.getRow_1().getItem_1(), ' ');
            pattern1 += characterHashMap.getOrDefault(recipeInput.getRow_1().getItem_2(), ' ');
            pattern1 += characterHashMap.getOrDefault(recipeInput.getRow_1().getItem_3(), ' ');
            pattern2 += characterHashMap.getOrDefault(recipeInput.getRow_2().getItem_1(), ' ');
            pattern2 += characterHashMap.getOrDefault(recipeInput.getRow_2().getItem_2(), ' ');
            pattern2 += characterHashMap.getOrDefault(recipeInput.getRow_2().getItem_3(), ' ');
            pattern3 += characterHashMap.getOrDefault(recipeInput.getRow_3().getItem_1(), ' ');
            pattern3 += characterHashMap.getOrDefault(recipeInput.getRow_3().getItem_2(), ' ');
            pattern3 += characterHashMap.getOrDefault(recipeInput.getRow_3().getItem_3(), ' ');
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
