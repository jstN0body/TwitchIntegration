package com.github.jstN0body.twitchintegration.twitchbot.events;

import com.github.jstN0body.twitchintegration.Main;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ChatEvent {

    private final Main plugin;

    public ChatEvent(SimpleEventHandler eventHandler, Main plugin) {
        this.plugin = plugin;

        eventHandler.onEvent(ChannelMessageEvent.class, this::onChat);
    }

    public void onChat(ChannelMessageEvent event) {
        if (plugin.getConfig().getStringList("mutedusers").contains(event.getUser().getName())) return;

        Bukkit.broadcastMessage("[" + ChatColor.GREEN + event.getChannel().getName() + ChatColor.RESET + "] " +
                "[" + ChatColor.AQUA + event.getUser().getName() + ChatColor.RESET + "] " + event.getMessage());
    }
}
