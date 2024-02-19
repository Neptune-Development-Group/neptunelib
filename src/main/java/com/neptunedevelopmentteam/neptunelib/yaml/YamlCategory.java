package com.neptunedevelopmentteam.neptunelib.yaml;

import java.util.List;

public class YamlCategory {

    private YamlCategory parentCategory;
    private String name;

    public YamlCategory(String name, YamlCategory parentCategory) {
        this.parentCategory = parentCategory;
        this.name = name;
    }
    public YamlCategory(String name) {
        this(name, null);
    }

    public YamlCategory getParentCategory() {
        return parentCategory;
    }
    public String getName() {
        return name;
    }

    public boolean isRootCategory() {
        return parentCategory == null;
    }

    public String getFullName() {
        if (isRootCategory()) {
            return name;
        }
        return parentCategory.getFullName() + "." + name;
    }

    public boolean isMatchingCategory(String matching_full_name) {
        return matching_full_name.equals(getFullName());
    }

    public static YamlCategory getCategoryFromString(String stri) {
        List<String> list = List.of(stri.split("\\."));
        if (list.isEmpty()) return new YamlCategory(stri);
        YamlCategory cat = new YamlCategory(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            cat = new YamlCategory(list.get(i), cat);
        }
        return cat;
    }
}
