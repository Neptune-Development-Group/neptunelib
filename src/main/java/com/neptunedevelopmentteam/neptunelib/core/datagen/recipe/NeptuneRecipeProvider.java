package com.neptunedevelopmentteam.neptunelib.core.datagen.recipe;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NeptuneRecipeProvider extends FabricRecipeProvider {
    private final List<NeptuneRecipe> neptuneRecipes = new ArrayList<>();
    public NeptuneRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        for (NeptuneRecipe recipe : neptuneRecipes) {
            recipe.build(exporter);
        }
    }

    public void addRecipe(NeptuneRecipe recipe) {
        neptuneRecipes.add(recipe);
    }
}
