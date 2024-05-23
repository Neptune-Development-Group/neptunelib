package com.neptunedevelopmentteam.neptunelib.core.datagen.worldgen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.ArrayList;
import java.util.List;

public class NeptuneOre {
    private BlockState blockState;
    private Identifier id;
    private List<OreFeatureConfig.Target> targets = new ArrayList<>();
    private int vein_size;
    private float discard_chance_on_air_exposure;
    public NeptuneOre(Identifier id, Block block, int vein_size, float discard_chance_on_air_exposure, boolean overworld_ore, boolean nether_ore, boolean end_ore, boolean replaces_stone, boolean replaces_deepslate, boolean replaces_endstone, boolean replaces_netherrack) {
        this.blockState = block.getDefaultState();
        this.id = id;
        this.vein_size = vein_size;
        this.discard_chance_on_air_exposure = discard_chance_on_air_exposure;
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
    }

    public void addBlockReplacement(Block block) {
        targets.add(OreFeatureConfig.createTarget(new NeptuneBlockMatchRuleTest(block), blockState));
    }

    public static NeptuneOre createStoneOverworldOre(Identifier name, Block block, int vein_size, float discard_chance_on_air_exposure) {
        return new NeptuneOre(name, block, vein_size, discard_chance_on_air_exposure, true, false, false, true, false, false, false);
    }

    public static NeptuneOre createDeepslateOverworldOre(Identifier name, Block block, int vein_size, float discard_chance_on_air_exposure) {
        return new NeptuneOre(name, block, vein_size, discard_chance_on_air_exposure, true, false, false, false, true, false, false);
    }

    public static NeptuneOre createNetherrackNetherOre(Identifier name, Block block, int vein_size, float discard_chance_on_air_exposure) {
        return new NeptuneOre(name, block, vein_size, discard_chance_on_air_exposure, false, true, false, false, false, false, true);
    }

    public static NeptuneOre createEndstoneEndOre(Identifier identifier, Block block, int vein_size, float discard_chance_on_air_exposure) {
        return new NeptuneOre(identifier, block, vein_size, discard_chance_on_air_exposure, false, false, true, false, false, true, false);
    }

    public void register(Registerable<ConfiguredFeature<?, ?>> configuredFeatureContext) {
        RegistryKey<ConfiguredFeature<? ,?>> key = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, id);
        configuredFeatureContext.register(key, new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(targets, vein_size, discard_chance_on_air_exposure)));
        
    }
}
