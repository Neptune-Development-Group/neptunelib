package com.neptunedevelopmentteam.neptunelib.core.datagen.recipe;

import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeInput {
    private final RecipeType type;
    private final RecipeRow row_1;
    private final RecipeRow row_2;
    private final RecipeRow row_3;
    private final List<ItemConvertible> shapelessItems;

    private RecipeInput(RecipeType type, RecipeRow row_1, RecipeRow row_2, RecipeRow row_3, List<ItemConvertible> shapelessItems) {
        this.type = type;
        this.row_1 = row_1;
        this.row_2 = row_2;
        this.row_3 = row_3;
        this.shapelessItems = shapelessItems;
    }

    public RecipeType getType() {
        return type;
    }

    public RecipeRow getRow_1() {
        return row_1;
    }

    public RecipeRow getRow_2() {
        return row_2;
    }

    public RecipeRow getRow_3() {
        return row_3;
    }

    public int getUniqueItemCount() {
        int unique_count = 0;
        if (row_1.getItem_1() != null) unique_count++;
        if (row_1.getItem_2() != null) unique_count++;
        if (row_1.getItem_3() != null) unique_count++;
        if (row_2.getItem_1() != null) unique_count++;
        if (row_2.getItem_2() != null) unique_count++;
        if (row_2.getItem_3() != null) unique_count++;
        if (row_3.getItem_1() != null) unique_count++;
        if (row_3.getItem_2() != null) unique_count++;
        if (row_3.getItem_3() != null) unique_count++;
        return unique_count;
    }

    public List<ItemConvertible> getInputs() {
        List<ItemConvertible> list = new ArrayList<>();
        if (row_1.getItem_1() != null) list.add(row_1.getItem_1());
        if (row_1.getItem_2() != null) list.add(row_1.getItem_2());
        if (row_1.getItem_3() != null) list.add(row_1.getItem_3());
        if (row_2.getItem_1() != null) list.add(row_2.getItem_1());
        if (row_2.getItem_2() != null) list.add(row_2.getItem_2());
        if (row_2.getItem_3() != null) list.add(row_2.getItem_3());
        if (row_3.getItem_1() != null) list.add(row_3.getItem_1());
        if (row_3.getItem_2() != null) list.add(row_3.getItem_2());
        if (row_3.getItem_3() != null) list.add(row_3.getItem_3());
        return list;
    }

    public static RecipeInput createShapedRecipe(RecipeRow row_1, RecipeRow row_2, RecipeRow row_3) {
        return new RecipeInput(RecipeType.SHAPED, row_1, row_2, row_3, null);
    }

    public static RecipeInput createShapelessRecipe(ItemConvertible... items) {
        List<ItemConvertible> list = new ArrayList<>(Arrays.asList(items));
        return new RecipeInput(RecipeType.SHAPELESS, null, null, null, list);
    }

    public List<ItemConvertible> getShapelessItems() {
        return shapelessItems;
    }
}
