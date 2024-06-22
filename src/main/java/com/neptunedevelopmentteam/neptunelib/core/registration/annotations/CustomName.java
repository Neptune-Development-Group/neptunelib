package com.neptunedevelopmentteam.neptunelib.core.registration.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface CustomName {
    String value();
}