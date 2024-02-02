package com.neptunedevelopmentteam.neptunelib;

import com.neptunedevelopmentteam.neptunelib.config.ConfigComment;
import com.neptunedevelopmentteam.neptunelib.config.NeptuneConfig;
import com.neptunedevelopmentteam.neptunelib.config.NeptuneSubConfig;

public class __NeptuneConfig extends NeptuneConfig {

    public __NeptuneServerUtilsConfig SERVER_UTILS = new __NeptuneServerUtilsConfig();


    public static class __NeptuneServerUtilsConfig implements NeptuneSubConfig {
        public NeptuneDiscordIntegrationConfig DISCORD_INTEGRATION = new NeptuneDiscordIntegrationConfig();

        @ConfigComment("Enable server utils for the server if true (if this is disabled all other server utils will be disabled)")
        public Boolean ENABLE = true;

        @ConfigComment("Enable the schedule restart command if true")
        public Boolean SCHEDULE_RESTART_COMMAND = true;
    }

    public static class NeptuneDiscordIntegrationConfig implements NeptuneSubConfig {

        @ConfigComment("Enable discord integration if true")
        public Boolean ENABLE = false;

        @ConfigComment("The bot token that you require for the bot to work")
        public String BOT_TOKEN = "PUT YOUR BOT TOKEN HERE";

        @ConfigComment("The channel id of the minecraft chat channel, this isn't to be changed unless you know what you're doing, use the command inside discord with the bot to change this")
        public Long BINDED_MINECRAFT_CHAT_CHANNEL = 0L;

        @ConfigComment("The channel id of the minecraft console channel, this isn't to be changed unless you know what you're doing, use the command inside discord with the bot to change this")
        public Long BINDED_MINECRAFT_CONSOLE_CHANNEL = 0L;
    }
}
