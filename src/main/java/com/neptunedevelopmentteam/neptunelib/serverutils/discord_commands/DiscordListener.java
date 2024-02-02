package com.neptunedevelopmentteam.neptunelib.serverutils.discord_commands;

import com.neptunedevelopmentteam.neptunelib.Neptunelib;
import com.neptunedevelopmentteam.neptunelib.serverutils.NeptuneDiscordIntegration;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.GuildChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static com.neptunedevelopmentteam.neptunelib.serverutils.NeptuneDiscordIntegration.messageEventHandler;

public class DiscordListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("bind_channel_to_minecraft_chat")) {
            GuildChannelUnion channel = event.getInteraction().getOption("channel").getAsChannel();
            TextChannel textChannel = channel.asTextChannel();
            Neptunelib.CONFIG.SERVER_UTILS.DISCORD_INTEGRATION.BINDED_MINECRAFT_CHAT_CHANNEL = textChannel.getIdLong();
            event.reply("You've binded the channel " + textChannel.getName() + " to the minecraft chat").queue();
            NeptuneDiscordIntegration.binded_minecraft_chat_channel = textChannel;
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        messageEventHandler(event);
    }
}
