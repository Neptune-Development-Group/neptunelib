package com.neptunedevelopmentteam.neptunelib.yaml.properties;

import com.neptunedevelopmentteam.neptunelib.yaml.YamlCategory;

public class YamlBooleanProperty extends AbstractYamlProperty<Boolean> {
    public YamlBooleanProperty(YamlCategory category, String name, Boolean value) {
        super(category, name, value);
    }

    @Override
    public String asString() {
        return Boolean.toString(getValue());
    }

    @Override
    public void parseStringAndSetValue(String string) {
        setValue(Boolean.parseBoolean(string));
    }

    @Override
    public String getYamlReadyString() {
        return name + ": " + asString();
    }
}
