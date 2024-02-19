package com.neptunedevelopmentteam.neptunelib.yaml.properties;

import com.neptunedevelopmentteam.neptunelib.yaml.YamlCategory;

public class YamlStringProperty extends AbstractYamlProperty<String> {

    public YamlStringProperty(YamlCategory category, String name, String value) {
        super(category, name, value);
    }

    @Override
    public String asString() {
        return getValue();
    }

    @Override
    public void parseStringAndSetValue(String string) {
        setValue(string);
    }
}
