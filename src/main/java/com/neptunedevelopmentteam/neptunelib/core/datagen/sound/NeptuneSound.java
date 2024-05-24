package com.neptunedevelopmentteam.neptunelib.core.datagen.sound;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class NeptuneSound {
    private final SoundEvent sound_event;
    private String subtitle_translation_key;
    private Identifier sound_identifier;
    private Identifier[] sound_paths;
    private float pitch = 1.0f;
    private float volume = 1.0f;
    private boolean stream = false; // Set to true if it's for music
    private int attenuation_distance = 16;
    public NeptuneSound(Identifier sound_group_identifier, Identifier... sound_paths) {
        this.sound_event = SoundEvent.of(sound_group_identifier);
        this.subtitle_translation_key = sound_group_identifier.getNamespace() + "." + ".sound.subtitle" + "." + sound_group_identifier.getPath();
        this.sound_identifier = sound_group_identifier;
        this.sound_paths = sound_paths;
    }

    public NeptuneSound(Identifier sound_group_identifier, float pitch, float volume, boolean stream, int attenuation_distance, Identifier... sound_paths) {
        this.sound_event = SoundEvent.of(sound_group_identifier);
        this.subtitle_translation_key = sound_group_identifier.getNamespace() + "." + ".sound.subtitle" + "." + sound_group_identifier.getPath();
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
        JsonObject soundsJsonObject = new JsonObject();
        // https://mcasset.cloud/1.20.6/assets/minecraft
        // https://minecraft.fandom.com/wiki/Sounds.json

        for (Identifier sound_path : sound_paths) {
            JsonObject soundFileObject = new JsonObject();
            soundFileObject.addProperty("name", sound_path.getPath());
            soundFileObject.addProperty("pitch", pitch);
            soundFileObject.addProperty("volume", volume);
            soundFileObject.addProperty("stream", stream);
            soundFileObject.addProperty("attenuation_distance", attenuation_distance);
            soundsJsonObject.add(sound_path.toString(), soundFileObject);
        }
        soundJsonObject.addProperty("subtitle", subtitle_translation_key);
        soundJsonObject.add("sounds", soundsJsonObject);
        return soundJsonObject;
    }

    public String getTranslationKey() {
        return subtitle_translation_key;
    }
}
