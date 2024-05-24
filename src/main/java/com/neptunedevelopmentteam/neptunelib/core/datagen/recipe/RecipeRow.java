package com.neptunedevelopmentteam.neptunelib.core.datagen.recipe;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;

public class RecipeRow {
    private final ItemConvertible item_1;
    private final ItemConvertible item_2;
    private final ItemConvertible item_3;

    private RecipeRow(ItemConvertible item_1, ItemConvertible item_2, ItemConvertible item_3) {
        if (item_1 == Items.AIR) this.item_1 = null;
        else this.item_1 = item_1;
        if (item_2 == Items.AIR) this.item_2 = null;
        else this.item_2 = item_2;
        if (item_3 == Items.AIR) this.item_3 = null;
        else this.item_3 = item_3;
    }

    public ItemConvertible getItem_1() {
        return item_1;
    }

    public ItemConvertible getItem_2() {
        return item_2;
    }

    public ItemConvertible getItem_3() {
        return item_3;
    }

    public static RecipeRow create(ItemConvertible item_1, ItemConvertible item_2, ItemConvertible item_3) {
        return new RecipeRow(item_1, item_2, item_3);
    }
}
