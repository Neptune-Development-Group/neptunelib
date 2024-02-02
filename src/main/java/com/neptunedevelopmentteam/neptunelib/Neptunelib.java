package com.neptunedevelopmentteam.neptunelib;

import com.neptunedevelopmentteam.neptunelib.core.util.gui.NeptuneGUI;
import com.neptunedevelopmentteam.neptunelib.serverutils.NeptuneServerUtils;
import com.neptunedevelopmentteam.neptunelib.serverutils.commands.ScheduleRestartCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
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

        });
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            if (CONFIG.SERVER_UTILS.ENABLE) {
                NeptuneServerUtils.init();
            }
        });
        ServerTickEvents.END_SERVER_TICK.register((server -> {
            if (CONFIG.SERVER_UTILS.ENABLE) {
                NeptuneServerUtils.tick(server);
            }
        }));
    }
}
