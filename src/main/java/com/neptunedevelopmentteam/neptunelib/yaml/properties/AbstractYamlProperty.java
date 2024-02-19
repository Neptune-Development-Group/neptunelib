package com.neptunedevelopmentteam.neptunelib.yaml.properties;

import com.neptunedevelopmentteam.neptunelib.yaml.YamlCategory;

public abstract class AbstractYamlProperty<T> implements YamlProperty<T> {
    YamlCategory category;
    String name;
    T value;

    public AbstractYamlProperty(YamlCategory category, String name, T value) {
        this.category = category;
        this.name = name;
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public YamlCategory getCategory() {
        return category;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isMatchingProperty(String matching_full_name) {
        return matching_full_name.equals(getFullName());
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }
}
