package com.github.jstN0body.twitchintegration;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

public class CustomMap {
    public static void giveMap(Player player, Location location, String name) {
        ItemStack map = new ItemStack(Material.MAP);
        MapMeta meta = (MapMeta) map.getItemMeta();
        MapView view = Bukkit.createMap(location.getWorld());
        view.setTrackingPosition(true);
        view.setUnlimitedTracking(true);
        view.setCenterX(location.getBlockX());
        view.setCenterZ(location.getBlockZ());
        meta.setMapView(view);
        meta.setDisplayName(name);
        map.setItemMeta(meta);
        player.getInventory().addItem(map);
    }
}
