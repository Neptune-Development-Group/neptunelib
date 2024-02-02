package com.neptunedevelopmentteam.neptunelib.utils;

import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;

public class NeptuneMessageUtils {

    public static void sendToAllPlayers(MinecraftServer server, Text text) {
        server.getPlayerManager().getPlayerList().forEach(player -> player.sendMessage(text));
    }
}
