package com.neptunedevelopmentteam.neptunelib.serverutils;

import com.neptunedevelopmentteam.neptunelib.Neptunelib;
import com.neptunedevelopmentteam.neptunelib.serverutils.discord_commands.DiscordListener;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import net.dv8tion.jda.api.requests.restaction.WebhookAction;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

import java.awt.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class NeptuneDiscordIntegration {

    private static JDA api;
    private static boolean initialized = false;
    private static MinecraftServer game_server;

    public static TextChannel binded_minecraft_chat_channel;

    private static Webhook webhook;

    public static void init(MinecraftServer server) {
        game_server = server;
        if (!Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.ENABLE) return;
        if (Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BOT_TOKEN.equals("PUT YOUR BOT TOKEN HERE")) {
            Neptunelib.LOGGER.error("INVALID BOT TOKEN SET IN CONFIG!");
            return;
        }
        api = JDABuilder.createDefault(Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BOT_TOKEN, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .setStatus(OnlineStatus.ONLINE)
                .addEventListeners(new DiscordListener())
                .setActivity(Activity.customStatus("Watching the server")).build();

        setupCommands();
        if (Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BINDED_MINECRAFT_CHAT_CHANNEL == 0L) {
            Neptunelib.LOGGER.error("INVALID CHANNEL ID SET IN CONFIG!");
            initialized = true;
            return;
        }
        binded_minecraft_chat_channel = api.getTextChannelById(Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BINDED_MINECRAFT_CHAT_CHANNEL);
        if (binded_minecraft_chat_channel == null) {
            initialized = true;
            return;
        }
        binded_minecraft_chat_channel.createWebhook("Minecraft Server").queue((webhook1 -> {
            webhook = webhook1;
        }));
        onServerBootupMessage();
        initialized = true;
    }

    public static void onIngameChatMessage(ServerPlayerEntity sender, Text message) {
        if (!checkIfAllowedToRun()) return;
        if (Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BINDED_MINECRAFT_CHAT_CHANNEL == 0L && binded_minecraft_chat_channel == null) return;
        String url_string = "https://mc-heads.net/head/" + sender.getUuidAsString() +  "/right.png";
        webhook.sendMessage(message.getString()).setUsername(sender.getName().getString()).setAvatarUrl(url_string).queue();
    }

    public static void onPlayerJoin(ServerPlayerEntity player) {
        if (!checkIfAllowedToRun()) return;
        if (Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BINDED_MINECRAFT_CHAT_CHANNEL == 0L && binded_minecraft_chat_channel == null) return;
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(player.getName().getString() + " joined the server!")
                .setColor(Color.GREEN)
                .setThumbnail("https://mc-heads.net/head/" + player.getUuidAsString() +  "/right.png");
        MessageCreateAction messageCreateAction = binded_minecraft_chat_channel.sendMessageEmbeds(embed.build());
        messageCreateAction.queue();
    }

    public static void onPlayerLeave(ServerPlayerEntity player) {
        if (!checkIfAllowedToRun()) return;
        if (Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BINDED_MINECRAFT_CHAT_CHANNEL == 0L && binded_minecraft_chat_channel == null) return;
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(player.getName().getString() + " left the server!")
                .setColor(Color.RED)
                .setThumbnail("https://mc-heads.net/head/" + player.getUuidAsString() +  "/right.png");
        MessageCreateAction messageCreateAction = binded_minecraft_chat_channel.sendMessageEmbeds(embed.build());
        messageCreateAction.queue();
    }

    public static void onServerShutdownMessage() {
        if (!checkIfAllowedToRun()) return;
        if (Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BINDED_MINECRAFT_CHAT_CHANNEL == 0L && binded_minecraft_chat_channel == null) return;
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Server shutting down!")
                .setColor(Color.RED);
        MessageCreateAction messageCreateAction = binded_minecraft_chat_channel.sendMessageEmbeds(embed.build());
        messageCreateAction.queue();
    }

    public static void onServerBootupMessage() {
        if (!checkIfAllowedToRun()) return;
        if (Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BINDED_MINECRAFT_CHAT_CHANNEL == 0L && binded_minecraft_chat_channel == null) return;
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Server is online!")
                .setColor(Color.GREEN);
        MessageCreateAction messageCreateAction = binded_minecraft_chat_channel.sendMessageEmbeds(embed.build());
        messageCreateAction.queue();
    }

    private static void setupCommands() {
        setupBindChannelToMinecraftChatCommand();
    }

    public static void messageEventHandler(MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.getAuthor().isSystem()) return;
        if (event.getChannel().getIdLong() != Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BINDED_MINECRAFT_CHAT_CHANNEL) return;
        Text discord_prefix = Text.literal("[DISCORD] ").setStyle(Style.EMPTY.withBold(true).withColor(TextColor.parse("#5865f2")));
        Text username = Text.literal("<" + event.getAuthor().getEffectiveName() + "> ");
        Text message = Text.literal(event.getMessage().getContentRaw());
        Text final_message = Text.literal("").append(discord_prefix).append(username).append(message);
        game_server.getPlayerManager().broadcast(final_message, false);

    }

    private static void setupBindChannelToMinecraftChatCommand() {
        api.updateCommands().addCommands(
                Commands.slash("bind_channel_to_minecraft_chat", "Bind the minecraft chat channel to the discord webhook")
                        .addOption(OptionType.CHANNEL, "channel", "Channel to bind")
        ).queue();
    }

    public static void shutdown() {
        if (!checkIfAllowedToRun()) return;
        onServerShutdownMessage();
        api.shutdown();

    }

    private static boolean checkIfAllowedToRun() {
        if (Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.ENABLE && initialized) {
            return true;
        }
        return false;
    }
}
