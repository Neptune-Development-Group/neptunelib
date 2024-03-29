package com.neptunedevelopmentteam.neptunelib.core.init_handlers;

import com.neptunedevelopmentteam.neptunelib.core.blocksettings.NeptuneBlockSettings;
import com.neptunedevelopmentteam.neptunelib.core.itemgroup.NeptuneItemGroup;
import com.neptunedevelopmentteam.neptunelib.core.itemsettings.NeptuneItemSettings;
import com.neptunedevelopmentteam.neptunelib.interfaces.NeptuneBlock;
import com.neptunedevelopmentteam.neptunelib.interfaces.NeptuneItem;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.function.Supplier;

public class NeptuneInitHandler {

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
                    String field_name_fixed = field.getName().toLowerCase(Locale.ROOT);
                    if (field.isAnnotationPresent(CustomName.class)) {
                        CustomName customName = field.getAnnotation(CustomName.class);
                        field_name_fixed = customName.value().toLowerCase(Locale.ROOT);
                    }
                    NeptuneItemSettings item_settings = ((NeptuneItem) item).neptunelib$getSettings();
                    if (!item_settings.getGroups().isEmpty()) {
                        for (Supplier<NeptuneItemGroup> group : item_settings.getGroups()) {
                            if (!group.get().items.contains(item)) {
                                group.get().__addItemToGroup(item);
                            }
                        }
                    }
                    Registry.register(Registries.ITEM, new Identifier(namespace, field_name_fixed), item);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            else if (field.getType() == Block.class) {
                try {
                    Block block = (Block) field.get(null);
                    NeptuneBlockSettings block_settings = ((NeptuneBlock) block).neptunelib$getSettings();
                    String field_name_fixed = field.getName().toLowerCase(Locale.ROOT);
                    if (field.isAnnotationPresent(CustomName.class)) {
                        CustomName customName = field.getAnnotation(CustomName.class);
                        field_name_fixed = customName.value().toLowerCase(Locale.ROOT);
                    }
                    Registry.register(Registries.BLOCK, new Identifier(namespace, field_name_fixed), block);
                    if (block_settings.__has_a_block_item) {
                        NeptuneItemSettings item_settings = block_settings.item_settings;

                        Item block_item = new BlockItem(block, item_settings);
                        if (!item_settings.getGroups().isEmpty()) {
                            for (Supplier<NeptuneItemGroup> group : item_settings.getGroups()) {
                                if (!group.get().items.contains(block_item)) {
                                    group.get().__addItemToGroup(block_item);
                                }
                            }
                        }
                        Registry.register(Registries.ITEM, new Identifier(namespace, field_name_fixed), block_item);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            else if (field.getType() == SoundEvent.class) {
                try {
                    SoundEvent soundEvent = (SoundEvent) field.get(null);
                    String field_name_fixed = field.getName().toLowerCase(Locale.ROOT);
                    if (field.isAnnotationPresent(CustomName.class)) {
                        CustomName customName = field.getAnnotation(CustomName.class);
                        field_name_fixed = customName.value().toLowerCase(Locale.ROOT);
                    }
                    Registry.register(Registries.SOUND_EVENT, new Identifier(namespace, field_name_fixed), soundEvent);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            else if (field.getType() == BlockEntityType.class) {
                try {
                    BlockEntityType blockEntityType = (BlockEntityType) field.get(null);
                    String field_name_fixed = field.getName().toLowerCase(Locale.ROOT);
                    if (field.isAnnotationPresent(CustomName.class)) {
                        CustomName customName = field.getAnnotation(CustomName.class);
                        field_name_fixed = customName.value().toLowerCase(Locale.ROOT);
                    }
                    Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(namespace, field_name_fixed), blockEntityType);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            else if (field.getType() == EntityType.class) {
                try {
                    EntityType entityType = (EntityType) field.get(null);
                    String field_name_fixed = field.getName().toLowerCase(Locale.ROOT);
                    if (field.isAnnotationPresent(CustomName.class)) {
                        CustomName customName = field.getAnnotation(CustomName.class);
                        field_name_fixed = customName.value().toLowerCase(Locale.ROOT);
                    }
                    Registry.register(Registries.ENTITY_TYPE, new Identifier(namespace, field_name_fixed), entityType);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
