package com.neptunedevelopmentteam.neptunelib.serverutils;

import com.neptunedevelopmentteam.neptunelib.serverutils.commands.ScheduleRestartCommand;
import com.neptunedevelopmentteam.neptunelib.utils.DeltaTimeManager;
import com.neptunedevelopmentteam.neptunelib.utils.NeptuneMessageUtils;
import com.neptunedevelopmentteam.neptunelib.utils.TimeUtil;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

import static com.neptunedevelopmentteam.neptunelib.Neptunelib.CONFIG;

public class NeptuneServerUtils {

    private static boolean tenSecondsLeft = false;
    private static boolean nineSecondsLeft = false;
    private static boolean eightSecondsLeft = false;
    private static boolean sevenSecondsLeft = false;
    private static boolean sixSecondsLeft = false;
    private static boolean fiveSecondsLeft = false;
    private static boolean fourSecondsLeft = false;
    private static boolean threeSecondsLeft = false;
    private static boolean twoSecondsLeft = false;
    private static boolean oneSecondsLeft = false;



    public static void init() {

    }

    public static void tick(MinecraftServer server) {
        if (!CONFIG.SERVER_UTILS.ENABLE) return;

        if (ScheduleRestartCommand.ENABLED && ScheduleRestartCommand.restart_scheduled) {
            if (!DeltaTimeManager.isStillWaitingOnDelay("neptunelib-schedulerestart-time")) {
                Text text = Text.literal("Restarting server...").setStyle(Style.EMPTY.withBold(true).withColor(TextColor.parse("red")));
                NeptuneMessageUtils.sendToAllPlayers(server, text);
                for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                    player.networkHandler.disconnect(text);
                }
                server.stop(true);
                return;
            }
            if (TimeUtil.millisecondsToSeconds(DeltaTimeManager.timeLeft("neptunelib-schedulerestart-time")) == 10) {
                if (tenSecondsLeft) return;
                Text text = Text.literal("Restarting server in 10 seconds").setStyle(Style.EMPTY.withBold(true).withColor(TextColor.parse("red")));
                NeptuneMessageUtils.sendToAllPlayers(server, text);
                tenSecondsLeft = true;
                return;
            }
            if (TimeUtil.millisecondsToSeconds(DeltaTimeManager.timeLeft("neptunelib-schedulerestart-time")) == 9) {
                if (nineSecondsLeft) return;
                Text text = Text.literal("Restarting server in 9 seconds").setStyle(Style.EMPTY.withBold(true).withColor(TextColor.parse("red")));
                NeptuneMessageUtils.sendToAllPlayers(server, text);
                nineSecondsLeft = true;
                return;
            }
            if (TimeUtil.millisecondsToSeconds(DeltaTimeManager.timeLeft("neptunelib-schedulerestart-time")) == 8) {
                if (eightSecondsLeft) return;
                Text text = Text.literal("Restarting server in 8 seconds").setStyle(Style.EMPTY.withBold(true).withColor(TextColor.parse("red")));
                NeptuneMessageUtils.sendToAllPlayers(server, text);
                eightSecondsLeft = true;
                return;
            }
            if (TimeUtil.millisecondsToSeconds(DeltaTimeManager.timeLeft("neptunelib-schedulerestart-time")) == 7) {
                if (sevenSecondsLeft) return;
                Text text = Text.literal("Restarting server in 7 seconds").setStyle(Style.EMPTY.withBold(true).withColor(TextColor.parse("red")));
                NeptuneMessageUtils.sendToAllPlayers(server, text);
                sevenSecondsLeft = true;
                return;
            }
            if (TimeUtil.millisecondsToSeconds(DeltaTimeManager.timeLeft("neptunelib-schedulerestart-time")) == 6) {
                if (sixSecondsLeft) return;
                Text text = Text.literal("Restarting server in 6 seconds").setStyle(Style.EMPTY.withBold(true).withColor(TextColor.parse("red")));
                NeptuneMessageUtils.sendToAllPlayers(server, text);
                sixSecondsLeft = true;
                return;
            }
            if (TimeUtil.millisecondsToSeconds(DeltaTimeManager.timeLeft("neptunelib-schedulerestart-time")) == 5) {
                if (fiveSecondsLeft) return;
                Text text = Text.literal("Restarting server in 5 seconds").setStyle(Style.EMPTY.withBold(true).withColor(TextColor.parse("red")));
                NeptuneMessageUtils.sendToAllPlayers(server, text);
                fiveSecondsLeft = true;
                return;
            }
            if (TimeUtil.millisecondsToSeconds(DeltaTimeManager.timeLeft("neptunelib-schedulerestart-time")) == 4) {
                if (fourSecondsLeft) return;
                Text text = Text.literal("Restarting server in 4 seconds").setStyle(Style.EMPTY.withBold(true).withColor(TextColor.parse("red")));
                NeptuneMessageUtils.sendToAllPlayers(server, text);
                fourSecondsLeft = true;
                return;
            }
            if (TimeUtil.millisecondsToSeconds(DeltaTimeManager.timeLeft("neptunelib-schedulerestart-time")) == 3) {
                if (threeSecondsLeft) return;
                Text text = Text.literal("Restarting server in 3 seconds").setStyle(Style.EMPTY.withBold(true).withColor(TextColor.parse("red")));
                NeptuneMessageUtils.sendToAllPlayers(server, text);
                threeSecondsLeft = true;
                return;
            }
            if (TimeUtil.millisecondsToSeconds(DeltaTimeManager.timeLeft("neptunelib-schedulerestart-time")) == 2) {
                if (twoSecondsLeft) return;
                Text text = Text.literal("Restarting server in 2 seconds").setStyle(Style.EMPTY.withBold(true).withColor(TextColor.parse("red")));
                NeptuneMessageUtils.sendToAllPlayers(server, text);
                twoSecondsLeft = true;
                return;
            }
            if (TimeUtil.millisecondsToSeconds(DeltaTimeManager.timeLeft("neptunelib-schedulerestart-time")) == 1) {
                if (oneSecondsLeft) return;
                Text text = Text.literal("Restarting server in 1 second").setStyle(Style.EMPTY.withBold(true).withColor(TextColor.parse("red")));
                NeptuneMessageUtils.sendToAllPlayers(server, text);
                oneSecondsLeft = true;
                return;
            }
        }
    }
}
