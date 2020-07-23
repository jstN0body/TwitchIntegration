package com.github.jstN0body.twitchintegration.twitchbot;

import com.github.jstN0body.twitchintegration.Main;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Actions {

    private final Main plugin;

    public Actions(Main plugin) {this.plugin = plugin;}

    public void spawnCreeper() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    Player player = (Player) object;
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
                }
            }
        }.runTask(plugin);
    }

    public void halfHealth() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    Player player = (Player) object;
                    player.damage(player.getHealth() / 2);
                }
            }
        }.runTask(plugin);
    }

    public void giveDiamonds(FileConfiguration config) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    Player player = (Player) object;
                    ItemStack itemStack = new ItemStack(Material.DIAMOND);
                    for (int i = 0 ; i < config.getInt("amountofdiamonds") ; i++) {
                        player.getInventory().addItem(itemStack);
                    }
                }
            }
        }.runTask(plugin);
    }

    public void randomTp(FileConfiguration config) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    Player player = (Player) object;
                    if (!inNether(player, config)) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spreadplayers ~ ~ 2500 3000 false " + player.getDisplayName());
                    } else {
                        Bukkit.dispatchCommand(player, "function cavespread:spread");
                    }
                }
            }
        }.runTask(plugin);
    }

    public void weather(FileConfiguration config) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather thunder " + config.getInt("weathertime"));
    }

    public void switchDimensions(FileConfiguration config) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    Player player = (Player) object;
                    World nether = Bukkit.getWorld(config.getString("netherworld"));
                    World overworld = Bukkit.getWorld(config.getString("world"));
                    int radius = config.getInt("randomtpradius");
                    int maxRange = radius + 100;
                    if (player.getWorld() == overworld) {
                        player.teleport(new Location(nether, 0, -2, 0));
                        randomTp(config);
                    } else if (inNether(player, config)) {
                        player.teleport(new Location(overworld, 0, -2, 0));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spreadplayers 0 0 " + radius + " " + maxRange + " under 75 false @a");
                    }
                }
            }
        }.runTask(plugin);
    }

    public void clearInventory() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    ((Player) object).getInventory().clear();
                }
            }
        }.runTask(plugin);
    }

    public void giveChickenNugget() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    ItemStack chickenNugget = new ItemStack(Material.GOLD_NUGGET);
                    ItemMeta meta = chickenNugget.getItemMeta();
                    meta.setDisplayName(ChatColor.YELLOW + "Chicken Nugget");
                    chickenNugget.setItemMeta(meta);
                    ((Player) object).getInventory().addItem(chickenNugget);
                }
            }
        }.runTask(plugin);
    }

    public void spawnTnt() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    ((Player) object).getWorld().spawnEntity(((Player) object).getEyeLocation(), EntityType.PRIMED_TNT);
                }
            }
        }.runTask(plugin);
    }

    public void teleportToSky(FileConfiguration config) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    Player player = (Player) object;
                    Location location = player.getLocation();
                    location.setY(config.getDouble("height"));
                    location.getBlock().setType(Material.OBSIDIAN);
                    player.teleport(location.getBlock().getLocation().add(0, 1, 0));
                }
            }
        }.runTask(plugin);
    }

    public void giveFood(FileConfiguration config) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    Player player = (Player) object;
                    for (int i = 0 ; i < config.getInt("foodamount") ; i++) {
                        player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF));
                    }
                }
            }
        }.runTask(plugin);
    }

    public void spawnWither() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Object[] players = Bukkit.getOnlinePlayers().toArray();
                for (Object object : players) {
                    Player player = (Player) object;
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.WITHER);
                }
            }
        }.runTask(plugin);
    }

    private boolean inNether(Player player, FileConfiguration config) {
        return player.getWorld() == Bukkit.getWorld(config.getString("netherworld"));
    }
}
