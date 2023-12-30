package com.neptunedevelopmentteam.neptunelib;

import com.neptunedevelopmentteam.neptunelib.core.init_handlers.InitHandler;
import com.neptunedevelopmentteam.neptunelib.core.itemgroup.NeptuneItemGroup;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class Neptunelib implements ModInitializer {
    @Override
    public void onInitialize() {
        System.out.println("Hello World from NeptuneLib!");
    }
}
