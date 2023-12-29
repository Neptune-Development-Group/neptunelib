package com.neptunedevelopmentteam.neptunelib;

import com.neptunedevelopmentteam.neptunelib.core.itemgroup.NeptuneItemGroup;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class Neptunelib implements ModInitializer {
//    public static final NeptuneItemGroup myItemGroup = new NeptuneItemGroup(new Identifier("my_mod", "item_group"), new ItemStack(Items.ACACIA_FENCE));
    @Override
    public void onInitialize() {
        System.out.println("Hello World from NeptuneLib!");
    }
}
