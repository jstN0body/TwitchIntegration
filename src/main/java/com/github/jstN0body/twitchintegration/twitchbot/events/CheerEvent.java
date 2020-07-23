package com.github.jstN0body.twitchintegration.twitchbot.events;

import com.github.jstN0body.twitchintegration.Main;
import com.github.jstN0body.twitchintegration.commands.EnableCommand;
import com.github.jstN0body.twitchintegration.twitchbot.Actions;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.pubsub.events.ChannelBitsEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class CheerEvent {

    private final Main plugin;

    public CheerEvent(SimpleEventHandler eventHandler, Main plugin) {
        this.plugin = plugin;

        eventHandler.onEvent(ChannelBitsEvent.class, this::onCheer);
    }

    public void onCheer(ChannelBitsEvent event) {
        if (!EnableCommand.integrationEnabled) return;

        FileConfiguration config = plugin.getConfig();
        int bits = event.getData().getBitsUsed();
        System.out.println(bits);
        Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + event.getData().getUserName() + " has cheered " + bits + " bits!!!");
        Actions actions = new Actions(plugin);

        if (bits == config.getInt("bitsforcreeper")) {
            actions.spawnCreeper();
        } else if (bits == config.getInt("bitsforhalfhealth")) {
            actions.halfHealth();
        } else if (bits == config.getInt("bitsfordiamonds")) {
            actions.giveDiamonds(config);
        } else if (bits == config.getInt("bitsforrandomtp")) {
            actions.randomTp(config);
        } else if (bits == config.getInt("bitsforweather")) {
            actions.weather(config);
        } else if (bits == config.getInt("bitsfordimensionswitch")) {
            actions.switchDimensions(config);
        } else if (bits == config.getInt("bitsforinventoryclear")) {
            actions.clearInventory();
        } else if (bits == config.getInt("bitsforchickennugget")) {
            actions.giveChickenNugget();
        } else if (bits == config.getInt("bitsfortnt")) {
            actions.spawnTnt();
        } else if (bits == config.getInt("bitsforsky")) {
            actions.teleportToSky(config);
        } else if (bits == config.getInt("bitsforfood")) {
            actions.giveFood(config);
        } else if (bits == config.getInt("bitsforwither")) {
            actions.spawnWither();
        }
    }
}
