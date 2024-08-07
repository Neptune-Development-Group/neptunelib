package com.neptunedevelopmentteam.neptunelib.core.datagen.translation;

import java.util.List;

public class NeptuneTranslation {
    private NeptuneLanguages language;
    private String raw_identifier = "";
    private String translation;

    public NeptuneTranslation(String language_identifier, String translation) {
        this.language = null;
        this.raw_identifier = language_identifier;
        this.translation = translation;
    }

    public NeptuneTranslation(NeptuneLanguages language, String translation) {
        this.language = language;
        this.translation = translation;
    }

    public static NeptuneTranslation createTranslation(NeptuneLanguages language, String translation) {
        return new NeptuneTranslation(language, translation);
    }

    public String getRawIdentifier() {
        return raw_identifier;
    }

    public NeptuneLanguages getLanguage() {
        return language;
    }

    public List<String> getLanguageIdentifiers() {
        if (!raw_identifier.isEmpty()) return List.of(raw_identifier);
        return switch (language) {
            case English -> List.of("en_us", "en_uk", "en_au", "en_ca", "en_gb", "en_nz");
            case Spanish -> List.of("es_ar", "es_cl", "es_ec", "es_es", "es_mx", "es_uy", "es_ve");
            case French -> List.of("fr_fr", "fr_ca");
        };
    }

    public String getTranslation() {
        return translation;
    }
}
