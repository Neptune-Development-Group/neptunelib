package com.neptunedevelopmentteam.neptunelib.core.registration;

import com.neptunedevelopmentteam.neptunelib.core.datagen.sound.NeptuneSound;
import com.neptunedevelopmentteam.neptunelib.core.datagen.worldgen.NeptuneOre;
import net.minecraft.registry.Registerable;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.ArrayList;
import java.util.List;

public class NeptuneRegistrationDatagenHookupManager {
    private static List<NeptuneSound> sounds = new ArrayList<>();
    private static List<NeptuneOre> neptuneOres = new ArrayList<>();

    public static List<NeptuneSound> getSounds() {
        return sounds;
    }

    public static void addSound(NeptuneSound neptuneSound) {
        sounds.add(neptuneSound);
    }

    public static List<NeptuneOre> getNeptuneOres() {
        return neptuneOres;
    }

    public static void addNeptuneOre(NeptuneOre neptuneOre) {
        if (!neptuneOres.contains(neptuneOre)) neptuneOres.add(neptuneOre);
    }

    public static void bootstrap_configured_feature_ores(Registerable<ConfiguredFeature<?, ?>> configuredFeatureRegisterable) {
        neptuneOres.forEach((neptuneOre) -> neptuneOre.registerConfiguredFeatures(configuredFeatureRegisterable));
    }

    public static void bootstrap_placeable_feature_ores(Registerable<PlacedFeature> placedFeatureRegisterable) {
        neptuneOres.forEach((neptuneOre) -> neptuneOre.registerPlacedFeatures(placedFeatureRegisterable));
    }
}
