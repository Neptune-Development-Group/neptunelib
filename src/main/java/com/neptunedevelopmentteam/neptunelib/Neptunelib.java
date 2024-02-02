package com.neptunedevelopmentteam.neptunelib;

import com.neptunedevelopmentteam.neptunelib.core.util.gui.NeptuneGUI;
import com.neptunedevelopmentteam.neptunelib.serverutils.NeptuneDiscordIntegration;
import com.neptunedevelopmentteam.neptunelib.serverutils.NeptuneServerUtils;
import com.neptunedevelopmentteam.neptunelib.serverutils.commands.ScheduleRestartCommand;
import com.neptunedevelopmentteam.neptunelib.serverutils.commands.SetBotTokenCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class Neptunelib implements ModInitializer {

    public static Logger LOGGER = LoggerFactory.getLogger("neptunelib");
    public static __NeptuneConfig CONFIG = new __NeptuneConfig();
    @Override
    public void onInitialize() {
        LOGGER.info("Hello World from NeptuneLib!");
        CONFIG.init("neptunelib");

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            if (!CONFIG.SERVER_UTILS.ENABLE) return;
            if (CONFIG.SERVER_UTILS.SCHEDULE_RESTART_COMMAND) {
                ScheduleRestartCommand.register(dispatcher);
            }
            if (CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.ENABLE) {
                SetBotTokenCommand.register(dispatcher);
            }

        });
        System.out.println(CONFIG.SERVER_UTILS.ENABLE);
        System.out.println(CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.ENABLE);

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            if (CONFIG.SERVER_UTILS.ENABLE) {
                NeptuneServerUtils.init();
                if (CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.ENABLE) {
                    NeptuneDiscordIntegration.init(server);
                }
            }
        });
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
            if (CONFIG.SERVER_UTILS.ENABLE && CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.ENABLE) {
                NeptuneDiscordIntegration.shutdown();
            }
            CONFIG.save();
        });
        ServerTickEvents.END_SERVER_TICK.register((server -> {
            if (CONFIG.SERVER_UTILS.ENABLE) {
                NeptuneServerUtils.tick(server);
            }
        }));
        ServerMessageEvents.CHAT_MESSAGE.register(((message, sender, params) -> {
            NeptuneDiscordIntegration.onIngameChatMessage(sender, message.getContent());
        }));
    }
}
