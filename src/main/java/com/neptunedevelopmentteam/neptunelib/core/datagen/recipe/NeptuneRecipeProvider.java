package com.neptunedevelopmentteam.neptunelib.core.datagen.recipe;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
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
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                neptuneRecipes.forEach(neptuneRecipe -> {
                    neptuneRecipe.build(exporter);
                });
            }
        };
    }


    public void addRecipe(NeptuneRecipe recipe) {
        neptuneRecipes.add(recipe);
    }

    @Override
    public String getName() {
        return "Neptune Recipe Provider";
    }

    public static String getRecipeName(ItemConvertible item) {
        return RecipeGenerator.getRecipeName(item);
    }

    public static String hasItem(ItemConvertible item) {
        return RecipeGenerator.hasItem(item);
    }

    public static AdvancementCriterion<?> conditionsFromItem(ItemConvertible item) {
        return RecipeGenerator.conditionsFromPredicates(ItemPredicate.Builder.create().items(Registries.ITEM, new ItemConvertible[]{item}));
    }
}
