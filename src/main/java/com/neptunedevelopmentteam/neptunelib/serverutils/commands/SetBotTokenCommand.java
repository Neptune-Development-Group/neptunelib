package com.neptunedevelopmentteam.neptunelib.serverutils.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.neptunedevelopmentteam.neptunelib.Neptunelib;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Base64;

public class SetBotTokenCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("set_bot_token")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.argument("bot_token", StringArgumentType.greedyString())
                        .executes(context -> {
                            String botToken = StringArgumentType.getString(context, "bot_token");
                            Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BOT_TOKEN = botToken;
                            context.getSource().sendFeedback(() -> Text.of("Set bot token to " + botToken + "\n Please restart the server for the changes to take effect"), true);
                            return 1;
                        })));
    }
}
