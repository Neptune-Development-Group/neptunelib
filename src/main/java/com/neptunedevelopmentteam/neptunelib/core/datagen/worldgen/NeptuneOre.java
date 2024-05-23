package com.neptunedevelopmentteam.neptunelib.core.datagen.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.ArrayList;
import java.util.List;

public class NeptuneOre {
    private BlockState blockState;
    private Identifier id;
    private List<OreFeatureConfig.Target> targets = new ArrayList<>();
    private List<PlacementModifier> modifiers = new ArrayList<>();
    private int vein_size;
    private int veins_per_chunk;
    private float discard_chance_on_air_exposure;
    private boolean overworld_ore;
    private boolean nether_ore;
    private boolean end_ore;
    public NeptuneOre(Identifier id, Block block, int max_height, int lowest_height, int rarity, boolean square_shaped, int vein_size, int veins_per_chunk, float discard_chance_on_air_exposure, boolean overworld_ore, boolean nether_ore, boolean end_ore, boolean replaces_stone, boolean replaces_deepslate, boolean replaces_endstone, boolean replaces_netherrack) {
        this.blockState = block.getDefaultState();
        this.id = id;
        this.vein_size = vein_size;
        this.veins_per_chunk = veins_per_chunk;
        this.discard_chance_on_air_exposure = discard_chance_on_air_exposure;
        this.overworld_ore = overworld_ore;
        this.nether_ore = nether_ore;
        this.end_ore = end_ore;
        if (replaces_stone) {
            targets.add(OreFeatureConfig.createTarget(new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES), blockState));
        }
        if (replaces_deepslate) {
            targets.add(OreFeatureConfig.createTarget(new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), blockState));
        }
        if (replaces_netherrack) {
            targets.add(OreFeatureConfig.createTarget(new NeptuneBlockMatchRuleTest(Blocks.NETHERRACK), blockState));
        }
        if (replaces_endstone) {
            targets.add(OreFeatureConfig.createTarget(new NeptuneBlockMatchRuleTest(Blocks.END_STONE), blockState));
        }
        if (square_shaped) {
            modifiers.add(SquarePlacementModifier.of());
        }
        if (rarity > 0) {
            modifiers.add(RarityFilterPlacementModifier.of(rarity));
        }
        modifiers.add(BiomePlacementModifier.of());
        modifiers.add(HeightRangePlacementModifier.uniform(YOffset.fixed(lowest_height), YOffset.fixed(max_height)));
    }

    public NeptuneOre addBlockReplacement(Block block) {
        targets.add(OreFeatureConfig.createTarget(new NeptuneBlockMatchRuleTest(block), blockState));
        return this;
    }

    public NeptuneOre addModifier(PlacementModifier modifier) {
        modifiers.add(modifier);
        return this;
    }

    public static NeptuneOre createStoneOverworldOre(Identifier name, Block block, int max_height, int lowest_height, int vein_size, int veins_per_chunk, float discard_chance_on_air_exposure) {
        return new NeptuneOre(name, block, max_height, lowest_height, 0, true, vein_size, veins_per_chunk, discard_chance_on_air_exposure, true, false, false, true, false, false, false);
    }

    public static NeptuneOre createDeepslateOverworldOre(Identifier name, Block block, int max_height, int lowest_height, int vein_size, int veins_per_chunk, float discard_chance_on_air_exposure) {
        return new NeptuneOre(name, block, max_height, lowest_height, 0, true, vein_size, veins_per_chunk, discard_chance_on_air_exposure, true, false, false, false, true, false, false);
    }

    public static NeptuneOre createNetherrackNetherOre(Identifier name, Block block, int max_height, int lowest_height, int vein_size, int veins_per_chunk, float discard_chance_on_air_exposure) {
        return new NeptuneOre(name, block, max_height, lowest_height, 0, true, vein_size, veins_per_chunk, discard_chance_on_air_exposure, false, true, false, false, false, false, true);
    }

    public static NeptuneOre createEndstoneEndOre(Identifier identifier, Block block, int max_height, int lowest_height, int vein_size, int veins_per_chunk, float discard_chance_on_air_exposure) {
        return new NeptuneOre(identifier, block, max_height, lowest_height, 0, true, vein_size, veins_per_chunk, discard_chance_on_air_exposure, false, false, true, false, false, true, false);
    }

    public void registerPlacedFeatures(Registerable<PlacedFeature> placedFeatureContext) {
        RegistryKey<ConfiguredFeature<? ,?>> configuredFeatureKey = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, id);
        RegistryKey<PlacedFeature> placedFeatureRegistryKey = RegistryKey.of(RegistryKeys.PLACED_FEATURE, id);
        placedFeatureContext.register(placedFeatureRegistryKey, new PlacedFeature(placedFeatureContext.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(configuredFeatureKey), modifiers));
    }

    public void registerConfiguredFeatures(Registerable<ConfiguredFeature<?, ?>> configuredFeatureContext) {
        RegistryKey<ConfiguredFeature<? ,?>> configuredFeatureKey = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, id);
        configuredFeatureContext.register(configuredFeatureKey, new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(targets, vein_size, discard_chance_on_air_exposure)));
    }

    public void generate() {
        if (overworld_ore) {
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(RegistryKeys.PLACED_FEATURE, id));
        }
        else if (nether_ore) {
            BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(RegistryKeys.PLACED_FEATURE, id));
        }
        else if (end_ore) {
            BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(RegistryKeys.PLACED_FEATURE, id));
        }
        else {
            BiomeModifications.addFeature(BiomeSelectors.all(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(RegistryKeys.PLACED_FEATURE, id));
        }
    }
}
