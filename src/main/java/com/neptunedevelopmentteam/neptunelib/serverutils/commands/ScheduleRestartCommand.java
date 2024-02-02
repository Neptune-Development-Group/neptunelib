package com.neptunedevelopmentteam.neptunelib.serverutils.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.neptunedevelopmentteam.neptunelib.utils.DeltaTimeManager;
import com.neptunedevelopmentteam.neptunelib.utils.NeptuneMessageUtils;
import com.neptunedevelopmentteam.neptunelib.utils.TimeUtil;
import net.minecraft.command.CommandSource;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

import java.util.ArrayList;
import java.util.List;

public class ScheduleRestartCommand {

    public static Boolean ENABLED = false;
    public static SuggestionProvider<ServerCommandSource> TIMEUNIT_SUGGESTION;

    public static Boolean restart_scheduled = false;
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        ENABLED = true;
        // Create a command that can be executed
        LiteralArgumentBuilder<ServerCommandSource> command = CommandManager
                .literal("schedule_restart").requires(ScheduleRestartCommand::checkPerms)
                .then(CommandManager.argument("timevalue", IntegerArgumentType.integer(0))
                        .then(CommandManager.argument("timeunit", StringArgumentType.word())
                                .suggests(TIMEUNIT_SUGGESTION).executes(ScheduleRestartCommand::runCommand))
                        );

        dispatcher.register(command);

    }

    private static int runCommand(CommandContext<ServerCommandSource> context) {
        Integer timevalue = IntegerArgumentType.getInteger(context, "timevalue");
        String timeunit = StringArgumentType.getString(context, "timeunit");


        int time = switch (timeunit) {
            case "seconds":
                yield TimeUtil.secondsToMilliseconds(timevalue);
            case "minutes":
                yield TimeUtil.minutesToMilliseconds(timevalue);
            case "hours":
                yield TimeUtil.hoursToMilliseconds(timevalue);
            default:
                yield 0;
        };

        if (time == 0) {
            context.getSource().sendError(Text.literal("Invalid time unit"));
            return 0;
        }
        DeltaTimeManager.createDelay("neptunelib-schedulerestart-time", (long) time);
        String formated_time_unit = timeunit;
        if (timevalue == 1) {
            formated_time_unit = timeunit.substring(0, timeunit.length() - 1);
        }
        Text feedback_text = Text.literal("Scheduled a restart in " + timevalue + " " + formated_time_unit);
        context.getSource().sendFeedback(() -> feedback_text, true);
        Text text = Text.literal("Restarting server in " + timevalue + " " + formated_time_unit).setStyle(Style.EMPTY.withBold(true).withColor(TextColor.parse("red")));
       NeptuneMessageUtils.sendToAllPlayers(context.getSource().getServer(), text);

        restart_scheduled = true;
        return 1;
    }


    public static boolean checkPerms(Object source) {
        if (source instanceof ServerCommandSource) {
            return ((ServerCommandSource) source).hasPermissionLevel(2);
        }
        return false;
    }

    static {
        TIMEUNIT_SUGGESTION = (context, builder) -> CommandSource.suggestMatching(new String[]{"seconds", "minutes", "hours"}, builder);
    }
}
