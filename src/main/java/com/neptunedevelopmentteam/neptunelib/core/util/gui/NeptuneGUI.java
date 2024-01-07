package com.neptunedevelopmentteam.neptunelib.core.util.gui;

import net.minecraft.util.Identifier;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        parse(output);
    }

    public void parse(String html) {
        Document doc = Jsoup.parse(html);

        Elements styleElements = doc.select("head style");

        for (Element styleElement : styleElements) {
            System.out.println(styleElement.html());
        }
    }

    public void parseCSSStyle(String style) {

    }
}
