package com.neptunedevelopmentteam.neptunelib.core.itemgroup;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class NeptuneItemGroup {
    boolean initialized = false;
    public List<Item> items = new ArrayList<>();
    private Identifier id;
    private Text display_name;

    private ItemStack icon;
    private ItemGroup group = null;


    /**
     * Initializes the item group.
     */
    public void initialize() {
        if (this.initialized) return;
        onInitialize();
        this.initialized = true;
    }

    public NeptuneItemGroup(Identifier id, ItemStack icon) {
        this.id = id;
        this.icon = icon;
        this.display_name = Text.translatable(getTranslationKey());
    }

    public void onInitialize() {
        group = FabricItemGroup.builder()
                .icon(() -> icon)
                .displayName(display_name)
                .entries(((displayContext, entries) -> {
                    for (Item item : items) {
                        entries.add(item);
                    }
                })).build();
        Registry.register(Registries.ITEM_GROUP, id, group); // Registering item group
    }

    /**
     * Adds an item to the group if it is not already present.
     *
     * @param  item  the item to be added to the group
     */
    public void __addItemToGroup(Item item) {
        System.out.println("Added item to group");
        items.add(item);

    }

    /**
     * Retrieves the group associated with this object.
     *
     * @return the group associated with this object
     */
    public ItemGroup getGroup() {
        return group;
    }

    /**
     * Returns the translation key for the item group.
     *
     * @return the translation key for the item group
     */
    public String getTranslationKey() {
        return "itemGroup.%s.%s".formatted(id.getNamespace(), id.getPath());
    }
}
