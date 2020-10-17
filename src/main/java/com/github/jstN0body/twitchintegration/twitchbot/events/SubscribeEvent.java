package com.github.jstN0body.twitchintegration.twitchbot.events;

import com.github.jstN0body.twitchintegration.commands.EnableCommand;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.pubsub.events.ChannelSubscribeEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class SubscribeEvent {

    public SubscribeEvent(SimpleEventHandler eventHandler) {

        eventHandler.onEvent(ChannelSubscribeEvent.class, this::onSubscribe);
    }

    public void onSubscribe(ChannelSubscribeEvent event) {
        if (!EnableCommand.integrationEnabled) return;
        Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.YELLOW + event.getData().getRecipientDisplayName() + " has subscribed to " + event.getData().getChannelName());
        for (Player player : Bukkit.getOnlinePlayers()) {
            Firework firework = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
            FireworkMeta meta = firework.getFireworkMeta();
            meta.addEffect(FireworkEffect.builder().trail(true).withColor(Color.AQUA).with(FireworkEffect.Type.BALL_LARGE).build());
            firework.setFireworkMeta(meta);
        }
    }
}
