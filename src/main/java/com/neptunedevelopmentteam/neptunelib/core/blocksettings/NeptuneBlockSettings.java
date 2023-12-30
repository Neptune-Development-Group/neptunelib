package com.neptunedevelopmentteam.neptunelib.core.blocksettings;

import com.neptunedevelopmentteam.neptunelib.core.itemsettings.NeptuneItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.function.Function;
import java.util.function.ToIntFunction;

public class NeptuneBlockSettings extends FabricBlockSettings {
    
    public NeptuneItemSettings item_settings = new NeptuneItemSettings();
    public FabricBlockEntityTypeBuilder.Factory<? extends BlockEntity> block_entity_factory = null;
    public Boolean __has_a_block_item = false;
    public Boolean __has_a_block_entity = false;
    public Identifier optional_block_entity_id = null;
    
    public static NeptuneBlockSettings create() {
        return new NeptuneBlockSettings();
    }

    public NeptuneBlockSettings addItemSettings(NeptuneItemSettings item_settings) {
        this.item_settings = item_settings;
        __has_a_block_item = true;
        return this;
    }

    public NeptuneBlockSettings addBlockEntity(FabricBlockEntityTypeBuilder.Factory<? extends BlockEntity> factory) {
        __has_a_block_entity = true;
        block_entity_factory = factory;
        return this;
    }
    public NeptuneBlockSettings addBlockEntity(FabricBlockEntityTypeBuilder.Factory<? extends BlockEntity> factory, Identifier id) {
        __has_a_block_entity = true;
        block_entity_factory = factory;
        optional_block_entity_id = id;
        return this;
    }

    @Override
    public NeptuneBlockSettings noCollision() {
        return (NeptuneBlockSettings) super.noCollision();
    }

    @Override
    public NeptuneBlockSettings nonOpaque() {
        return (NeptuneBlockSettings) super.nonOpaque();
    }

    @Override
    public NeptuneBlockSettings slipperiness(float value) {
        return (NeptuneBlockSettings) super.slipperiness(value);
    }

    @Override
    public NeptuneBlockSettings velocityMultiplier(float velocityMultiplier) {
        return (NeptuneBlockSettings) super.velocityMultiplier(velocityMultiplier);
    }

    @Override
    public NeptuneBlockSettings jumpVelocityMultiplier(float jumpVelocityMultiplier) {
        return (NeptuneBlockSettings) super.jumpVelocityMultiplier(jumpVelocityMultiplier);
    }

    @Override
    public NeptuneBlockSettings sounds(BlockSoundGroup group) {
        return (NeptuneBlockSettings) super.sounds(group);
    }

    @Override
    public NeptuneBlockSettings lightLevel(ToIntFunction<BlockState> levelFunction) {
        return (NeptuneBlockSettings) super.lightLevel(levelFunction);
    }

    @Override
    public NeptuneBlockSettings luminance(ToIntFunction<BlockState> luminanceFunction) {
        return (NeptuneBlockSettings) super.luminance(luminanceFunction);
    }

    @Override
    public NeptuneBlockSettings strength(float hardness, float resistance) {
        return (NeptuneBlockSettings) super.strength(hardness, resistance);
    }

    @Override
    public NeptuneBlockSettings breakInstantly() {
        return (NeptuneBlockSettings) super.breakInstantly();
    }

    @Override
    public NeptuneBlockSettings strength(float strength) {
        return (NeptuneBlockSettings) super.strength(strength);
    }

    @Override
    public NeptuneBlockSettings ticksRandomly() {
        return (NeptuneBlockSettings) super.ticksRandomly();
    }

    @Override
    public NeptuneBlockSettings dynamicBounds() {
        return (NeptuneBlockSettings) super.dynamicBounds();
    }

    @Override
    public NeptuneBlockSettings dropsNothing() {
        return (NeptuneBlockSettings) super.dropsNothing();
    }

    @Override
    public NeptuneBlockSettings dropsLike(Block block) {
        return (NeptuneBlockSettings) super.dropsLike(block);
    }

    @Override
    public NeptuneBlockSettings air() {
        return (NeptuneBlockSettings) super.air();
    }

    @Override
    public NeptuneBlockSettings allowsSpawning(AbstractBlock.TypedContextPredicate<EntityType<?>> predicate) {
        return (NeptuneBlockSettings) super.allowsSpawning(predicate);
    }

    @Override
    public NeptuneBlockSettings solidBlock(AbstractBlock.ContextPredicate predicate) {
        return (NeptuneBlockSettings) super.solidBlock(predicate);
    }

    @Override
    public NeptuneBlockSettings suffocates(AbstractBlock.ContextPredicate predicate) {
        return (NeptuneBlockSettings) super.suffocates(predicate);
    }

    @Override
    public NeptuneBlockSettings blockVision(AbstractBlock.ContextPredicate predicate) {
        return (NeptuneBlockSettings) super.blockVision(predicate);
    }

    @Override
    public NeptuneBlockSettings postProcess(AbstractBlock.ContextPredicate predicate) {
        return (NeptuneBlockSettings) super.postProcess(predicate);
    }

    @Override
    public NeptuneBlockSettings emissiveLighting(AbstractBlock.ContextPredicate predicate) {
        return (NeptuneBlockSettings) super.emissiveLighting(predicate);
    }

    @Override
    public NeptuneBlockSettings requiresTool() {
        return (NeptuneBlockSettings) super.requiresTool();
    }

    @Override
    public NeptuneBlockSettings mapColor(MapColor color) {
        return (NeptuneBlockSettings) super.mapColor(color);
    }

    @Override
    public NeptuneBlockSettings hardness(float hardness) {
        return (NeptuneBlockSettings) super.hardness(hardness);
    }

    @Override
    public NeptuneBlockSettings resistance(float resistance) {
        return (NeptuneBlockSettings) super.resistance(resistance);
    }

    @Override
    public NeptuneBlockSettings offset(AbstractBlock.OffsetType offsetType) {
        return (NeptuneBlockSettings) super.offset(offsetType);
    }

    @Override
    public NeptuneBlockSettings noBlockBreakParticles() {
        return (NeptuneBlockSettings) super.noBlockBreakParticles();
    }

    @Override
    public NeptuneBlockSettings requires(FeatureFlag... features) {
        return (NeptuneBlockSettings) super.requires(features);
    }

    @Override
    public NeptuneBlockSettings mapColor(Function<BlockState, MapColor> mapColorProvider) {
        return (NeptuneBlockSettings) super.mapColor(mapColorProvider);
    }

    @Override
    public NeptuneBlockSettings burnable() {
        return (NeptuneBlockSettings) super.burnable();
    }

    @Override
    public NeptuneBlockSettings liquid() {
        return (NeptuneBlockSettings) super.liquid();
    }

    @Override
    public NeptuneBlockSettings solid() {
        return (NeptuneBlockSettings) super.solid();
    }

    @Override
    public NeptuneBlockSettings notSolid() {
        return (NeptuneBlockSettings) super.notSolid();
    }

    @Override
    public NeptuneBlockSettings pistonBehavior(PistonBehavior pistonBehavior) {
        return (NeptuneBlockSettings) super.pistonBehavior(pistonBehavior);
    }

    @Override
    public NeptuneBlockSettings instrument(Instrument instrument) {
        return (NeptuneBlockSettings) super.instrument(instrument);
    }

    @Override
    public NeptuneBlockSettings replaceable() {
        return (NeptuneBlockSettings) super.replaceable();
    }

    @Override
    public NeptuneBlockSettings lightLevel(int lightLevel) {
        return (NeptuneBlockSettings) super.lightLevel(lightLevel);
    }

    @Override
    public NeptuneBlockSettings luminance(int luminance) {
        return (NeptuneBlockSettings) super.luminance(luminance);
    }

    @Override
    public NeptuneBlockSettings drops(Identifier dropTableId) {
        return (NeptuneBlockSettings) super.drops(dropTableId);
    }

    @Override
    public NeptuneBlockSettings materialColor(MapColor color) {
        return (NeptuneBlockSettings) super.materialColor(color);
    }

    @Override
    public NeptuneBlockSettings materialColor(DyeColor color) {
        return (NeptuneBlockSettings) super.materialColor(color);
    }

    @Override
    public NeptuneBlockSettings mapColor(DyeColor color) {
        return (NeptuneBlockSettings) super.mapColor(color);
    }

    @Override
    public NeptuneBlockSettings collidable(boolean collidable) {
        return (NeptuneBlockSettings) super.collidable(collidable);
    }
}
