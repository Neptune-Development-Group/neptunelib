package com.neptunedevelopmentteam.neptunelib.config;

import com.neptunedevelopmentteam.neptunelib.Neptunelib;
import com.neptunedevelopmentteam.neptunelib.core.registration.annotations.CustomName;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NeptuneConfig {

    // Ported from NeptuneLib(experimental staging) for FabricMC by Creativious

    @NotConfigField
    private Path config_path;
    @NotConfigField
    private boolean init_ran = false;
    @NotConfigField
    private NeptuneYaml loaded_config_yaml;

    @NotConfigField

    public NeptuneConfig() {
        // @TODO: Add a field analyzer
    }

    public void init(String mod_id) {
        this.init_ran = true;
        config_path = FabricLoader.getInstance().getConfigDir().resolve(mod_id + ".yaml");
        File config_file = config_path.toFile();
        if (config_file.exists()) {
            this.loadConfig(config_file);
        }
        else {
            this.saveConfig(config_file);
        }
    }
    public void save() {
        if (this.init_ran) {
            this.saveConfig(config_path.toFile());
        }
    }

    private void saveConfig(File config_file) {
        try {
            HashMap<String, String> objects_to_save = processFieldsForSave(this, getFields());
            if (this.loaded_config_yaml == null) {
                this.loaded_config_yaml = new NeptuneYaml(config_file);
            }
            for (Map.Entry<String, String> entry : objects_to_save.entrySet()) {
                if (entry == null) {
                    continue;
                }

                this.loaded_config_yaml.set(entry.getKey(), entry.getValue());
            }
            this.loaded_config_yaml.save();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    private HashMap<String, String> processFieldsForSave(Object parent_object, Field[] fields) throws IllegalAccessException {
        HashMap<String, String> save_values = new HashMap<>();
        for (Field field : fields) {
            String name = field.getAnnotation(CustomName.class) != null ? field.getAnnotation(CustomName.class).value() : getNameFromField(field);
            if (Arrays.stream(field.getType().getInterfaces()).toList().contains(NeptuneSubConfig.class)) {
                HashMap<String, String> sub_config_values = processFieldsForSave(field.get(parent_object), getFieldsFromClass(field.getType()));
                for (Map.Entry<String, String> sub_config_entry : sub_config_values.entrySet()) {
                    save_values.put(name + "." + sub_config_entry.getKey(), sub_config_entry.getValue());
                }
                continue;
            }
            if (!NeptuneSerializationUtil.isSupportedClass(field.getType())) continue;
            String comment = field.getAnnotation(ConfigComment.class) != null ? field.getAnnotation(ConfigComment.class).value() : "";
            if (comment.contains("\n")) {
                Neptunelib.LOGGER.warn("Attempted to use newline in comment for " + name + " in " + parent_object.getClass().getName());
            }
            comment = comment.replace("\n", "");
            String value = NeptuneYaml.getYamlReadyString(NeptuneSerializationUtil.getSerializedValue(field.get(parent_object)), comment);
            if (value.isEmpty()) continue;
            save_values.put(name, value);
        }
        return save_values;
    }
    private void loadConfig(File config_file) {
        System.out.println("Loading config");
        if (this.loaded_config_yaml == null) {
            this.loaded_config_yaml = new NeptuneYaml(config_file);
        }
        Map<String, String> yaml_values = this.loaded_config_yaml.load();
        for (Map.Entry<String, String> entry : yaml_values.entrySet()) {
            try {
                findMatchingFieldAndSetValue(entry);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }

    }

    private void findMatchingFieldAndSetValue(Map.Entry<String, String> entry) throws IllegalAccessException {
        Field[] fields = getFields();
        for (Field other_field : fields) {
            String key = other_field.isAnnotationPresent(CustomName.class) ? other_field.getAnnotation(CustomName.class).value() : getNameFromField(other_field);
            if (key.equals(entry.getKey())) {
                other_field.setAccessible(true);
                if (!NeptuneSerializationUtil.isSupportedClass(other_field.getType())) continue;
                other_field.set(this, NeptuneSerializationUtil.getDeserializedValue(entry.getValue(), other_field.getType()));
            }
            else if (entry.getKey().contains(".")) {
                String first_category_from_entry = entry.getKey().substring(0, entry.getKey().indexOf("."));
                if (!first_category_from_entry.equals(key)) continue;
                findMatchingFieldAndSetValue(entry, other_field.get(this), getFieldsFromClass(other_field.get(this).getClass()), first_category_from_entry);
            }
        }
    }

    private void findMatchingFieldAndSetValue(Map.Entry<String, String> entry, Object parent_object, Field[] fields, String last_category_path) throws IllegalAccessException {
        for (Field other_field : fields) {
            String key = last_category_path + "." + (other_field.isAnnotationPresent(CustomName.class) ? other_field.getAnnotation(CustomName.class).value() : getNameFromField(other_field));
            if (key.equals(entry.getKey())) {
                other_field.setAccessible(true);
                if (!NeptuneSerializationUtil.isSupportedClass(other_field.getType())) continue;
                other_field.set(parent_object, NeptuneSerializationUtil.getDeserializedValue(entry.getValue(), other_field.getType()));
            }
            else if (entry.getKey().contains(".")) {
                String regex = "^" + last_category_path + "\\.[\\w\\s]*";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(key);
                if (!matcher.find()) continue;
                String t_category = matcher.group(0);
                matcher = pattern.matcher(entry.getKey());
                if (!matcher.find()) continue;
                String new_last_category = matcher.group(0);
                if (!t_category.equals(new_last_category)) continue;
                findMatchingFieldAndSetValue(entry, other_field.get(parent_object), getFieldsFromClass(other_field.get(parent_object).getClass()), new_last_category);
            }
        }
    }

    private String getNameFromField(Field field) {
        return field.getName().toLowerCase(Locale.ROOT).replace("_", " ");
    }

    private Field[] getFields() {
        return getFieldsFromClass(this.getClass());
    }

    private Field[] getFieldsFromClass(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields()).filter(field -> !field.isAnnotationPresent(NotConfigField.class)).toArray(Field[]::new);
    }
}
