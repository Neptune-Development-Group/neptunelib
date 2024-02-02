package com.neptunedevelopmentteam.neptunelib;

import com.neptunedevelopmentteam.neptunelib.config.ConfigComment;
import com.neptunedevelopmentteam.neptunelib.config.NeptuneConfig;
import com.neptunedevelopmentteam.neptunelib.config.NeptuneSubConfig;

public class __NeptuneConfig extends NeptuneConfig {

    public __NeptuneServerUtilsConfig SERVER_UTILS = new __NeptuneServerUtilsConfig();

    public static class __NeptuneServerUtilsConfig implements NeptuneSubConfig {

        @ConfigComment("Enable server utils for the server if true (if this is disabled all other server utils will be disabled)")
        public Boolean ENABLE = true;

        @ConfigComment("Enable the schedule restart command if true")
        public Boolean SCHEDULE_RESTART_COMMAND = true;
    }
}
