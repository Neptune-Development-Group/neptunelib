package com.neptunedevelopmentteam.neptunelib.core.blocksettings;

import com.neptunedevelopmentteam.neptunelib.core.itemsettings.NeptuneItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.function.Function;
import java.util.function.ToIntFunction;

public class NeptuneBlockSettings {

    public NeptuneItemSettings item_settings = new NeptuneItemSettings();
    public Boolean __has_a_block_item = false;
    private AbstractBlock.Settings settings;

    public NeptuneBlockSettings(AbstractBlock.Settings settings) {
        this.settings = settings;
    }

    /**
     * Adds an item setting to the Neptune block settings.
     *
     * @param  item_settings  the item settings to be added
     * @return                the updated Neptune block settings
     */

    public NeptuneBlockSettings addItemSettings(NeptuneItemSettings item_settings) {
        this.item_settings = item_settings;
        __has_a_block_item = true;
        return this;
    }

    public AbstractBlock.Settings getSettings() {
        return settings;
    }

    public Boolean hasABlockItem() {
        return __has_a_block_item;
    }

    public NeptuneItemSettings getItemSettings() {
        return item_settings;
    }

    public static NeptuneBlockSettings create() {
        return new NeptuneBlockSettings(AbstractBlock.Settings.create());
    }

    public static NeptuneBlockSettings create(AbstractBlock.Settings settings) {
        return new NeptuneBlockSettings(settings);
    }

    public static NeptuneBlockSettings createWithItemSettings(NeptuneItemSettings item_settings) {
        return new NeptuneBlockSettings(AbstractBlock.Settings.create()).addItemSettings(item_settings);
    }

    public NeptuneBlockSettings mapColor(DyeColor color) {
        settings.mapColor(color);
        return this;
    }

    public NeptuneBlockSettings mapColor(MapColor color) {
        settings.mapColor(color);
        return this;
    }

    public NeptuneBlockSettings mapColor(Function<BlockState, MapColor> mapColorProvider) {
        settings.mapColor(mapColorProvider);
        return this;
    }

    public NeptuneBlockSettings noCollision() {
        settings.noCollision();
        return this;
    }

    public NeptuneBlockSettings nonOpaque() {
        settings.nonOpaque();
        return this;
    }

    public NeptuneBlockSettings slipperiness(float slipperiness) {
        settings.slipperiness(slipperiness);
        return this;
    }

    public NeptuneBlockSettings velocityMultiplier(float velocityMultiplier) {
        settings.velocityMultiplier(velocityMultiplier);
        return this;
    }

    public NeptuneBlockSettings jumpVelocityMultiplier(float jumpVelocityMultiplier) {
        settings.jumpVelocityMultiplier(jumpVelocityMultiplier);
        return this;
    }

    public NeptuneBlockSettings sounds(BlockSoundGroup soundGroup) {
        settings.sounds(soundGroup);
        return this;
    }

    public NeptuneBlockSettings luminance(ToIntFunction<BlockState> luminance) {
        settings.luminance(luminance);
        return this;
    }

    public NeptuneBlockSettings strength(float hardness, float resistance) {
        settings.strength(hardness, resistance);
        return this;
    }

    public NeptuneBlockSettings breakInstantly() {
        settings.breakInstantly();
        return this;
    }

    public NeptuneBlockSettings strength(float strength) {
        settings.strength(strength);
        return this;
    }

    public NeptuneBlockSettings ticksRandomly() {
        settings.ticksRandomly();
        return this;
    }

    public NeptuneBlockSettings dynamicBounds() {
        settings.dynamicBounds();
        return this;
    }

    public NeptuneBlockSettings dropsNothing() {
        settings.dropsNothing();
        return this;
    }

    public NeptuneBlockSettings dropsLike(Block source) {
        settings.dropsLike(source);
        return this;
    }

    public NeptuneBlockSettings burnable() {
        settings.burnable();
        return this;
    }

    public NeptuneBlockSettings liquid() {
        settings.liquid();
        return this;
    }

    public NeptuneBlockSettings solid() {
        settings.solid();
        return this;
    }

    @Deprecated
    public NeptuneBlockSettings notSolid() {
        settings.notSolid();
        return this;
    }

    public NeptuneBlockSettings pistonBehavior(PistonBehavior pistonBehavior) {
        settings.pistonBehavior(pistonBehavior);
        return this;
    }

    public NeptuneBlockSettings air() {
        settings.air();
        return this;
    }

    public NeptuneBlockSettings allowsSpawning(AbstractBlock.TypedContextPredicate<EntityType<?>> predicate) {
        settings.allowsSpawning(predicate);
        return this;
    }

    public NeptuneBlockSettings solidBlock(AbstractBlock.ContextPredicate predicate) {
        settings.solidBlock(predicate);
        return this;
    }

    public NeptuneBlockSettings suffocates(AbstractBlock.ContextPredicate predicate) {
        settings.suffocates(predicate);
        return this;
    }

    public NeptuneBlockSettings blockVision(AbstractBlock.ContextPredicate predicate) {
        settings.blockVision(predicate);
        return this;
    }

    public NeptuneBlockSettings postProcess(AbstractBlock.ContextPredicate predicate) {
        settings.postProcess(predicate);
        return this;
    }

    public NeptuneBlockSettings emissiveLighting(AbstractBlock.ContextPredicate predicate) {
        settings.emissiveLighting(predicate);
        return this;
    }

    public NeptuneBlockSettings requiresTool() {
        settings.requiresTool();
        return this;
    }

    public NeptuneBlockSettings hardness(float hardness) {
        settings.hardness(hardness);
        return this;
    }

    public NeptuneBlockSettings resistance(float resistance) {
        settings.resistance(resistance);
        return this;
    }

    public NeptuneBlockSettings offset(AbstractBlock.OffsetType offsetType) {
        settings.offset(offsetType);
        return this;
    }

    public NeptuneBlockSettings noBlockBreakParticles() {
        settings.noBlockBreakParticles();
        return this;
    }

    public NeptuneBlockSettings requires(FeatureFlag... features) {
        settings.requires(features);
        return this;
    }

    public NeptuneBlockSettings replaceable() {
        settings.replaceable();
        return this;
    }
}
