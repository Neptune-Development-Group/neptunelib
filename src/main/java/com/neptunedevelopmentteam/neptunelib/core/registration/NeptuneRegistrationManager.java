package com.neptunedevelopmentteam.neptunelib.core.registration;

import com.neptunedevelopmentteam.neptunelib.core.registration.annotations.CustomName;
import net.minecraft.item.Item;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class NeptuneRegistrationManager {
    public static List<NeptuneSoundRegistrationType> __sounds = new ArrayList<>();

    @SafeVarargs
    public static void setup(String namespace, Class<? extends NeptuneEasyRegistrationType<?>> ... classes){
        for (Class<? extends NeptuneEasyRegistrationType<?>> clazz: classes) {
            registerHolderClass(clazz, namespace);
        }
    }

    private static <T> void registerHolderClass(Class<? extends NeptuneEasyRegistrationType<?>> clazz, String namespace) {
        Field[] allFields = clazz.getDeclaredFields();
        Field firstField = allFields[0];
        try {
            Method registerMethod = clazz.getMethod("register", firstField.getType(), Field.class, String.class);
            for (Field field: allFields) {
                T object = (T) field.get(null);
                registerMethod.invoke(null, object, field, namespace);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not find register method in class " + clazz.getName());
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCustomNameFromField(Field field) {
        return field.isAnnotationPresent(CustomName.class) ? field.getAnnotation(CustomName.class).value().toLowerCase(Locale.ROOT) : field.getName().toLowerCase(Locale.ROOT);
    }
}
