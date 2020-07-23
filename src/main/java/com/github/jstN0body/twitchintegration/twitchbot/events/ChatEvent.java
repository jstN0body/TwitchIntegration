package com.github.jstN0body.twitchintegration.twitchbot.events;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ChatEvent {


    public ChatEvent(SimpleEventHandler eventHandler) {

        eventHandler.onEvent(ChannelMessageEvent.class, this::onChat);
    }

    public void onChat(ChannelMessageEvent event) {
        Bukkit.broadcastMessage("[" + ChatColor.GREEN + event.getChannel().getName() + ChatColor.RESET + "] " +
                "[" + ChatColor.AQUA + event.getUser().getName() + ChatColor.RESET + "] " + event.getMessage());
    }
}
