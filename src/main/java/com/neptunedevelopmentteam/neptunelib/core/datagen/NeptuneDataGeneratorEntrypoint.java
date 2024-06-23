package com.neptunedevelopmentteam.neptunelib.core.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;

import java.util.function.Supplier;

public interface NeptuneDataGeneratorEntrypoint extends DataGeneratorEntrypoint {

    String getModId();
    void onInit();
    default void onRegistryBuild(RegistryBuilder registryBuilder) {}
}
