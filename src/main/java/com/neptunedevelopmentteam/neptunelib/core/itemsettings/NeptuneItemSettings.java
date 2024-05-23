package com.neptunedevelopmentteam.neptunelib.core.itemsettings;

import com.neptunedevelopmentteam.neptunelib.core.itemgroup.NeptuneItemGroup;
import net.fabricmc.fabric.api.item.v1.CustomDamageHandler;
import net.fabricmc.fabric.api.item.v1.EquipmentSlotProvider;
import net.minecraft.component.DataComponentType;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class NeptuneItemSettings extends Item.Settings {
    private List<Supplier<NeptuneItemGroup>> groups = new ArrayList<>();

    public List<Supplier<NeptuneItemGroup>> getGroups() {
        return groups;
    }

    public NeptuneItemSettings group(Supplier<NeptuneItemGroup> group) {
        groups.add(group);
        return this;
    }

    @Override
    public NeptuneItemSettings equipmentSlot(EquipmentSlotProvider equipmentSlotProvider) {
        return (NeptuneItemSettings) super.equipmentSlot(equipmentSlotProvider);
    }

    @Override
    public NeptuneItemSettings customDamage(CustomDamageHandler handler) {
        return (NeptuneItemSettings) super.customDamage(handler);
    }

    @Override
    public NeptuneItemSettings maxCount(int maxCount) {
        return (NeptuneItemSettings) super.maxCount(maxCount);
    }


    @Override
    public NeptuneItemSettings maxDamage(int maxDamage) {
        return (NeptuneItemSettings) super.maxDamage(maxDamage);
    }

    @Override
    public NeptuneItemSettings recipeRemainder(Item recipeRemainder) {
        return (NeptuneItemSettings) super.recipeRemainder(recipeRemainder);
    }

    @Override
    public NeptuneItemSettings rarity(Rarity rarity) {
        return (NeptuneItemSettings) super.rarity(rarity);
    }

    @Override
    public NeptuneItemSettings fireproof() {
        return (NeptuneItemSettings) super.fireproof();
    }

    @Override
    public NeptuneItemSettings requires(FeatureFlag... features) {
        return (NeptuneItemSettings) super.requires(features);
    }

    @Override
    public NeptuneItemSettings food(FoodComponent foodComponent) {
        return (NeptuneItemSettings) super.food(foodComponent);
    }

    @Override
    public <T> NeptuneItemSettings component(DataComponentType<T> type, T value) {
        return (NeptuneItemSettings) super.component(type, value);
    }

    @Override
    public NeptuneItemSettings attributeModifiers(AttributeModifiersComponent attributeModifiersComponent) {
        return (NeptuneItemSettings) super.attributeModifiers(attributeModifiersComponent);
    }
}
