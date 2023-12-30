package com.neptunedevelopmentteam.neptunelib.core.init_handlers;

import com.neptunedevelopmentteam.neptunelib.core.blocksettings.NeptuneBlockSettings;
import com.neptunedevelopmentteam.neptunelib.core.itemsettings.NeptuneItemSettings;
import com.neptunedevelopmentteam.neptunelib.interfaces.ForcedBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class InitHandler {

    /**
     * Registers all fields of the given class that are of type Item or Block into the specified namespace.
     *
     * @param  clazz       the class containing the fields to be registered
     * @param  namespace   the namespace to register the fields into
     */
    public static <T> void register(Class<? extends ObjectInit<T>> clazz, String namespace) {
        Field[] allFields = clazz.getDeclaredFields();
        for (Field field : allFields) {
            // If the field's type is Item, then print it
            if (field.getType() == Item.class) {
                try {
                    Item item = (Item) field.get(null);
                    String field_name_fixed = field.getName().toLowerCase(Locale.ROOT).replace("_", "");
                    Registry.register(Registries.ITEM, new Identifier(namespace, field_name_fixed), item);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            else if (field.getType() == Block.class) {
                try {
                    Block block = (Block) field.get(null);
                    NeptuneBlockSettings block_settings = ((ForcedBlockSettings) block).neptunelib$getSettings();
                    String field_name_fixed = field.getName().toLowerCase(Locale.ROOT).replace("_", "");
                    Registry.register(Registries.BLOCK, new Identifier(namespace, field_name_fixed), block);
                    if (block_settings.__has_a_block_item) {
                        Item block_item = new BlockItem(block, block_settings.item_settings);
                        Registry.register(Registries.ITEM, new Identifier(namespace, field_name_fixed), block_item);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
