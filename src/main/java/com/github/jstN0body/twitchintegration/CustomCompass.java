package com.github.jstN0body.twitchintegration;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

public class CustomCompass {
    public static void giveCompass(Player player, Location location, String name) {
        ItemStack compass = new ItemStack(Material.COMPASS);
        CompassMeta meta = (CompassMeta) compass.getItemMeta();
        meta.setLodestone(location);
        meta.setDisplayName(name);
        compass.setItemMeta(meta);
        player.getInventory().addItem(compass);
    }
}
