package com.neptunedevelopmentteam.neptunelib.core.util.gui;

import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.util.Scanner;

public class NeptuneGUI {
    public NeptuneGUI() {

    }
    public void test() {
        ClassLoader classLoader = NeptuneGUI.class.getClassLoader();
        parseResource(new Identifier("neptunelib", "test"));
    }

    public void parseResource(Identifier identifier) {
        Class<?> clazz = getClass();
        ClassLoader classLoader = clazz.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("assets/" + identifier.getNamespace() + "/html/" + identifier.getPath() + ".html");
        Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
        String output = scanner.hasNext() ? scanner.next() : "";
    }
}
