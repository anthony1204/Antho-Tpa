package com.antho.anthotpa.commands;

import com.antho.anthotpa.TpaManager;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class TpaCommand implements CommandExecutor {
    private final TpaManager tpaManager;

    public TpaCommand(TpaManager tpaManager) {
        this.tpaManager = tpaManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can execute this command!");
            return true;
        }

        Player p = (Player) sender;

        if (args.length >= 1) {
            p.sendMessage("§cUsage: /tpa");
            return true;
        }
        p.sendMessage("type your target player in the chat.");
        p.getPersistentDataContainer().set(new NamespacedKey("at","tpchat"), PersistentDataType.BOOLEAN,true);
        return true;
    }
}
