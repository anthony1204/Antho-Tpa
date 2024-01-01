package com.antho.anthotpa.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class tabcompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> c = new ArrayList<String>();
        if(strings.length==1) {
            c.add("run the command without arguments!");
        } else if (strings.length>=2) {
            c.add("why did you KEEP ADDING ARGUMENTS?!?!?!?");
            c.add("by discord/anthony4933");
        }
        return c;
    }
}
