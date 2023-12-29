package com.neptunedevelopmentteam.neptunelib.core.itemsettings;

import com.neptunedevelopmentteam.neptunelib.core.itemgroup.NeptuneItemGroup;
import net.fabricmc.fabric.api.item.v1.CustomDamageHandler;
import net.fabricmc.fabric.api.item.v1.EquipmentSlotProvider;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;

public class NeptuneItemSettings extends FabricItemSettings {

    @Nullable
    private NeptuneItemGroup group = null;

    public NeptuneItemSettings group(NeptuneItemGroup group) {
        this.group = group;
        return this;
    }
    public NeptuneItemGroup group() {
        return this.group;
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
    public NeptuneItemSettings food(FoodComponent foodComponent) {
        return (NeptuneItemSettings) super.food(foodComponent);
    }

    @Override
    public NeptuneItemSettings maxCount(int maxCount) {
        return (NeptuneItemSettings) super.maxCount(maxCount);
    }

    @Override
    public NeptuneItemSettings maxDamageIfAbsent(int maxDamage) {
        return (NeptuneItemSettings) super.maxDamageIfAbsent(maxDamage);
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
}
