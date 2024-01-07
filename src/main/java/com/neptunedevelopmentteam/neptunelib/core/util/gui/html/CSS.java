package com.neptunedevelopmentteam.neptunelib.core.util.gui.html;

import java.util.List;

public class CSS {
    public String identifier;
    public List<CSSAttribute> attributes;
    public CSS(String identifier, CSSAttribute[] attributes) {
        this.identifier = identifier;
        this.attributes = List.of(attributes);
    }
}
