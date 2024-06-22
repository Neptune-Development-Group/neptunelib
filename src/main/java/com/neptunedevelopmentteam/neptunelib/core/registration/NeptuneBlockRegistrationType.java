package com.neptunedevelopmentteam.neptunelib.core.registration;

import com.neptunedevelopmentteam.neptunelib.core.blocksettings.NeptuneBlockSettings;
import com.neptunedevelopmentteam.neptunelib.core.itemgroup.NeptuneItemGroup;
import com.neptunedevelopmentteam.neptunelib.core.itemsettings.NeptuneItemSettings;
import com.neptunedevelopmentteam.neptunelib.interfaces.NeptuneBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;
import java.util.function.Supplier;

public interface NeptuneBlockRegistrationType extends NeptuneEasyRegistrationType<Block> {
    default void register(Block object, Field fieldSource, String namespace) {
        String name = NeptuneEasyRegistrationType.getName(fieldSource);
        if (object instanceof NeptuneBlock neptuneBlock) {
            NeptuneBlockSettings neptuneBlockSettings = neptuneBlock.neptunelib$getSettings();
            Registry.register(Registries.BLOCK, Identifier.of(namespace, name), object);
            if (neptuneBlockSettings != null && neptuneBlockSettings.__has_a_block_item) {
                NeptuneItemSettings neptuneItemSettings = neptuneBlockSettings.item_settings;
                Item block_item = new BlockItem(object, neptuneItemSettings);
                for (Supplier<NeptuneItemGroup> group : neptuneItemSettings.getGroups()) {
                    if (!group.get().items.contains(block_item)) group.get().__addItemToGroup(block_item);
                }
                Registry.register(Registries.ITEM, Identifier.of(namespace, name), block_item);

            }
        }
    }
}
