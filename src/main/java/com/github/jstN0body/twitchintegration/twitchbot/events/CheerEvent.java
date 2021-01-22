package com.github.jstN0body.twitchintegration.twitchbot.events;

import com.github.jstN0body.twitchintegration.Plugin;
import com.github.jstN0body.twitchintegration.Reward;
import com.github.jstN0body.twitchintegration.commands.EnableCommand;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.pubsub.events.ChannelBitsEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class CheerEvent {

    private final Plugin plugin;

    public CheerEvent(SimpleEventHandler eventHandler, Plugin plugin) {
        this.plugin = plugin;

        eventHandler.onEvent(ChannelBitsEvent.class, this::onCheer);
    }

    public void onCheer(ChannelBitsEvent event) {
        if (!EnableCommand.integrationEnabled) return;


        Integer bits = event.getData().getBitsUsed();
        System.out.println(bits);
        Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + event.getData().getUserName() + " has cheered " + bits + " bits!!!");

        Reward reward = Reward.get(bits);
        Reward.execute(reward, plugin);
    }
}
