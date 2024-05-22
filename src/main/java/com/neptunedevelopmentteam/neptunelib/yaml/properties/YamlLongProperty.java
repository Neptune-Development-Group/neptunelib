package com.neptunedevelopmentteam.neptunelib.yaml.properties;

import com.neptunedevelopmentteam.neptunelib.yaml.YamlCategory;

public class YamlLongProperty extends AbstractYamlProperty<Long> {
    public YamlLongProperty(YamlCategory category, String name, Long value) {
        super(category, name, value);
    }

    @Override
    public String asString() {
        return Long.toString(getValue());
    }

    @Override
    public void parseStringAndSetValue(String string) {
        setValue(Long.parseLong(string));
    }

    @Override
    public String getYamlReadyString() {
        return name + ": " + asString();
    }
}
