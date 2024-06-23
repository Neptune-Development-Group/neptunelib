package com.neptunedevelopmentteam.neptunelib.core.datagen.sound;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class NeptuneSound {
    private final SoundEvent sound_event;
    private String subtitle_translation_key;
    private Identifier sound_identifier;
    private Identifier[] sound_paths;
    private float pitch = 1.0f;
    private float volume = 1.0f;
    private boolean stream = false; // Set to true if it's for music
    private int attenuation_distance = 16;
    public NeptuneSound(Identifier sound_group_identifier, @NotNull Identifier... sound_paths) {
        this.sound_event = SoundEvent.of(sound_group_identifier);
        this.subtitle_translation_key = "sound." + sound_group_identifier.getNamespace() + "." + sound_group_identifier.getPath();
        this.sound_identifier = sound_group_identifier;
        this.sound_paths = sound_paths;
    }

    public NeptuneSound(Identifier sound_group_identifier, float pitch, float volume, boolean stream, int attenuation_distance, Identifier... sound_paths) {
        this.sound_event = SoundEvent.of(sound_group_identifier);
        this.subtitle_translation_key =  "sound." + sound_group_identifier.getNamespace() + "." + sound_group_identifier.getPath();
        this.sound_identifier = sound_group_identifier;
        this.pitch = pitch;
        this.volume = volume;
        this.stream = stream;
        this.attenuation_distance = attenuation_distance;
        this.sound_paths = sound_paths;
    }

    public SoundEvent getSoundEvent() {
        return sound_event;
    }

    public String getSubtitleTranslationKey() {
        return subtitle_translation_key;
    }

    public Identifier getSoundIdentifier() {
        return sound_identifier;
    }

    public Identifier[] getSoundPaths() {
        return sound_paths;
    }

    public float getPitch() {
        return pitch;
    }

    public float getVolume() {
        return volume;
    }

    public boolean isStream() {
        return stream;
    }

    public int getAttenuationDistance() {
        return attenuation_distance;
    }

    public JsonObject getJsonObject() {
        JsonObject soundJsonObject = new JsonObject();
        JsonArray soundsJsonObject = new JsonArray();
        // https://mcasset.cloud/1.20.6/assets/minecraft
        // https://minecraft.fandom.com/wiki/Sounds.json

        for (Identifier sound_path : sound_paths) {
            soundsJsonObject.add(sound_path.toString());
        }
        soundJsonObject.addProperty("subtitle", subtitle_translation_key);
        soundJsonObject.add("sounds", soundsJsonObject);
        soundJsonObject.addProperty("name", sound_identifier.getPath());
        soundJsonObject.addProperty("pitch", pitch);
        soundJsonObject.addProperty("volume", volume);
        soundJsonObject.addProperty("stream", stream);
        soundJsonObject.addProperty("attenuation_distance", attenuation_distance);
        return soundJsonObject;
    }

    public String getTranslationKey() {
        return subtitle_translation_key;
    }
}
