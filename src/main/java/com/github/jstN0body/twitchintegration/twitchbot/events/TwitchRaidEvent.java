package com.github.jstN0body.twitchintegration.twitchbot.events;

import com.github.jstN0body.twitchintegration.Plugin;
import com.github.jstN0body.twitchintegration.commands.EnableCommand;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.RaidEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class TwitchRaidEvent {

    private final Plugin plugin;

    public TwitchRaidEvent(SimpleEventHandler eventHandler, Plugin plugin) {
        eventHandler.onEvent(RaidEvent.class, this::onRaid);
        this.plugin = plugin;
    }

    public void onRaid(RaidEvent event) {
        if (!EnableCommand.integrationEnabled) return;

        Bukkit.broadcastMessage(ChatColor.BLUE + event.getRaider().getName() + " has raided with " + event.getViewers() + " viewers!!!");
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players)
                {
                    Player player = (Player) object;
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, 36000, event.getViewers()));
                }
            }
        }.runTask(plugin);
    }
}
