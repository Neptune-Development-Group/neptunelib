package com.neptunedevelopmentteam.neptunelib.yaml.properties;

import com.neptunedevelopmentteam.neptunelib.yaml.YamlCategory;

public interface YamlProperty<T> {
    String asString();

    T getValue();
    void parseStringAndSetValue(String string);
    void setValue(T value);
    YamlCategory getCategory();

    String getName();

    default String getFullName() {
        return getCategory().getFullName() + "." + getName();
    }

    default boolean isStringProperty() {
        return getValue() instanceof String;
    }

    default boolean isBooleanProperty() {
        return getValue() instanceof Boolean;
    }

    default boolean isIntegerProperty() {
        return getValue() instanceof Integer;
    }

    default boolean isLongProperty() {
        return getValue() instanceof Long;
    }

    default boolean isDoubleProperty() {
        return getValue() instanceof Double;
    }

    default YamlStringProperty asStringProperty() {
        return (YamlStringProperty) this;
    }

    default YamlBooleanProperty asBooleanProperty() {
        return (YamlBooleanProperty) this;
    }

    default YamlIntegerProperty asIntegerProperty() {
        return (YamlIntegerProperty) this;
    }

    default YamlLongProperty asLongProperty() {
        return (YamlLongProperty) this;
    }

    default YamlDoubleProperty asDoubleProperty() {
        return (YamlDoubleProperty) this;
    }
}
