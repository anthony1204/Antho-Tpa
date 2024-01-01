package com.antho.anthotpa;

import net.kyori.adventure.Adventure;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TpaManager {
    private final Map<UUID, TpaRequest> requests = new HashMap<>();

    public void sendRequest(Player from, Player to) {
        if (from.getUniqueId().equals(to.getUniqueId())) {
            from.sendMessage("§cYou can't send tpa-requests to yourself silly!");
            return;
        }

        requests.put(to.getUniqueId(), new TpaRequest(from, to));
        from.sendMessage("§aTpa-Request has been sent to " + to.getName());
        TextComponent cc = new TextComponent("You recieved a teleport request from "+from.getName()+"! ");
        TextComponent c = new TextComponent("§7[§aAccept§7]§r");
        c.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("accept the tp request from "+from.getName())));
        c.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/tpy"));
        to.spigot().sendMessage(cc);
        to.spigot().sendMessage(c);
    }

    public void acceptRequest(Player player) {
        final TpaRequest request = requests.get(player.getUniqueId());

        if (request == null) {
            player.sendMessage("§cYou don't have any tpa-requests!");
            return;
        }

        final Player teleportTo = request.getFrom();
        player.teleport(teleportTo);

        requests.remove(player.getUniqueId());
    }
}
