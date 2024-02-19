package com.neptunedevelopmentteam.neptunelib.yaml.properties;

import com.neptunedevelopmentteam.neptunelib.yaml.YamlCategory;

public class YamlIntegerProperty extends AbstractYamlProperty<Integer> {
    public YamlIntegerProperty(YamlCategory category, String name, Integer value) {
        super(category, name, value);
    }

    @Override
    public String asString() {
        return Integer.toString(getValue());
    }

    @Override
    public void parseStringAndSetValue(String string) {
        setValue(Integer.parseInt(string));
    }
}
