package com.neptunedevelopmentteam.neptunelib.core.datagen.recipe;

import net.minecraft.item.ItemConvertible;

public class RecipeOutput {

    private final ItemConvertible itemConvertible;
    private final int amount;
    public RecipeOutput(ItemConvertible itemConvertible, int amount) {
        this.itemConvertible = itemConvertible;
        this.amount = amount;
    }

    public ItemConvertible getItem() {
        return itemConvertible;
    }

    public int getAmount() {
        return amount;
    }
}
