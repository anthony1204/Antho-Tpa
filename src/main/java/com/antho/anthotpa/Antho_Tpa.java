package com.antho.anthotpa;

import com.antho.anthotpa.commands.AcceptCommand;
import com.antho.anthotpa.commands.TpaCommand;
import com.antho.anthotpa.commands.tabcompleter;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

public class Antho_Tpa extends JavaPlugin implements Listener {

    private BukkitAudiences adventure;
    private final Antho_Tpa dis = this;

    public @NonNull BukkitAudiences adventure() {
        if (this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    private TpaManager tpaManager;

    @Override
    public void onEnable() {
        // Initialize an audiences instance for the plugin
        this.adventure = BukkitAudiences.create(this);
        // then do any other initialization
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        this.tpaManager = new TpaManager();
        getCommand("tpa").setExecutor(new TpaCommand(tpaManager));
        getCommand("tpy").setExecutor(new AcceptCommand(tpaManager));
        getCommand("tpa").setTabCompleter(new tabcompleter());
        getCommand("tpy").setTabCompleter(new tabcompleter());
    }

    @Override
    public void onDisable() {
        if (this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }

    @EventHandler
    public void chtevent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (!(p.getPersistentDataContainer().has(new NamespacedKey("at", "tpchat"), PersistentDataType.BOOLEAN))) {
            return;
        }
        if (p.getPersistentDataContainer().get(new NamespacedKey("at", "tpchat"), PersistentDataType.BOOLEAN)) {
            if (Bukkit.getPlayer(e.getMessage()) == null) {
                p.sendMessage("the provided player is not online or doesn't exist! example: you can shorten names so you can type 'm' to teleport to masen!");
                p.sendMessage("please type /tpa again.");
                return;
            }
            tpaManager.sendRequest(p, Bukkit.getPlayer(e.getMessage()));
            p.sendMessage("tp request sent to " + Bukkit.getPlayer(e.getMessage()).getName() + "!");
            e.setCancelled(true);
            p.getPersistentDataContainer().remove(new NamespacedKey("at", "tpchat"));
        }
    }
    public static Antho_Tpa get(){
        return get().dis;
    }

}
