package com.neptunedevelopmentteam.neptunelib.config;

import java.util.ArrayList;
import java.util.List;

public class NeptuneSerializationUtil {

    private static List<Class<?>> supportedClasses = new ArrayList<>();

    static {
        supportedClasses.add(Boolean.class);
        supportedClasses.add(Integer.class);
        supportedClasses.add(Long.class);
        supportedClasses.add(Double.class);
        supportedClasses.add(String.class);
    }

    public static boolean isSupportedClass(Class<?> clazz) {
        return supportedClasses.contains(clazz);
    }

    public static String getSerializedValue(Object value) {
        return (isSupportedClass(value.getClass())) ? value.toString() : "";
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
        }
        return null;
    }
}
