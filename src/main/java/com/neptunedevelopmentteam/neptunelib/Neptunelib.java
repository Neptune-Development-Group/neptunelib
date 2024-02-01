package com.neptunedevelopmentteam.neptunelib;

import com.neptunedevelopmentteam.neptunelib.core.util.gui.NeptuneGUI;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Neptunelib implements ModInitializer {

    public static Logger LOGGER = LoggerFactory.getLogger("neptunelib");
    @Override
    public void onInitialize() {
        LOGGER.info("Hello World from NeptuneLib!");
    }
}
