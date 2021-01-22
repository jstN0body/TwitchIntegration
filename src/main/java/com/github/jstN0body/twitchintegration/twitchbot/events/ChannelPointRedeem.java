package com.github.jstN0body.twitchintegration.twitchbot.events;

import com.github.jstN0body.twitchintegration.Plugin;
import com.github.jstN0body.twitchintegration.Reward;
import com.github.jstN0body.twitchintegration.commands.EnableCommand;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ChannelPointRedeem {

    private final Plugin plugin;

    public ChannelPointRedeem(SimpleEventHandler eventHandler, Plugin plugin) {
        this.plugin = plugin;

        eventHandler.onEvent(RewardRedeemedEvent.class, this::onRedeem);
    }

    public void onRedeem(RewardRedeemedEvent event) {
        if (!EnableCommand.integrationEnabled) return;

        String name = event.getRedemption().getReward().getTitle();
        System.out.println(name);
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + event.getRedemption().getUser().getDisplayName() + " has redeemed " + name + "!!!");

        Reward reward = Reward.get(name);
        Reward.execute(reward, plugin);
    }
}
