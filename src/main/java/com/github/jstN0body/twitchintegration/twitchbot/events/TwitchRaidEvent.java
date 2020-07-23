package com.github.jstN0body.twitchintegration.twitchbot.events;

import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.chat.events.channel.RaidEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TwitchRaidEvent {

    public TwitchRaidEvent(SimpleEventHandler eventHandler) {
        eventHandler.onEvent(RaidEvent.class, this::onRaid);
    }

    public void onRaid(RaidEvent event) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "title @a title {\"text\":\"" + event.getRaider().getName() + " has raided!!!\",\"bold\":true, \"color\":\"blue\"} times 10 80 10");
        Object[] players = Bukkit.getOnlinePlayers().toArray();
        for (Object object : players) {
            Player player = (Player) object;
            player.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, 36000, event.getViewers()));
        }
    }
}
