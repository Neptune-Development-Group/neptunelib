package com.neptunedevelopmentteam.neptunelib.yaml.properties;

import com.neptunedevelopmentteam.neptunelib.yaml.YamlCategory;

import java.util.ArrayList;
import java.util.List;

public class YamlListProperty extends AbstractYamlProperty<List<String>> {
    public YamlListProperty(YamlCategory category, String name, List<String> value) {
        super(category, name, value);
    }

    @Override
    public String asString() {
        return value.toString();
    }

    @Override
    public void parseStringAndSetValue(String string) {
        String[] lines = string.split("\n");
        List<String> list = new ArrayList<>();
        for (String line : lines) {
            // remove all tabs
            line = line.replaceAll("\t", "");
            if (line.charAt(0) != '-') continue;
            list.add(line.substring(1));
        }
        setValue(list);
    }

    @Override
    public String getYamlReadyString() {
        StringBuilder string = new StringBuilder();
        string.append(name).append(":\n");
        for (String line : getValue()) {
            // add all tabs
            int tabs = category.getCategoryCount() + 1;
            string.append("\t".repeat(Math.max(0, tabs)));
            string.append("- ").append(line).append("\n");
        }
        // remove the last newline
        string.deleteCharAt(string.length() - 1);
        return string.toString();
    }
}
