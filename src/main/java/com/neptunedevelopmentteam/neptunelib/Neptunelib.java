package com.neptunedevelopmentteam.neptunelib;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Neptunelib implements ModInitializer {

    public static Logger LOGGER = LoggerFactory.getLogger("neptunelib");
    @Override
    public void onInitialize() {
        LOGGER.info("Hello World from NeptuneLib!");
    }
}
