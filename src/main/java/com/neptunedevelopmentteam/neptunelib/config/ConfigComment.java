package com.neptunedevelopmentteam.neptunelib.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigComment {
    /**
     * The comment you want to display
     */
    String value();

    /**
     * True if the comment should be on the same line as the value, false if you want the comment to be above the value
     */
    boolean inline() default true;
}
