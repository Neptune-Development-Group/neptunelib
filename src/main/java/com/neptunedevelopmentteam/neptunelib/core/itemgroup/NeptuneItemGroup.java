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

public class NeptuneItemGroup {
    boolean initialized = false;
    public List<Item> items = new ArrayList<>();
    Identifier id;
    Text display_name;

    ItemStack icon;
    ItemGroup group = null;

    public void initialize() {
        if (this.initialized) return;
        onInitialize();
        this.initialized = true;
    }

    public NeptuneItemGroup(Identifier id, ItemStack icon) {
        this.id = id;
        this.icon = icon;
        this.display_name = Text.translatable("itemGroup.%s.%s", id.getNamespace(), id.getPath());
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

    public void addItemToGroup(Item item) {
        if (!items.contains(item)) {
            items.add(item);
        }
    }

    public ItemGroup getGroup() {
        return group;
    }

    public String getTranslationKey() {
        return "itemGroup.%s.%s".formatted(id.getNamespace(), id.getPath());
    }
}
