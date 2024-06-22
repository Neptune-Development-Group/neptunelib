package com.neptunedevelopmentteam.neptunelib.core.registration;

import com.neptunedevelopmentteam.neptunelib.core.itemgroup.NeptuneItemGroup;
import com.neptunedevelopmentteam.neptunelib.core.itemsettings.NeptuneItemSettings;
import com.neptunedevelopmentteam.neptunelib.interfaces.NeptuneItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;
import java.util.function.Supplier;

public interface NeptuneItemRegistrationType extends NeptuneEasyRegistrationType<Item> {
    static void register(Item object, Field fieldSource, String namespace) {
        String name = NeptuneEasyRegistrationType.getName(fieldSource);
        if (object instanceof NeptuneItem neptuneItem) {
            NeptuneItemSettings neptuneItemSettings = neptuneItem.neptunelib$getSettings();
            if (neptuneItemSettings != null && !neptuneItemSettings.getGroups().isEmpty()) {
                for (Supplier<NeptuneItemGroup> group : neptuneItemSettings.getGroups()) {
                    if (!group.get().items.contains(object)) group.get().__addItemToGroup(object);
                }
            }
            Registry.register(Registries.ITEM, Identifier.of(namespace, name), object);
        }
    }
}
