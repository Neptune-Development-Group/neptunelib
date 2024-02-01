package com.neptunedevelopmentteam.neptunelib.config;

import com.neptunedevelopmentteam.neptunelib.Neptunelib;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class NeptuneSerializationUtil {

    public static List<Class<?>> supportedClasses = new ArrayList<>();

    static {
        supportedClasses.add(Boolean.class);
        supportedClasses.add(Integer.class);
        supportedClasses.add(Long.class);
        supportedClasses.add(Double.class);
        supportedClasses.add(String.class);
        supportedClasses.add(int.class);
        supportedClasses.add(long.class);
        supportedClasses.add(double.class);
        supportedClasses.add(boolean.class);
    }

    public static boolean isSupportedClass(Class<?> clazz) {
        return supportedClasses.contains(clazz);
    }

    public static String getSerializedValue(Object value) {
        String val = (isSupportedClass(value.getClass())) ? value.toString() : "";
        if (Objects.equals(val, "")) {
            Neptunelib.LOGGER.warn("Could not serialize value " + value + " with class " + value.getClass().getName() + "\nIs this class unsupported?");
        }
        return val;
    }

    public static Object getDeserializedValue(String value, Class<?> clazz) {
        if (clazz.equals(Boolean.class)) {
            return Boolean.parseBoolean(value);
        } else if (clazz.equals(Integer.class)) {
            return Integer.parseInt(value);
        } else if (clazz.equals(Long.class)) {
            return Long.parseLong(value);
        } else if (clazz.equals(Double.class)) {
            return Double.parseDouble(value);
        } else if (clazz.equals(String.class)) {
            return value;
        } else if (clazz.equals(int.class)) {
            return Integer.parseInt(value);
        } else if (clazz.equals(long.class)) {
            return Long.parseLong(value);
        } else if (clazz.equals(double.class)) {
            return Double.parseDouble(value);
        } else if (clazz.equals(boolean.class)) {
            return Boolean.parseBoolean(value);
        }
        // @TODO: Add a way for people to add their own custom serializers
        Neptunelib.LOGGER.warn("Could not deserialize value " + value + " with class " + clazz.getName() + "\nIs this class unsupported?");
        return null;
    }
}
