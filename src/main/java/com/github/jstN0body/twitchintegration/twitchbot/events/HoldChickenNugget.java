package com.github.jstN0body.twitchintegration.twitchbot.events;

import com.github.jstN0body.twitchintegration.Main;
import com.github.jstN0body.twitchintegration.commands.EnableCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HoldChickenNugget implements Listener {

    public HoldChickenNugget(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void switchHeldItem(PlayerItemHeldEvent event) {
        if (!EnableCommand.integrationEnabled) return;

        try {
            ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Chicken Nugget")) {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 2));
                event.getPlayer().getInventory().remove(item);
            }
        } catch (Exception ignored) {}

    }
}
