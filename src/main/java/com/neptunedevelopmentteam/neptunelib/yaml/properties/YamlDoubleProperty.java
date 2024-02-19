package com.neptunedevelopmentteam.neptunelib.yaml.properties;

import com.neptunedevelopmentteam.neptunelib.yaml.YamlCategory;

public class YamlDoubleProperty extends AbstractYamlProperty<Double> {
    public YamlDoubleProperty(YamlCategory category, String name, Double value) {
        super(category, name, value);
    }

    @Override
    public String asString() {
        return Double.toString(getValue());
    }

    @Override
    public void parseStringAndSetValue(String string) {
        setValue(Double.parseDouble(string));
    }
}
