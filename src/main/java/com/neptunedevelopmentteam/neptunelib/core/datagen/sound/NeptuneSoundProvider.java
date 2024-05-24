package com.neptunedevelopmentteam.neptunelib.core.datagen.sound;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.registry.RegistryWrapper;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NeptuneSoundProvider implements DataProvider {
    private final FabricDataOutput dataOutput;

    public NeptuneSoundProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        this.dataOutput = dataOutput;
    }
    List<NeptuneSound> neptuneSounds = new ArrayList<>();
    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        JsonObject parentObject = new JsonObject();

        neptuneSounds.forEach(sound -> {
            parentObject.add(sound.getSoundIdentifier().getPath(), sound.getJsonObject());
        });
        return DataProvider.writeToPath(writer, parentObject, getOutputPath());
    }

    @Override
    public String getName() {
        return "Sounds";
    }

    public void addSound(NeptuneSound neptuneSound) {
        neptuneSounds.add(neptuneSound);
    }

    public Path getOutputPath() {
        return dataOutput.resolvePath(DataOutput.OutputType.RESOURCE_PACK).resolve(dataOutput.getModId()).resolve("sounds.json");
    }
}
