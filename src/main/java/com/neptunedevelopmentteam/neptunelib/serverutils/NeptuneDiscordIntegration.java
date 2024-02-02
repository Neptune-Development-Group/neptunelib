package com.neptunedevelopmentteam.neptunelib.serverutils;

import com.neptunedevelopmentteam.neptunelib.Neptunelib;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.user.UserStatus;
import org.javacord.api.entity.webhook.Webhook;
import org.javacord.api.entity.webhook.WebhookBuilder;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.interaction.*;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;

import java.awt.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class NeptuneDiscordIntegration {

    private static DiscordApi api;
    private static boolean initialized = false;

    private static SlashCommand bindChannelToMinecraftChatCommand;
    private static ServerTextChannel binded_minecraft_chat_channel;
    private static Webhook binded_minecraft_chat_webhook = null;
    private static MinecraftServer game_server;

    public static void init(MinecraftServer server) {
        game_server = server;
        if (!Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.ENABLE) return;
        if (Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BOT_TOKEN.equals("PUT YOUR BOT TOKEN HERE")) {
            Neptunelib.LOGGER.error("INVALID BOT TOKEN SET IN CONFIG!");
            return;
        }
        api = new DiscordApiBuilder()
                .setToken(Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BOT_TOKEN)
                .addIntents(Intent.MESSAGE_CONTENT, Intent.GUILD_MEMBERS)
                .login()
                .join();
        api.updateStatus(UserStatus.ONLINE);
        api.updateActivity("Watching the server");
        setupCommands();
        api.addSlashCommandCreateListener(NeptuneDiscordIntegration::slashCommandEventHandler);
        if (Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BINDED_MINECRAFT_CHAT_CHANNEL == 0L) {
            Neptunelib.LOGGER.error("INVALID CHANNEL ID SET IN CONFIG!");
            initialized = true;
            return;
        }
        var optional_text_channel = api.getServerTextChannelById(Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BINDED_MINECRAFT_CHAT_CHANNEL);
        if (optional_text_channel.isEmpty()) {
            Neptunelib.LOGGER.error("INVALID CHANNEL ID SET IN CONFIG!");
            initialized = true;
            return;
        }
        binded_minecraft_chat_channel = optional_text_channel.get();
        api.addMessageCreateListener(NeptuneDiscordIntegration::messageEventHandler);
        initialized = true;
    }

    public static void onIngameChatMessage(ServerPlayerEntity sender, Text message) {
        if (!checkIfAllowedToRun()) return;
        if (Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BINDED_MINECRAFT_CHAT_CHANNEL == 0L && binded_minecraft_chat_channel == null) return;
        if (binded_minecraft_chat_webhook == null) {
            binded_minecraft_chat_webhook = new WebhookBuilder(binded_minecraft_chat_channel)
                    .setName("Minecraft Chat")
                    .create()
                    .join();
        }
        String url_string = "https://mc-heads.net/head/" + sender.getUuidAsString() +  "/right.png";
        try {
            URL url = new URL(url_string);
            if (binded_minecraft_chat_webhook.asIncomingWebhook().isEmpty()) return;
            binded_minecraft_chat_webhook.asIncomingWebhook().get().sendMessage(message.getString(), sender.getName().getString(), url);
        } catch (Exception e) {
            binded_minecraft_chat_webhook.asIncomingWebhook().get().sendMessage(message.getString(), sender.getName().getString(), (URL) null);
        }
    }

    public static void onPlayerJoin(ServerPlayerEntity player) {
        if (!checkIfAllowedToRun()) return;
        if (Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BINDED_MINECRAFT_CHAT_CHANNEL == 0L && binded_minecraft_chat_channel == null) return;
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(player.getName().getString() + " joined the server!")
                .setColor(Color.GREEN)
                .setThumbnail("https://mc-heads.net/head/" + player.getUuidAsString() +  "/right.png");
        binded_minecraft_chat_channel.sendMessage(embed);
    }

    public static void onPlayerLeave(ServerPlayerEntity player) {
        if (!checkIfAllowedToRun()) return;
        if (Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BINDED_MINECRAFT_CHAT_CHANNEL == 0L && binded_minecraft_chat_channel == null) return;
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(player.getName().getString() + " left the server!")
                .setColor(Color.RED)
                .setThumbnail("https://mc-heads.net/head/" + player.getUuidAsString() +  "/right.png");
        binded_minecraft_chat_channel.sendMessage(embed);
    }

    private static void setupCommands() {
        setupBindChannelToMinecraftChatCommand();
    }

    private static void messageEventHandler(MessageCreateEvent event) {
        if (event.getChannel().asServerTextChannel().isEmpty()) return;
        if (event.getMessageAuthor().isWebhook()) return;
        if (event.getMessageAuthor().isBotUser()) return;
        if (event.getChannel().asServerTextChannel().get().getId() == binded_minecraft_chat_channel.getId()) {
            Text discord_prefix = Text.literal("[DISCORD] ").setStyle(Style.EMPTY.withBold(true).withColor(TextColor.parse("#5865f2")));
            Text username = Text.literal("<" + event.getMessageAuthor().getDiscriminatedName() + "> ");
            Text message = Text.literal(event.getMessageContent());
            Text final_message = Text.literal("").append(discord_prefix).append(username).append(message);
            game_server.getPlayerManager().broadcast(final_message, false);
        }
    }

    private static void slashCommandEventHandler(SlashCommandCreateEvent event) {
        SlashCommandInteraction slashCommandInteraction = event.getSlashCommandInteraction();
        if (slashCommandInteraction.getCommandId() == bindChannelToMinecraftChatCommand.getId()) {
            Optional<SlashCommandInteractionOption> optional_channel_option = slashCommandInteraction.getOptionByName("channel");
            if (optional_channel_option.isEmpty()) return;
            SlashCommandInteractionOption channel_option = optional_channel_option.get();
            Optional<ServerChannel> optional_channel = channel_option.getChannelValue();
            if (optional_channel.isEmpty()) return;
            ServerChannel channel = optional_channel.get();
            var temp  = channel.asServerTextChannel();
            if (temp.isEmpty()) {
                Neptunelib.LOGGER.error("INVALID CHANNEL ID SET FROM COMMAND");
                slashCommandInteraction
                        .createImmediateResponder()
                        .setContent("Invalid channel")
                        .setFlags(MessageFlag.EPHEMERAL)
                        .respond();
                return;
            }
            binded_minecraft_chat_channel = temp.get();
            Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BINDED_MINECRAFT_CHAT_CHANNEL = channel.getId();
            slashCommandInteraction
                    .createImmediateResponder()
                    .setContent("You've set the minecraft chat channel to " + channel.getName() + "!")
                    .setFlags(MessageFlag.EPHEMERAL)
                    .respond();
        }
    }

    private static void setupBindChannelToMinecraftChatCommand() {
        bindChannelToMinecraftChatCommand = SlashCommand.with("bind_channel_to_minecraft_chat", "Binds a channel to the minecraft chat",
                Collections.singletonList(
                        SlashCommandOption.create(SlashCommandOptionType.CHANNEL, "channel", "The channel to bind", true)
                ))
                .setDefaultEnabledForPermissions(PermissionType.ADMINISTRATOR)
                .setEnabledInDms(false)
                .createGlobal(api)
                .join();
    }

    public static void shutdown() {
        if (!checkIfAllowedToRun()) return;
        if (binded_minecraft_chat_webhook != null) {
            binded_minecraft_chat_webhook.delete();
            binded_minecraft_chat_webhook = null;
        }
        api.disconnect();

    }

    private static boolean checkIfAllowedToRun() {
        if (Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.ENABLE && initialized) {
            return true;
        }
        return false;
    }
}
