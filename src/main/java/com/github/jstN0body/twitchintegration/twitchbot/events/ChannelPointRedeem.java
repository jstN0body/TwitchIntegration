package com.github.jstN0body.twitchintegration.twitchbot.events;

import com.github.jstN0body.twitchintegration.Main;
import com.github.jstN0body.twitchintegration.commands.EnableCommand;
import com.github.jstN0body.twitchintegration.twitchbot.Actions;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

public class ChannelPointRedeem {

    private final Main plugin;

    public ChannelPointRedeem(SimpleEventHandler eventHandler, Main plugin) {
        this.plugin = plugin;

        eventHandler.onEvent(RewardRedeemedEvent.class, this::onRedeem);
    }

    public void onRedeem(RewardRedeemedEvent event) {
        if (!EnableCommand.integrationEnabled) return;

        String name = event.getRedemption().getReward().getTitle();
        System.out.println(name);
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + event.getRedemption().getUser().getDisplayName() + " has redeemed " + name + "!!!");
        FileConfiguration config = plugin.getConfig();
        Actions actions = new Actions(plugin);

        if (name.equalsIgnoreCase(config.getString("channelpoints.creeper"))) {
            actions.spawnMob(EntityType.CREEPER);
        } else if (name.equalsIgnoreCase(config.getString("channelpoints.halfhealth"))) {
            actions.halfHealth();
        } else if (name.equalsIgnoreCase(config.getString("channelpoints.diamonds"))) {
            actions.giveDiamonds(config);
        } else if (name.equalsIgnoreCase(config.getString("channelpoints.randomtp"))) {
            actions.randomTp(config);
        } else if (name.equalsIgnoreCase(config.getString("channelpoints.weather"))) {
            actions.weather(config);
        } else if (name.equalsIgnoreCase(config.getString("channelpoints.dimensionswitch"))) {
            actions.switchDimensions(config);
        } else if (name.equalsIgnoreCase(config.getString("channelpoints.inventoryclear"))) {
            actions.clearInventory();
        } else if (name.equalsIgnoreCase(config.getString("channelpoints.chickennugget"))) {
            actions.giveChickenNugget();
        } else if (name.equalsIgnoreCase(config.getString("channelpoints.tnt"))) {
            actions.spawnTnt();
        } else if (name.equalsIgnoreCase(config.getString("channelpoints.food"))) {
            actions.giveItem(config);
        } else if (name.equalsIgnoreCase(config.getString("channelpoints.wither"))) {
            actions.spawnWither();
        }
    }
}
