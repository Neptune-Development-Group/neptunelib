package com.neptunedevelopmentteam.neptunelib.core.init_handlers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CustomName {
    String value();
}